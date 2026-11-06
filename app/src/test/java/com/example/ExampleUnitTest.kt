package com.example

import com.example.data.local.GuidanceData
import com.example.data.model.RiasecType
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {

  @Test
  fun testOfficialStudyProgramsCount() {
    // Sesuai Kepmendikbudristek No. 345/M/2022 terdapat 59 kelompok prodi PTN
    assertEquals(59, GuidanceData.studyPrograms.size)
  }

  @Test
  fun testElectiveSubjectsHours() {
    // Mapel pilihan umum berbobot 5 JP, PKWU 2 JP
    val pkwu = GuidanceData.electiveSubjects.find { it.id == "pkwu" }
    assertNotNull(pkwu)
    assertEquals(2, pkwu?.jpPerWeek)

    val fisika = GuidanceData.electiveSubjects.find { it.id == "fis" }
    assertNotNull(fisika)
    assertEquals(5, fisika?.jpPerWeek)
  }

  @Test
  fun testRiasecQuestionsCount() {
    // Sesuai Lampiran 2 halaman 55 terdapat 42 butir kuesioner RIASEC 1
    assertEquals(42, GuidanceData.riasec1Questions.size)
    // Sesuai Lampiran 2 halaman 56-57 terdapat 24 butir keterampilan RIASEC 2
    assertEquals(24, GuidanceData.riasec2Skills.size)
  }
}
