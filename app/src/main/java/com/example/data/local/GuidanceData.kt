package com.example.data.local

import com.example.data.model.*

object GuidanceData {

  val electiveSubjects = listOf(
    ElectiveSubject(
      id = "bio",
      name = "Biologi",
      shortName = "BIO",
      group = SubjectGroup.MIPA,
      jpPerWeek = 5,
      description = "Mempelajari makhluk hidup, genetika, bioteknologi, ekologi, anatomi, dan fisiologi tubuh serta lingkungan.",
      suitableForProdi = listOf("Kedokteran", "Kedokteran Gigi", "Farmasi", "Biologi", "Pertanian", "Peternakan", "Keperawatan", "Gizi")
    ),
    ElectiveSubject(
      id = "kim",
      name = "Kimia",
      shortName = "KIM",
      group = SubjectGroup.MIPA,
      jpPerWeek = 5,
      description = "Mempelajari struktur materi, reaksi kimia, stoikiometri, termokimia, larutan, dan senyawa organik.",
      suitableForProdi = listOf("Teknik Kimia", "Kedokteran", "Farmasi", "Teknologi Pangan", "Kimia Murni", "Ilmu Gizi")
    ),
    ElectiveSubject(
      id = "fis",
      name = "Fisika",
      shortName = "FIS",
      group = SubjectGroup.MIPA,
      jpPerWeek = 5,
      description = "Mempelajari mekanika, termodinamika, gelombang, optik, kelistrikan, magnet, dan fisika modern.",
      suitableForProdi = listOf("Teknik Mesin", "Teknik Sipil", "Teknik Elektro", "Arsitektur", "Fisika", "Astronomi", "Kebumian")
    ),
    ElectiveSubject(
      id = "inf",
      name = "Informatika",
      shortName = "INF",
      group = SubjectGroup.MIPA,
      jpPerWeek = 5,
      description = "Mempelajari computational thinking, algoritma pemrograman, analisis data, jaringan komputer, dan dampak sosial informatika.",
      suitableForProdi = listOf("Ilmu Komputer", "Teknik Informatika", "Sistem Informasi", "Sains Data", "Teknologi Informasi")
    ),
    ElectiveSubject(
      id = "mtk_tl",
      name = "Matematika Tingkat Lanjut",
      shortName = "MTK-TL",
      group = SubjectGroup.MIPA,
      jpPerWeek = 5,
      description = "Mendalami geometri analitik, kalkulus lanjut, polinomial, matriks, vektor, dan pemodelan matematis tingkat tinggi.",
      suitableForProdi = listOf("Semua Jurusan Teknik", "Matematika", "Statistika", "Sains Data", "Ilmu Komputer", "Aktuaria")
    ),
    ElectiveSubject(
      id = "sos",
      name = "Sosiologi",
      shortName = "SOS",
      group = SubjectGroup.IPS,
      jpPerWeek = 5,
      description = "Mengkaji interaksi sosial, struktur dan diferensiasi sosial, konflik, perubahan sosial, serta penelitian sosial.",
      suitableForProdi = listOf("Sosiologi", "Ilmu Komunikasi", "Hukum", "Ilmu Politik", "Kriminologi", "Hubungan Internasional", "Psikologi")
    ),
    ElectiveSubject(
      id = "eko",
      name = "Ekonomi",
      shortName = "EKO",
      group = SubjectGroup.IPS,
      jpPerWeek = 5,
      description = "Mempelajari mekanisme pasar, kebijakan moneter dan fiskal, perdagangan internasional, akuntansi, dan literasi finansial.",
      suitableForProdi = listOf("Akuntansi", "Manajemen", "Ilmu Ekonomi", "Bisnis Digital", "Perbankan", "Perpajakan", "Logistik")
    ),
    ElectiveSubject(
      id = "geo",
      name = "Geografi",
      shortName = "GEO",
      group = SubjectGroup.IPS,
      jpPerWeek = 5,
      description = "Mempelajari litosfer, hidrosfer, atmosfer, biosfer, sistem informasi geografis (SIG), penginderaan jauh, dan tata ruang wilayah.",
      suitableForProdi = listOf("Geografi", "Perencanaan Wilayah dan Kota (PWK)", "Sains Informasi Geografi", "Oseanografi", "Klimatologi")
    ),
    ElectiveSubject(
      id = "ant",
      name = "Antropologi",
      shortName = "ANT",
      group = SubjectGroup.IPS,
      jpPerWeek = 5,
      description = "Mempelajari kebudayaan manusia, etnografi, keragaman suku bangsa, evolusi kebudayaan, dan dinamika kearifan lokal.",
      suitableForProdi = listOf("Antropologi Budaya", "Arkeologi", "Pariwisata", "Kajian Budaya", "Sosiologi")
    ),
    ElectiveSubject(
      id = "sej_tl",
      name = "Sejarah Tingkat Lanjut",
      shortName = "SEJ-TL",
      group = SubjectGroup.IPS,
      jpPerWeek = 5,
      description = "Mendalami historiografi, sejarah peradaban dunia, dinamika politik global, metodologi penelitian sejarah, dan sejarah kontemporer.",
      suitableForProdi = listOf("Ilmu Sejarah", "Hubungan Internasional", "Ilmu Hukum", "Kearsipan", "Ilmu Politik")
    ),
    ElectiveSubject(
      id = "indo_tl",
      name = "Bahasa Indonesia Tingkat Lanjut",
      shortName = "BIND-TL",
      group = SubjectGroup.BAHASA,
      jpPerWeek = 5,
      description = "Mendalami retorika, kritik sastra, penulisan kreatif ilmiah dan fiksi, linguistik, serta kajian naskah drama dan puisi.",
      suitableForProdi = listOf("Sastra Indonesia", "Pendidikan Bahasa Indonesia", "Jurnalistik", "Komunikasi Massa", "Penerbitan")
    ),
    ElectiveSubject(
      id = "ing_tl",
      name = "Bahasa Inggris Tingkat Lanjut",
      shortName = "BING-TL",
      group = SubjectGroup.BAHASA,
      jpPerWeek = 5,
      description = "Mengasah kemahiran bahasa Inggris akademis (academic writing, critical reading, debat, analisis teks fiksi dan non-fiksi).",
      suitableForProdi = listOf("Sastra Inggris", "Hubungan Internasional", "Pendidikan Bahasa Inggris", "Linguistik", "Pariwisata Internasional")
    ),
    ElectiveSubject(
      id = "b_arab",
      name = "Bahasa Arab",
      shortName = "ARAB",
      group = SubjectGroup.BAHASA,
      jpPerWeek = 5,
      description = "Mempelajari tata bahasa Arab (Nahwu, Sharaf), percakapan, membaca literatur Arab, dan budaya Timur Tengah.",
      suitableForProdi = listOf("Sastra Arab", "Studi Islam / Syariah", "Pendidikan Bahasa Arab", "Hubungan Internasional")
    ),
    ElectiveSubject(
      id = "b_jepang",
      name = "Bahasa Jepang",
      shortName = "JPN",
      group = SubjectGroup.BAHASA,
      jpPerWeek = 5,
      description = "Mempelajari huruf Hiragana, Katakana, Kanji dasar, tata bahasa, percakapan sehari-hari, dan etika kebudayaan Jepang.",
      suitableForProdi = listOf("Sastra Jepang", "Pendidikan Bahasa Jepang", "Hubungan Internasional", "Pariwisata")
    ),
    ElectiveSubject(
      id = "b_jerman",
      name = "Bahasa Jerman",
      shortName = "JER",
      group = SubjectGroup.BAHASA,
      jpPerWeek = 5,
      description = "Mempelajari kosakata, tata bahasa Jerman (Grammatik), percakapan, dan persiapan studi atau kerja di Eropa Tengah.",
      suitableForProdi = listOf("Sastra Jerman", "Studi Eropa", "Hubungan Internasional")
    ),
    ElectiveSubject(
      id = "b_korea",
      name = "Bahasa Korea",
      shortName = "KOR",
      group = SubjectGroup.BAHASA,
      jpPerWeek = 5,
      description = "Mempelajari huruf Hangeul, pelafalan, tata bahasa dasar, percakapan, dan wawasan industri kreatif kebudayaan Korea.",
      suitableForProdi = listOf("Bahasa dan Kebudayaan Korea", "Kajian Asia Timur", "Pariwisata")
    ),
    ElectiveSubject(
      id = "b_mandarin",
      name = "Bahasa Mandarin",
      shortName = "MAN",
      group = SubjectGroup.BAHASA,
      jpPerWeek = 5,
      description = "Mempelajari Pinyin, aksara Hanzi, nada pelafalan, tata bahasa, dan komunikasi bisnis Mandarin.",
      suitableForProdi = listOf("Sastra Mandarin / China", "Perdagangan Internasional", "Pariwisata", "Hubungan Internasional")
    ),
    ElectiveSubject(
      id = "b_prancis",
      name = "Bahasa Prancis",
      shortName = "PRN",
      group = SubjectGroup.BAHASA,
      jpPerWeek = 5,
      description = "Mempelajari tata bahasa Prancis, percakapan, pemahaman teks, dan wawasan sastra serta seni budaya Francophone.",
      suitableForProdi = listOf("Sastra Prancis", "Diplomasi Internasional", "Pariwisata", "Kuliner & Hospitality")
    ),
    ElectiveSubject(
      id = "pkwu",
      name = "Prakarya & Kewirausahaan (PKWU)",
      shortName = "PKWU",
      group = SubjectGroup.VOKASI,
      jpPerWeek = 2,
      description = "Mempelajari budidaya, kerajinan, rekayasa, atau pengolahan pangan berorientasi nilai ekonomi dan wirausaha mandiri.",
      suitableForProdi = listOf("Manajemen Bisnis", "Wirausaha Mandiri", "Teknologi Hasil Pertanian", "Kriya")
    ),
    ElectiveSubject(
      id = "vokasi_smk",
      name = "Dasar Kejuruan / Vokasi Terapan",
      shortName = "VOKASI",
      group = SubjectGroup.VOKASI,
      jpPerWeek = 5,
      description = "Mata pelajaran dasar program keahlian SMK (misal PPLG, DKV, Animasi, Kuliner, dll.) yang diselenggarakan satuan pendidikan.",
      suitableForProdi = listOf("D4/D3 Vokasi Terapan", "Desain Grafis / DKV", "Broadcasting", "Teknologi Terapan")
    )
  )

  // 59 Program Studi Resmi Kepmendikbudristek No. 345/M/2022 (Lampiran 4)
  val studyPrograms = listOf(
    // HUMANIORA
    StudyProgram(1, "Seni (Seni Rupa, Seni Tari, Musik)", StudyProgramCluster.HUMANIORA, "Seni Budaya", listOf("indo_tl")),
    StudyProgram(2, "Sejarah", StudyProgramCluster.HUMANIORA, "Sejarah", listOf("sej_tl")),
    StudyProgram(3, "Linguistik", StudyProgramCluster.HUMANIORA, "Bahasa Indonesia; atau Bahasa Inggris; atau keduanya", listOf("indo_tl", "ing_tl")),
    StudyProgram(4, "Susastra atau Sastra", StudyProgramCluster.HUMANIORA, "Bahasa Indonesia; atau Bahasa Asing relevan", listOf("indo_tl", "ing_tl", "b_arab", "b_jepang", "b_jerman", "b_mandarin")),
    StudyProgram(5, "Filsafat", StudyProgramCluster.HUMANIORA, "Sosiologi", listOf("sos")),

    // ILMU SOSIAL
    StudyProgram(6, "Sosial (Sosiologi, Antropologi)", StudyProgramCluster.ILMU_SOSIAL, "Sosiologi", listOf("sos", "ant")),
    StudyProgram(7, "Ekonomi (Ilmu Ekonomi)", StudyProgramCluster.ILMU_SOSIAL, "Ekonomi; atau Matematika; atau keduanya", listOf("eko", "mtk_tl")),
    StudyProgram(8, "Pertahanan", StudyProgramCluster.ILMU_SOSIAL, "Pendidikan Pancasila", listOf("sos", "sej_tl")),
    StudyProgram(9, "Psikologi", StudyProgramCluster.ILMU_SOSIAL, "Sosiologi; atau Matematika; atau keduanya", listOf("sos", "mtk_tl", "bio")),

    // ILMU ALAM
    StudyProgram(10, "Kimia", StudyProgramCluster.ILMU_ALAM, "Kimia", listOf("kim", "mtk_tl")),
    StudyProgram(11, "Ilmu atau Sains Kebumian (Geologi, Geofisika)", StudyProgramCluster.ILMU_ALAM, "Fisika; atau Matematika TL; atau keduanya", listOf("fis", "mtk_tl", "geo")),
    StudyProgram(12, "Ilmu atau Sains Kelautan (Oseanografi)", StudyProgramCluster.ILMU_ALAM, "Biologi", listOf("bio", "fis")),
    StudyProgram(13, "Biologi", StudyProgramCluster.ILMU_ALAM, "Biologi", listOf("bio", "kim")),
    StudyProgram(14, "Biofisika", StudyProgramCluster.ILMU_ALAM, "Fisika", listOf("fis", "bio", "mtk_tl")),
    StudyProgram(15, "Fisika", StudyProgramCluster.ILMU_ALAM, "Fisika", listOf("fis", "mtk_tl")),
    StudyProgram(16, "Astronomi", StudyProgramCluster.ILMU_ALAM, "Fisika; atau Matematika TL; atau keduanya", listOf("fis", "mtk_tl")),

    // ILMU FORMAL
    StudyProgram(17, "Komputer (Informatika, Sistem Informasi)", StudyProgramCluster.ILMU_FORMAL, "Matematika Tingkat Lanjut", listOf("mtk_tl", "inf")),
    StudyProgram(18, "Logika", StudyProgramCluster.ILMU_FORMAL, "Matematika Tingkat Lanjut", listOf("mtk_tl", "inf")),
    StudyProgram(19, "Matematika / Statistika / Aktuaria", StudyProgramCluster.ILMU_FORMAL, "Matematika Tingkat Lanjut", listOf("mtk_tl", "inf")),

    // ILMU TERAPAN
    StudyProgram(20, "Ilmu dan Sains Pertanian (Agroteknologi)", StudyProgramCluster.ILMU_TERAPAN, "Biologi", listOf("bio", "kim")),
    StudyProgram(21, "Peternakan", StudyProgramCluster.ILMU_TERAPAN, "Biologi", listOf("bio", "kim")),
    StudyProgram(22, "Ilmu atau Sains Perikanan", StudyProgramCluster.ILMU_TERAPAN, "Biologi", listOf("bio", "kim")),
    StudyProgram(23, "Arsitektur", StudyProgramCluster.ILMU_TERAPAN, "Matematika; atau Fisika; atau keduanya", listOf("fis", "mtk_tl")),
    StudyProgram(24, "Perencanaan Wilayah dan Kota (PWK)", StudyProgramCluster.ILMU_TERAPAN, "Ekonomi; atau Matematika; atau keduanya", listOf("geo", "mtk_tl", "eko")),
    StudyProgram(25, "Desain (DKV, Desain Produk, Desain Interior)", StudyProgramCluster.ILMU_TERAPAN, "Seni Budaya; atau Matematika; atau keduanya", listOf("mtk_tl", "inf", "vokasi_smk")),
    StudyProgram(26, "Ilmu atau Sains Akuntansi", StudyProgramCluster.ILMU_TERAPAN, "Ekonomi", listOf("eko", "mtk_tl")),
    StudyProgram(27, "Ilmu atau Sains Manajemen", StudyProgramCluster.ILMU_TERAPAN, "Ekonomi", listOf("eko", "sos")),
    StudyProgram(28, "Logistik", StudyProgramCluster.ILMU_TERAPAN, "Ekonomi", listOf("eko", "mtk_tl")),
    StudyProgram(29, "Administrasi Bisnis", StudyProgramCluster.ILMU_TERAPAN, "Ekonomi", listOf("eko", "sos")),
    StudyProgram(30, "Bisnis (Kewirausahaan)", StudyProgramCluster.ILMU_TERAPAN, "Ekonomi", listOf("eko", "pkwu")),
    StudyProgram(31, "Ilmu atau Sains Komunikasi", StudyProgramCluster.ILMU_TERAPAN, "Sosiologi", listOf("sos", "ing_tl", "indo_tl")),
    StudyProgram(32, "Pendidikan (Keguruan)", StudyProgramCluster.ILMU_TERAPAN, "Mata pelajaran pendukung yang relevan dengan prodi kependidikan", listOf("sos", "mtk_tl", "bio", "fis", "kim")),
    StudyProgram(33, "Teknik atau Rekayasa (Sipil, Mesin, Elektro, Industri)", StudyProgramCluster.ILMU_TERAPAN, "Fisika dan Matematika TL; atau Kimia dan Matematika TL", listOf("fis", "mtk_tl", "kim")),
    StudyProgram(34, "Ilmu atau Sains Lingkungan", StudyProgramCluster.ILMU_TERAPAN, "Biologi", listOf("bio", "kim", "geo")),
    StudyProgram(35, "Kehutanan", StudyProgramCluster.ILMU_TERAPAN, "Biologi", listOf("bio", "geo")),
    StudyProgram(36, "Kedokteran (Pendidikan Dokter)", StudyProgramCluster.ILMU_TERAPAN, "Biologi; atau Kimia; atau keduanya", listOf("bio", "kim", "fis")),
    StudyProgram(37, "Kedokteran Gigi", StudyProgramCluster.ILMU_TERAPAN, "Biologi; atau Kimia; atau keduanya", listOf("bio", "kim", "fis")),
    StudyProgram(38, "Kedokteran Hewan (Veteriner)", StudyProgramCluster.ILMU_TERAPAN, "Biologi; atau Kimia; atau keduanya", listOf("bio", "kim")),
    StudyProgram(39, "Ilmu Farmasi", StudyProgramCluster.ILMU_TERAPAN, "Biologi; atau Kimia; atau keduanya", listOf("kim", "bio", "mtk_tl")),
    StudyProgram(40, "Ilmu atau Sains Gizi", StudyProgramCluster.ILMU_TERAPAN, "Biologi; atau Kimia; atau keduanya", listOf("bio", "kim")),
    StudyProgram(41, "Kesehatan Masyarakat", StudyProgramCluster.ILMU_TERAPAN, "Biologi", listOf("bio", "sos")),
    StudyProgram(42, "Kebidanan", StudyProgramCluster.ILMU_TERAPAN, "Biologi", listOf("bio", "kim")),
    StudyProgram(43, "Keperawatan", StudyProgramCluster.ILMU_TERAPAN, "Biologi", listOf("bio", "sos")),
    StudyProgram(44, "Kesehatan (Teknologi Laboratorium Medis, Radiologi)", StudyProgramCluster.ILMU_TERAPAN, "Biologi", listOf("bio", "kim", "fis")),
    StudyProgram(45, "Ilmu atau Sains Informasi", StudyProgramCluster.ILMU_TERAPAN, "Matematika Tingkat Lanjut", listOf("mtk_tl", "inf")),
    StudyProgram(46, "Hukum", StudyProgramCluster.ILMU_TERAPAN, "Sosiologi; atau Pendidikan Pancasila; atau keduanya", listOf("sos", "sej_tl")),
    StudyProgram(47, "Ilmu atau Sains Militer / Akmil / Akpol", StudyProgramCluster.ILMU_TERAPAN, "Sosiologi", listOf("sos", "fis")),
    StudyProgram(48, "Urusan Publik (Administrasi Publik)", StudyProgramCluster.ILMU_TERAPAN, "Sosiologi", listOf("sos", "eko")),
    StudyProgram(49, "Ilmu Keolahragaan", StudyProgramCluster.ILMU_TERAPAN, "PJOK; atau Biologi; atau keduanya", listOf("bio")),
    StudyProgram(50, "Pariwisata / Hospitality", StudyProgramCluster.ILMU_TERAPAN, "Ekonomi", listOf("eko", "ing_tl", "ant")),
    StudyProgram(51, "Transportasi", StudyProgramCluster.ILMU_TERAPAN, "Matematika Tingkat Lanjut", listOf("mtk_tl", "fis")),
    StudyProgram(52, "Bioteknologi / Bioinformatika", StudyProgramCluster.ILMU_TERAPAN, "Biologi; atau Matematika; atau keduanya", listOf("bio", "kim", "inf")),
    StudyProgram(53, "Geografi / Sains Informasi Geografi", StudyProgramCluster.ILMU_TERAPAN, "Geografi; atau Matematika; atau keduanya", listOf("geo", "mtk_tl")),
    StudyProgram(54, "Informatika Medis / Kesehatan", StudyProgramCluster.ILMU_TERAPAN, "Biologi; atau Matematika TL; atau keduanya", listOf("inf", "bio", "mtk_tl")),
    StudyProgram(55, "Konservasi Sumber Daya Alam & Hutan", StudyProgramCluster.ILMU_TERAPAN, "Biologi", listOf("bio", "geo")),
    StudyProgram(56, "Teknologi Pangan / Hasil Pertanian", StudyProgramCluster.ILMU_TERAPAN, "Kimia; atau Biologi; atau keduanya", listOf("kim", "bio")),
    StudyProgram(57, "Sains Data", StudyProgramCluster.ILMU_TERAPAN, "Matematika Tingkat Lanjut", listOf("mtk_tl", "inf")),
    StudyProgram(58, "Sains Perkopian", StudyProgramCluster.ILMU_TERAPAN, "Biologi", listOf("bio", "kim", "pkwu")),
    StudyProgram(59, "Studi Humanitas", StudyProgramCluster.ILMU_TERAPAN, "Sosiologi", listOf("sos", "ant"))
  )

  // Contoh Paket Mapel Sekolah (Kesiapan Menu Sekolah)
  val samplePackages = listOf(
    SubjectPackage(
      id = "pkg_teknik",
      name = "Paket Teknik & Teknologi Rekayasa",
      targetFocus = "Teknik Mesin, Sipil, Elektro, Komputer, Arsitektur",
      subjectIds = listOf("mtk_tl", "fis", "kim", "inf"),
      totalJp = 20,
      description = "Mempersiapkan siswa untuk rumpun keteknikan dan ilmu rekayasa dengan pondasi kuat di Matematika Lanjut, Fisika, Kimia, dan Informatika."
    ),
    SubjectPackage(
      id = "pkg_kedokteran",
      name = "Paket Kedokteran & Ilmu Hayati",
      targetFocus = "Kedokteran, Kedokteran Gigi, Farmasi, Gizi, Keperawatan",
      subjectIds = listOf("bio", "kim", "fis", "sos"),
      totalJp = 20,
      description = "Kombinasi Biologi, Kimia, Fisika, ditambah Sosiologi untuk pemahaman empati sosial kemasyarakatan calon tenaga medis."
    ),
    SubjectPackage(
      id = "pkg_soshum",
      name = "Paket Sosial, Hukum & Kebijakan Publik",
      targetFocus = "Hukum, Hubungan Internasional, Sosiologi, Ilmu Komunikasi",
      subjectIds = listOf("sos", "eko", "sej_tl", "ing_tl"),
      totalJp = 20,
      description = "Fokus pendalaman masalah kemasyarakatan, analisis hukum, sejarah geopolitik internasional, dan kemahiran bahasa Inggris akademis."
    ),
    SubjectPackage(
      id = "pkg_bisnis",
      name = "Paket Bisnis, Akuntansi & Wirausaha",
      targetFocus = "Manajemen, Akuntansi, Bisnis Digital, Entrepreneurship",
      subjectIds = listOf("eko", "mtk_tl", "inf", "sos"),
      totalJp = 20,
      description = "Memadukan Ekonomi, Matematika Analitik untuk hitungan finansial, Informatika untuk sistem bisnis digital, dan Sosiologi."
    ),
    SubjectPackage(
      id = "pkg_seni_dkv",
      name = "Paket Seni, Desain & Komunikasi Visual",
      targetFocus = "Desain Komunikasi Visual (DKV), Animasi, Arsitektur, Desain Interior",
      subjectIds = listOf("indo_tl", "ing_tl", "inf", "vokasi_smk"),
      totalJp = 20,
      description = "Mengasah kreativitas komunikasi visual, literasi digital, animasi/desain terapan, dan kemahiran bahasa."
    )
  )

  // RIASEC 1 Questions (42 Pernyataan dari Lampiran 2 Buku Panduan Halaman 55)
  val riasec1Questions = listOf(
    Riasec1Question(1, "Aku suka mengulik peralatan mesin/elektronik", RiasecType.REALISTIC),
    Riasec1Question(2, "Aku suka mengerjakan puzzle / teka-teki logika", RiasecType.INVESTIGATIVE),
    Riasec1Question(3, "Aku suka bekerja mandiri dan teratur", RiasecType.CONVENTIONAL),
    Riasec1Question(4, "Aku suka bekerja sama dalam kelompok", RiasecType.SOCIAL),
    Riasec1Question(5, "Aku suka membuat target prestasi untuk diriku sendiri", RiasecType.ENTERPRISING),
    Riasec1Question(6, "Aku suka merapikan barang-barang (buku, alat tulis, kamar)", RiasecType.CONVENTIONAL),
    Riasec1Question(7, "Aku suka menyusun balok / LEGO atau merakit konstruksi", RiasecType.REALISTIC),
    Riasec1Question(8, "Aku suka membaca buku tentang seni, sastra, dan musik", RiasecType.ARTISTIC),
    Riasec1Question(9, "Aku suka mengerjakan hal-hal dengan instruksi yang jelas dan teratur", RiasecType.CONVENTIONAL),
    Riasec1Question(10, "Aku suka meyakinkan teman untuk mengikuti caraku atau ideku", RiasecType.ENTERPRISING),
    Riasec1Question(11, "Aku suka melakukan percobaan/eksperimen sains", RiasecType.INVESTIGATIVE),
    Riasec1Question(12, "Aku suka menjelaskan materi pelajaran kepada teman", RiasecType.SOCIAL),
    Riasec1Question(13, "Aku suka membantu orang lain memecahkan persoalan mereka", RiasecType.SOCIAL),
    Riasec1Question(14, "Aku suka memelihara binatang atau mengamati alam terbuka", RiasecType.REALISTIC),
    Riasec1Question(15, "Aku tidak berkeberatan bekerja melebihi waktu untuk hasil terbaik", RiasecType.ENTERPRISING),
    Riasec1Question(16, "Aku suka menjual sesuatu atau berdagang", RiasecType.ENTERPRISING),
    Riasec1Question(17, "Aku suka membuat karya seni atau tulisan kreatif", RiasecType.ARTISTIC),
    Riasec1Question(18, "Aku sangat menyukai sains dan mencari tahu hukum alam", RiasecType.INVESTIGATIVE),
    Riasec1Question(19, "Aku suka mendapatkan tantangan kompetitif yang baru", RiasecType.ENTERPRISING),
    Riasec1Question(20, "Aku suka menghibur dan memberi semangat kepada teman", RiasecType.SOCIAL),
    Riasec1Question(21, "Aku suka mencari tahu cara kerja suatu alat atau mesin", RiasecType.REALISTIC),
    Riasec1Question(22, "Aku suka merangkaikan, menyambung, atau merakit benda", RiasecType.REALISTIC),
    Riasec1Question(23, "Aku adalah orang yang penuh ide kreatif dan imajinatif", RiasecType.ARTISTIC),
    Riasec1Question(24, "Aku suka memperhatikan hal-hal mendetail dan teliti", RiasecType.CONVENTIONAL),
    Riasec1Question(25, "Aku suka merapikan catatan pelajaran atau lembar kerja", RiasecType.CONVENTIONAL),
    Riasec1Question(26, "Aku suka mencari tahu penyebab mendalam dari suatu kejadian", RiasecType.INVESTIGATIVE),
    Riasec1Question(27, "Aku suka memainkan alat musik atau bernyanyi", RiasecType.ARTISTIC),
    Riasec1Question(28, "Aku suka mempelajari kebudayaan berbagai daerah di dunia", RiasecType.SOCIAL),
    Riasec1Question(29, "Aku ingin membuka usaha atau bisnis sendiri suatu saat nanti", RiasecType.ENTERPRISING),
    Riasec1Question(30, "Aku suka memasak atau meracik hal-hal baru yang artistik", RiasecType.ARTISTIC),
    Riasec1Question(31, "Aku suka bermain peran/drama atau mengekspresikan karakter", RiasecType.ARTISTIC),
    Riasec1Question(32, "Aku suka mempraktikkan langsung hal-hal yang aku pelajari", RiasecType.REALISTIC),
    Riasec1Question(33, "Aku suka mendiskusikan hal-hal yang terjadi di sekitarku", RiasecType.SOCIAL),
    Riasec1Question(34, "Aku suka mengerjakan soal analisis matematika atau grafik data", RiasecType.INVESTIGATIVE),
    Riasec1Question(35, "Aku suka merapikan dan menata kamarku secara teratur", RiasecType.CONVENTIONAL),
    Riasec1Question(36, "Aku suka memimpin kelompok belajar atau kepengurusan kelas", RiasecType.ENTERPRISING),
    Riasec1Question(37, "Aku suka berkegiatan di luar ruangan (outdoor)", RiasecType.REALISTIC),
    Riasec1Question(38, "Aku suka menolong orang yang sedang membutuhkan bantuan", RiasecType.SOCIAL),
    Riasec1Question(39, "Aku suka menghitung dan meneliti angka-angka secara kritis", RiasecType.INVESTIGATIVE),
    Riasec1Question(40, "Aku suka berbicara di depan umum atau berpresentasi", RiasecType.ENTERPRISING),
    Riasec1Question(41, "Aku suka menggambar, membuat sketsa, atau mengedit visual", RiasecType.ARTISTIC),
    Riasec1Question(42, "Aku suka berkegiatan rapi di dalam ruangan dengan meja dan kursi", RiasecType.CONVENTIONAL)
  )

  // RIASEC 2 Skills (24 Pilihan Kata Kerja Keterampilan - Lampiran 2 Halaman 56-57)
  val riasec2Skills = listOf(
    Riasec2Skill(1, "Memperbaiki barang", RiasecType.REALISTIC),
    Riasec2Skill(2, "Mencari solusi", RiasecType.INVESTIGATIVE),
    Riasec2Skill(3, "Memberi saran", RiasecType.SOCIAL),
    Riasec2Skill(4, "Mengutak-atik barang", RiasecType.REALISTIC),
    Riasec2Skill(5, "Menari / Olah Tubuh", RiasecType.ARTISTIC),
    Riasec2Skill(6, "Mengelola acara", RiasecType.ENTERPRISING),
    Riasec2Skill(7, "Memahami orang lain", RiasecType.SOCIAL),
    Riasec2Skill(8, "Menulis cerita", RiasecType.ARTISTIC),
    Riasec2Skill(9, "Menganalisis masalah", RiasecType.INVESTIGATIVE),
    Riasec2Skill(10, "Bermusik", RiasecType.ARTISTIC),
    Riasec2Skill(11, "Membongkar-pasang barang", RiasecType.REALISTIC),
    Riasec2Skill(12, "Membereskan barang", RiasecType.CONVENTIONAL),
    Riasec2Skill(13, "Mengajar", RiasecType.SOCIAL),
    Riasec2Skill(14, "Mengurutkan data", RiasecType.CONVENTIONAL),
    Riasec2Skill(15, "Memimpin kelompok", RiasecType.ENTERPRISING),
    Riasec2Skill(16, "Membantu orang", RiasecType.SOCIAL),
    Riasec2Skill(17, "Menjual benda", RiasecType.ENTERPRISING),
    Riasec2Skill(18, "Menghitung", RiasecType.INVESTIGATIVE),
    Riasec2Skill(19, "Berpikir logis", RiasecType.INVESTIGATIVE),
    Riasec2Skill(20, "Membujuk orang", RiasecType.ENTERPRISING),
    Riasec2Skill(21, "Mengikuti aturan", RiasecType.CONVENTIONAL),
    Riasec2Skill(22, "Merakit benda", RiasecType.REALISTIC),
    Riasec2Skill(23, "Menggambar", RiasecType.ARTISTIC),
    Riasec2Skill(24, "Menjalankan rutin", RiasecType.CONVENTIONAL)
  )

  // FAQ & Ringkasan Bab Buku Panduan Kemendikbudristek Edisi Revisi 2024
  val guideSummary = listOf(
    GuideTopic(
      chapter = "Prinsip Dasar",
      title = "Berapa banyak mata pelajaran pilihan yang harus diambil siswa Fase F?",
      content = "Peserta didik memilih 4 sampai 5 mata pelajaran pilihan dengan total alokasi 20 sampai 25 Jam Pelajaran (JP) per minggu (setara 180 JP per tahun di Kelas XI dan 160 JP di Kelas XII). Satuan pendidikan wajib membuka paling sedikit 7 (tujuh) mata pelajaran pilihan."
    ),
    GuideTopic(
      chapter = "Prinsip Seleksi PTN",
      title = "Bagaimana mata pelajaran pilihan berpengaruh ke Seleksi Masuk PTN (SNBP)?",
      content = "Pada SNBP (Seleksi Nasional Berdasarkan Prestasi), penilaian terdiri dari:\n1. Komponen Pertama (minimal 50%): Rata-rata nilai rapor seluruh mata pelajaran umum dan pilihan secara holistik.\n2. Komponen Kedua (maksimal 50%): Nilai rapor maksimal 2 mata pelajaran pendukung prodi yang dituju (merujuk Kepmendikbudristek No. 345/M/2022), prestasi, dan/atau portofolio."
    ),
    GuideTopic(
      chapter = "Fleksibilitas Siswa",
      title = "Apakah mata pelajaran pilihan boleh diganti jika merasa tidak cocok?",
      content = "Ya! Sesuai Panduan Bab 4, peserta didik diperbolehkan mengganti mata pelajaran pilihan paling lambat di KELAS XI SEMESTER 2 sesuai permintaannya dan penilaian ulang oleh sekolah/BK. Sekolah wajib memfasilitasi program matrikulasi untuk mengejar capaian pembelajaran."
    ),
    GuideTopic(
      chapter = "Konseling & Dialog",
      title = "Bagaimana jika terjadi perbedaan pilihan antara anak dan orang tua?",
      content = "Seperti studi kasus Dian pada Bab 1 buku panduan, pemilihan mapel harus melalui proses dialog terbuka dan konseling bersama Guru BK. Guru BK memfasilitasi temu orang tua dan murid untuk mendengarkan aspirasi kedua belah pihak, memoderasi, dan mencari kesepakatan bersama yang membahagiakan murid."
    ),
    GuideTopic(
      chapter = "Kesiapan Sekolah",
      title = "4 Kondisi Kesiapan Satuan Pendidikan (Kondisi A, B, C, D)",
      content = "Kondisi A: Siswa memilih bebas tanpa batas kuota, sekolah berkolaborasi antarlembaga.\nKondisi B: Survei awal lalu sekolah menyediakan menu berdasarkan aspirasi profesi.\nKondisi C: Menu mapel pilihan dengan kuota terbatas disesuaikan ketersediaan guru dan ruangan.\nKondisi D: Layanan bimbingan dilakukan pendidik yang ditugaskan jika guru BK belum mencukupi."
    )
  )
}

data class GuideTopic(
  val chapter: String,
  val title: String,
  val content: String
)
