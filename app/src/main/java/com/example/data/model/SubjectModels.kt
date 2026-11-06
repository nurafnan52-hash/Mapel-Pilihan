package com.example.data.model

enum class SubjectGroup(val displayName: String, val code: String) {
  MIPA("MIPA (Matematika & IPA)", "MIPA"),
  IPS("IPS (Ilmu Pengetahuan Sosial)", "IPS"),
  BAHASA("Bahasa & Budaya", "BAHASA"),
  VOKASI("Vokasi & Terapan", "VOKASI")
}

data class ElectiveSubject(
  val id: String,
  val name: String,
  val shortName: String,
  val group: SubjectGroup,
  val jpPerWeek: Int = 5,
  val description: String,
  val suitableForProdi: List<String>
)

data class SubjectPackage(
  val id: String,
  val name: String,
  val targetFocus: String,
  val subjectIds: List<String>,
  val totalJp: Int,
  val description: String
)
