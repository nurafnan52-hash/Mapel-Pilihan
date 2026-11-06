package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.GuidanceData

@Composable
fun GuideInfoScreen(
  modifier: Modifier = Modifier
) {
  var selectedTab by remember { mutableStateOf(0) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("guide_info_screen"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(14.dp)
  ) {
    // Header
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.MenuBook,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary
            )
            Text(
              text = "Pusat Panduan Kurikulum Merdeka",
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp,
              color = MaterialTheme.colorScheme.onPrimaryContainer
            )
          }

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = "Rangkuman lengkap regulasi, prinsip bimbingan BK, aturan penggantian mapel, dan persiapan SNBP / PTKL berdasarkan buku Panduan Edisi Revisi 2024 Kemendikbudristek.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f),
            lineHeight = 17.sp
          )

          Spacer(modifier = Modifier.height(12.dp))

          // Tab Bar (Ringkasan Bab vs Tanya Jawab FAQ)
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            FilterChip(
              selected = selectedTab == 0,
              onClick = { selectedTab = 0 },
              label = { Text("Struktur 6 Bab Panduan", fontSize = 12.sp) }
            )
            FilterChip(
              selected = selectedTab == 1,
              onClick = { selectedTab = 1 },
              label = { Text("Tanya Jawab (FAQ)", fontSize = 12.sp) }
            )
          }
        }
      }
    }

    if (selectedTab == 0) {
      // 6 Chapters Accordion Cards
      item {
        ChapterCard(
          chapterNum = "Bab 1",
          title = "Pendahuluan & Filosofi Pemilihan",
          summary = "Pemilihan mapel berpusat pada minat, bakat, dan kemampuan siswa. Kesalahan memilih mapel bukanlah kegagalan fatal, melainkan bagian eksplorasi kematangan karier (studi kasus Dian calon Arsitek). Beban intrakurikuler Fase F: mapel umum wajib + mapel pilihan 20-25 JP per minggu.",
          keyPoints = listOf(
            "Minat: Minat karier jangka panjang siswa setelah SMA.",
            "Bakat: Potensi bawaan akademik maupun non-akademik.",
            "Kemampuan: Kapasitas intelektual dan performa belajar yang telah terbukti.",
            "Satuan pendidikan wajib membuka paling sedikit 7 mapel pilihan."
          )
        )
      }

      item {
        ChapterCard(
          chapterNum = "Bab 2",
          title = "Persiapan Satuan Pendidikan",
          summary = "Sekolah menganalisis kesiapan ruang kelas, ketersediaan pendidik, serta memilih salah satu mekanisme pengorganisasian (Kondisi A, B, C, atau D).",
          keyPoints = listOf(
            "Kondisi A: Siswa memilih bebas, sekolah berkolaborasi antarlembaga.",
            "Kondisi B: Survei awal -> menu berdasarkan aspirasi profesi/prodi.",
            "Kondisi C: Menu terbatas kuota sesuai ketersediaan guru & ruang.",
            "Kondisi D: Layanan bimbingan oleh pendidik yang ditugaskan jika guru BK belum cukup.",
            "Sistem pembelajaran: Kelas tetap (fixed class) atau kelas dinamis (moving class)."
          )
        )
      }

      item {
        ChapterCard(
          chapterNum = "Bab 3",
          title = "Proses Bimbingan & Pemilihan",
          summary = "Alur bimbingan mencakup Layanan Dasar (klasikal, kelompok) dan Layanan Responsif (konseling individu). Melibatkan sinergi guru BK, wali kelas, guru mapel, serta orang tua.",
          keyPoints = listOf(
            "Instrumen pemetaan: Tes RIASEC John Holland (Lampiran 2).",
            "Dialog orang tua: Guru BK memoderasi jika ada perbedaan cita-cita anak vs harapan ortu.",
            "Pengolahan data: Verifikasi kelengkapan, validasi kuota rombel (2-36 siswa per rombel)."
          )
        )
      }

      item {
        ChapterCard(
          chapterNum = "Bab 4",
          title = "Pendampingan & Aturan Penggantian Mapel",
          summary = "Pendampingan pasca penetapan dan aturan tegas mengenai pergantian mata pelajaran pilihan.",
          keyPoints = listOf(
            "Batas waktu penggantian mapel: Paling lambat KELAS XI SEMESTER 2.",
            "Harus melalui penilaian ulang minat bakat oleh satuan pendidikan.",
            "Sekolah wajib menyediakan program matrikulasi untuk mengejar capaian pembelajaran mapel baru."
          )
        )
      }

      item {
        ChapterCard(
          chapterNum = "Bab 5",
          title = "Refleksi & Evaluasi Sekolah",
          summary = "Evaluasi berkala per kegiatan, per semester, dan per tahun untuk memastikan keterlaksanaan kurikulum yang berpusat pada siswa.",
          keyPoints = listOf(
            "Mengevaluasi apakah ada mapel yang dipilih/dihindari hanya karena suka/tidak suka pada guru pengampunya.",
            "Tindak lanjut perbaikan pengorganisasian kelas dan penjadwalan."
          )
        )
      }

      item {
        ChapterCard(
          chapterNum = "Bab 6",
          title = "Menyiapkan Siswa Selepas SMA",
          summary = "Panduan jalur kelanjutan studi (PTN, SNBP, SNBT, Mandiri, PTKL Kedinasan) serta kesiapan bekerja/wirausaha dalam era VUCA.",
          keyPoints = listOf(
            "SNBP: 50% rata-rata seluruh mapel + 50% mapel pendukung prodi & prestasi.",
            "SNBT: Tes potensi kognitif & penalaran matematika/literasi (UTBK).",
            "PTKL: 125 perguruan tinggi kedinasan di bawah 24 kementerian/lembaga (PKN STAN, IPDN, Poltekip/Poltekim, STIS, dll.).",
            "Dunia VUCA: Dihadapi dengan Vision, Understanding, Clarity, Agility."
          )
        )
      }
    } else {
      // FAQ Items
      items(GuidanceData.guideSummary) { faq ->
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Surface(
              shape = RoundedCornerShape(4.dp),
              color = MaterialTheme.colorScheme.primaryContainer
            ) {
              Text(
                text = faq.chapter,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = faq.title,
              fontWeight = FontWeight.Bold,
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
              text = faq.content,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              lineHeight = 17.sp
            )
          }
        }
      }
    }
  }
}

@Composable
fun ChapterCard(
  chapterNum: String,
  title: String,
  summary: String,
  keyPoints: List<String>
) {
  var isExpanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    modifier = Modifier
      .fillMaxWidth()
      .clickable { isExpanded = !isExpanded }
  ) {
    Column(modifier = Modifier.padding(14.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Surface(
            shape = RoundedCornerShape(6.dp),
            color = MaterialTheme.colorScheme.primary
          ) {
            Text(
              text = chapterNum,
              color = Color.White,
              fontWeight = FontWeight.Bold,
              fontSize = 11.sp,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
          }
          Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
          )
        }

        Icon(
          imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.outline
        )
      }

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = summary,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        lineHeight = 16.sp
      )

      AnimatedVisibility(visible = isExpanded) {
        Column(
          modifier = Modifier.padding(top = 10.dp),
          verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = "Poin-poin Penting Regulasi:",
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.primary
          )
          keyPoints.forEach { pt ->
            Row(
              verticalAlignment = Alignment.Top,
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              modifier = Modifier.padding(vertical = 1.dp)
            ) {
              Text("•", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
              Text(
                text = pt,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 15.sp
              )
            }
          }
        }
      }
    }
  }
}
