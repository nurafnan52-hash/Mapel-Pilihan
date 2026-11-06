package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.local.GuidanceData
import com.example.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class AppScreen(val title: String) {
  HOME("Beranda"),
  RIASEC("Tes RIASEC"),
  PRODI("Katalog Prodi PTN"),
  SUBJECTS("Pilih Mapel"),
  OFFICIAL_FORM("Formulir Resmi"),
  GUIDE_INFO("Buku Panduan")
}

class SelectionViewModel : ViewModel() {

  // Navigation
  private val _currentScreen = MutableStateFlow(AppScreen.HOME)
  val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

  fun navigateTo(screen: AppScreen) {
    _currentScreen.value = screen
  }

  // Student Profile
  private val _studentProfile = MutableStateFlow(
    StudentProfile(
      fullName = "Dian Pratama",
      studentClass = "X-A (Fase E)",
      nisn = "0078129341",
      birthPlaceDate = "Jakarta, 12 Agustus 2008",
      address = "Jl. Merdeka Belajar No. 45",
      phoneNumber = "081234567890",
      parentName = "Bambang Santoso",
      homeroomTeacherName = "Ibu Siti Rahayu, M.Pd.",
      counselorName = "Bapak Ahmad Zulfikar, S.Pd., Kons."
    )
  )
  val studentProfile: StateFlow<StudentProfile> = _studentProfile.asStateFlow()

  fun updateStudentProfile(updated: StudentProfile) {
    _studentProfile.value = updated
  }

  // Aspirations
  private val _dreamProfession = MutableStateFlow("Arsitek / Perancang Bangunan")
  val dreamProfession: StateFlow<String> = _dreamProfession.asStateFlow()

  private val _dreamReason = MutableStateFlow(
    "Tertarik merancang tata ruang masa depan yang estetis, fungsional, dan ramah lingkungan berbasis kearifan lokal."
  )
  val dreamReason: StateFlow<String> = _dreamReason.asStateFlow()

  fun updateAspirations(profession: String, reason: String) {
    _dreamProfession.value = profession
    _dreamReason.value = reason
  }

  // Target Study Program
  private val _targetStudyProgram = MutableStateFlow<StudyProgram?>(
    GuidanceData.studyPrograms.find { it.id == 23 } // Default: Arsitektur (dari contoh Dian)
  )
  val targetStudyProgram: StateFlow<StudyProgram?> = _targetStudyProgram.asStateFlow()

  fun setTargetStudyProgram(program: StudyProgram?) {
    _targetStudyProgram.value = program
  }

  // RIASEC Mode & Answers
  // riasecMode: 1 = RIASEC 1 (42 pernyataan minat), 2 = RIASEC 2 (24 keterampilan, pilih 8)
  private val _riasecMode = MutableStateFlow(1)
  val riasecMode: StateFlow<Int> = _riasecMode.asStateFlow()

  fun setRiasecMode(mode: Int) {
    _riasecMode.value = mode
  }

  private val _riasec1Answers = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
  val riasec1Answers: StateFlow<Map<Int, Boolean>> = _riasec1Answers.asStateFlow()

  private val _riasec2SelectedSkills = MutableStateFlow<Set<Int>>(emptySet())
  val riasec2SelectedSkills: StateFlow<Set<Int>> = _riasec2SelectedSkills.asStateFlow()

  private val _riasecResult = MutableStateFlow<RiasecEvaluationResult?>(null)
  val riasecResult: StateFlow<RiasecEvaluationResult?> = _riasecResult.asStateFlow()

  fun toggleRiasec1Answer(questionId: Int) {
    _riasec1Answers.update { current ->
      val updated = current.toMutableMap()
      val isChecked = updated[questionId] ?: false
      updated[questionId] = !isChecked
      updated
    }
  }

  fun toggleRiasec2Skill(skillId: Int): Boolean {
    var added = false
    _riasec2SelectedSkills.update { current ->
      if (current.contains(skillId)) {
        current - skillId
      } else {
        if (current.size < 8) {
          added = true
          current + skillId
        } else {
          current
        }
      }
    }
    return added
  }

  fun evaluateRiasec1() {
    val answers = _riasec1Answers.value
    val scoreMap = mutableMapOf<RiasecType, Int>().apply {
      RiasecType.entries.forEach { this[it] = 0 }
    }

    GuidanceData.riasec1Questions.forEach { question ->
      if (answers[question.id] == true) {
        scoreMap[question.type] = (scoreMap[question.type] ?: 0) + 1
      }
    }

    val sorted = scoreMap.toList().sortedByDescending { it.second }
    val topThree = sorted.take(3).map { it.first }
    val codeString = topThree.joinToString(separator = "") { it.code }

    _riasecResult.value = RiasecEvaluationResult(
      scores = scoreMap,
      topCodes = topThree,
      hollandCode = codeString
    )
  }

  fun evaluateRiasec2() {
    val selectedIds = _riasec2SelectedSkills.value
    val scoreMap = mutableMapOf<RiasecType, Int>().apply {
      RiasecType.entries.forEach { this[it] = 0 }
    }

    GuidanceData.riasec2Skills.forEach { skill ->
      if (selectedIds.contains(skill.id)) {
        scoreMap[skill.type] = (scoreMap[skill.type] ?: 0) + 1
      }
    }

    val sorted = scoreMap.toList().sortedByDescending { it.second }
    val topThree = sorted.take(3).map { it.first }
    val codeString = topThree.joinToString(separator = "") { it.code }

    _riasecResult.value = RiasecEvaluationResult(
      scores = scoreMap,
      topCodes = topThree,
      hollandCode = codeString
    )
  }

  fun fillPredefinedRiasecDemo() {
    // Fill demo sample for Dian: Realistic + Investigative + Artistic
    val demoMap = mutableMapOf<Int, Boolean>()
    listOf(1, 2, 8, 11, 14, 17, 18, 21, 22, 23, 27, 30, 31, 32, 34, 37, 41).forEach {
      demoMap[it] = true
    }
    _riasec1Answers.value = demoMap
    evaluateRiasec1()
  }

  // Subject Selection
  private val _selectedSubjectIds = MutableStateFlow<List<String>>(
    listOf("mtk_tl", "fis", "kim", "inf") // Default 20 JP (4 mapel @5 JP)
  )
  val selectedSubjectIds: StateFlow<List<String>> = _selectedSubjectIds.asStateFlow()

  private val _backupSubjectIds = MutableStateFlow<List<String>>(
    listOf("geo", "sos") // Default cadangan
  )
  val backupSubjectIds: StateFlow<List<String>> = _backupSubjectIds.asStateFlow()

  fun toggleMainSubject(subjectId: String): String? {
    val current = _selectedSubjectIds.value
    if (current.contains(subjectId)) {
      _selectedSubjectIds.value = current - subjectId
      return null
    }

    if (current.size >= 5) {
      return "Maksimal memilih 5 mata pelajaran pilihan (20 - 25 JP per minggu)"
    }

    // Check if it's already in backup
    if (_backupSubjectIds.value.contains(subjectId)) {
      _backupSubjectIds.value = _backupSubjectIds.value - subjectId
    }

    _selectedSubjectIds.value = current + subjectId
    return null
  }

  fun toggleBackupSubject(subjectId: String): String? {
    val current = _backupSubjectIds.value
    if (current.contains(subjectId)) {
      _backupSubjectIds.value = current - subjectId
      return null
    }

    if (_selectedSubjectIds.value.contains(subjectId)) {
      return "Mata pelajaran ini sudah dipilih sebagai pilihan utama"
    }

    if (current.size >= 2) {
      return "Maksimal memilih 2 mata pelajaran cadangan"
    }

    _backupSubjectIds.value = current + subjectId
    return null
  }

  fun applyPackage(pkg: SubjectPackage) {
    _selectedSubjectIds.value = pkg.subjectIds
  }

  fun clearSelectedSubjects() {
    _selectedSubjectIds.value = emptyList()
  }

  // Official Form Status
  private val _isParentApproved = MutableStateFlow(true)
  val isParentApproved: StateFlow<Boolean> = _isParentApproved.asStateFlow()

  private val _isCounselorApproved = MutableStateFlow(false)
  val isCounselorApproved: StateFlow<Boolean> = _isCounselorApproved.asStateFlow()

  fun toggleParentApproval() {
    _isParentApproved.value = !_isParentApproved.value
  }

  fun toggleCounselorApproval() {
    _isCounselorApproved.value = !_isCounselorApproved.value
  }
}
