package com.example.data.model

enum class RiasecType(
  val code: String,
  val title: String,
  val nickname: String,
  val description: String,
  val typicalCareers: List<String>,
  val studyFields: List<String>
) {
  REALISTIC(
    code = "R",
    title = "Realistic",
    nickname = "Doers (Praktisi)",
    description = "Menyukai kegiatan praktis, bekerja dengan alat/mesin, benda nyata, hewan/tumbuhan, atau aktivitas fisik luar ruangan.",
    typicalCareers = listOf("Teknik Mesin", "Teknik Elektro", "Pertanian", "Mekanik", "Konstruksi", "Penerbangan", "Kelautan"),
    studyFields = listOf("Teknik", "Ilmu Pertanian", "Kehutanan", "Peternakan", "Sains Kebumian", "Transportasi")
  ),
  INVESTIGATIVE(
    code = "I",
    title = "Investigative",
    nickname = "Thinkers (Peneliti)",
    description = "Suka mengamati, menganalisis, meneliti, memecahkan masalah kompleks, berpikir logis, dan kegiatan bernalar ilmiah.",
    typicalCareers = listOf("Dokter", "Peneliti Sains", "Data Scientist", "Software Engineer", "Psikolog", "Apoteker"),
    studyFields = listOf("Kedokteran", "Matematika", "Fisika", "Kimia", "Biologi", "Ilmu Komputer", "Farmasi")
  ),
  ARTISTIC(
    code = "A",
    title = "Artistic",
    nickname = "Creators (Pencipta)",
    description = "Kreatif, imajinatif, menyukai situasi tidak terstruktur, mengekspresikan diri melalui seni, desain, bahasa, tulisan, atau musik.",
    typicalCareers = listOf("Arsitek", "Desainer Grafis / DKV", "Animator", "Sastrawan / Penulis", "Produser Film", "Musisi"),
    studyFields = listOf("Arsitektur", "Desain Komunikasi Visual", "Seni Rupa & Kriya", "Sastra & Bahasa", "Broadcasting", "Animasi")
  ),
  SOCIAL(
    code = "S",
    title = "Social",
    nickname = "Helpers (Penolong)",
    description = "Suka berinteraksi, mengajar, membantu, memberi konseling, merawat, dan bekerja sama dalam kelompok.",
    typicalCareers = listOf("Guru / Pendidik", "Konselor BK", "Perawat", "Pekerja Sosial", "Public Relations", "Hubungan Masyarakat"),
    studyFields = listOf("Ilmu Pendidikan", "Psikologi", "Keperawatan", "Ilmu Komunikasi", "Ilmu Kesejahteraan Sosial")
  ),
  ENTERPRISING(
    code = "E",
    title = "Enterprising",
    nickname = "Persuaders (Pemimpin/Wirausaha)",
    description = "Suka memimpin, mempengaruhi orang lain, bernegosiasi, berwirausaha, menjual ide, dan berbicara di depan umum.",
    typicalCareers = listOf("Wirausahawan", "Manajer Bisnis", "Pengacara", "Diplomat", "Marketer", "Politisi"),
    studyFields = listOf("Manajemen", "Bisnis", "Ilmu Hukum", "Ilmu Hubungan Internasional", "Pemasaran", "Administrasi Publik")
  ),
  CONVENTIONAL(
    code = "C",
    title = "Conventional",
    nickname = "Organizers (Pengelola Data)",
    description = "Suka keteraturan, data angka yang akurat, detail sistematis, prosedur terstruktur, dan administrasi tertib.",
    typicalCareers = listOf("Akuntan", "Auditor", "Perbankan / Bankir", "Analis Keuangan", "Administrator Basis Data"),
    studyFields = listOf("Akuntansi", "Keuangan & Perbankan", "Ilmu Perpustakaan & Informasi", "Statistika", "Administrasi Bisnis")
  );

  companion object {
    fun fromCode(code: String): RiasecType = entries.find { it.code == code } ?: REALISTIC
  }
}

data class Riasec1Question(
  val id: Int,
  val text: String,
  val type: RiasecType
)

data class Riasec2Skill(
  val id: Int,
  val text: String,
  val type: RiasecType
)

data class RiasecEvaluationResult(
  val scores: Map<RiasecType, Int>,
  val topCodes: List<RiasecType>,
  val hollandCode: String,
  val completedAt: Long = System.currentTimeMillis()
)
