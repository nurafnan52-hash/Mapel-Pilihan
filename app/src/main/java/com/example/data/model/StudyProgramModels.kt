package com.example.data.model

enum class StudyProgramCluster(val title: String) {
  HUMANIORA("Rumpun Humaniora"),
  ILMU_SOSIAL("Rumpun Ilmu Sosial"),
  ILMU_ALAM("Rumpun Ilmu Alam"),
  ILMU_FORMAL("Rumpun Ilmu Formal"),
  ILMU_TERAPAN("Rumpun Ilmu Terapan")
}

data class StudyProgram(
  val id: Int,
  val name: String,
  val cluster: StudyProgramCluster,
  val supportingSubjectsText: String, // as officially specified in Kepmendikbudristek 345/M/2022
  val recommendedElectiveIds: List<String>,
  val description: String = ""
)
