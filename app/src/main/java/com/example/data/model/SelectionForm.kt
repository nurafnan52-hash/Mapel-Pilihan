package com.example.data.model

data class StudentProfile(
  val fullName: String = "",
  val studentClass: String = "X-1",
  val nisn: String = "",
  val birthPlaceDate: String = "",
  val address: String = "",
  val phoneNumber: String = "",
  val parentName: String = "",
  val homeroomTeacherName: String = "",
  val counselorName: String = ""
)

data class SelectionForm(
  val student: StudentProfile = StudentProfile(),
  // Bagian A
  val dreamProfessions: String = "",
  val dreamReason: String = "",
  val targetStudyProgramId: Int? = null,
  // Bagian B - Mata pelajaran pilihan utama (4 atau 5)
  val selectedSubjectIds: List<String> = emptyList(),
  // Mata pelajaran cadangan (1 atau 2)
  val backupSubjectIds: List<String> = emptyList(),
  // Status dan Tanggal
  val submissionDate: String = "",
  val isApprovedByParent: Boolean = false,
  val isApprovedByCounselor: Boolean = false,
  val lastUpdatedAt: Long = System.currentTimeMillis()
)
