package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.GuidanceData
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.SelectionViewModel

@Composable
fun HomeScreen(
  viewModel: SelectionViewModel,
  onNavigate: (AppScreen) -> Unit,
  modifier: Modifier = Modifier
) {
  val studentProfile by viewModel.studentProfile.collectAsState()
  val dreamProfession by viewModel.dreamProfession.collectAsState()
  val targetStudyProgram by viewModel.targetStudyProgram.collectAsState()
  val selectedSubjectIds by viewModel.selectedSubjectIds.collectAsState()
  val riasecResult by viewModel.riasecResult.collectAsState()

  val totalJp = selectedSubjectIds.sumOf { id ->
    GuidanceData.electiveSubjects.find { it.id == id }?.jpPerWeek ?: 0
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("home_screen_content"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Top Hero Banner
    item {
      Card(
        modifier = Modifier
          .fillMaxWidth()
          .testTag("hero_banner_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.primaryContainer
        )
      ) {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(
              Brush.linearGradient(
                colors = listOf(
                  MaterialTheme.colorScheme.primary.copy(alpha = 0.95f),
                  MaterialTheme.colorScheme.secondary.copy(alpha = 0.90f)
                )
              )
            )
            .padding(20.dp)
        ) {
          Column {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White.copy(alpha = 0.2f)
              ) {
                Text(
                  text = "KURIKULUM MERDEKA • EDISI REVISI 2024",
                  color = Color.White,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
              text = "Panduan Pemilihan Mapel Pilihan Fase F",
              color = Color.White,
              fontSize = 20.sp,
              fontWeight = FontWeight.ExtraBold,
              lineHeight = 26.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
              text = "Membantu murid kelas X SMA menentukan 4-5 mata pelajaran pilihan sesuai minat, bakat, dan rencana karier.",
              color = Color.White.copy(alpha = 0.9f),
              fontSize = 13.sp,
              lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Student Status Summary Strip
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = Color.White.copy(alpha = 0.15f),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column {
                  Text(
                    text = "Siswa: ${studentProfile.fullName}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                  )
                  Text(
                    text = "Cita-cita: $dreamProfession",
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 12.sp
                  )
                }

                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = if (totalJp in 20..25) Color(0xFF10B981) else Color(0xFFF59E0B)
                ) {
                  Text(
                    text = "$totalJp JP Terpilih",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                  )
                }
              }
            }
          }
        }
      }
    }

    // Step-by-Step Flow Cards
    item {
      Text(
        text = "Alur 4 Langkah Pemilihan Mapel",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
      )
    }

    item {
      StepActionCard(
        stepNumber = "1",
        title = "Eksplorasi Minat & Bakat (RIASEC)",
        subtitle = if (riasecResult != null) "Selesai: Holland Code ${riasecResult?.hollandCode}" else "Instrumen tes Holland RIASEC resmi Lampiran 2",
        icon = Icons.Default.Psychology,
        statusBadge = if (riasecResult != null) "Tuntas" else "Mulai Tes",
        isCompleted = riasecResult != null,
        onClick = { onNavigate(AppScreen.RIASEC) }
      )
    }

    item {
      StepActionCard(
        stepNumber = "2",
        title = "Katalog 59 Prodi PTN & Mapel Pendukung",
        subtitle = "Cek mata pelajaran pendukung SNBP (Kepmen 345/M/2022)",
        icon = Icons.Default.School,
        statusBadge = targetStudyProgram?.name ?: "Pilih Prodi",
        isCompleted = targetStudyProgram != null,
        onClick = { onNavigate(AppScreen.PRODI) }
      )
    }

    item {
      StepActionCard(
        stepNumber = "3",
        title = "Simulasi & Pemilihan Mapel Fase F",
        subtitle = "Pilih 4-5 mapel (20-25 JP) + 2 mapel cadangan",
        icon = Icons.Default.Assignment,
        statusBadge = "$totalJp / 20-25 JP",
        isCompleted = totalJp in 20..25,
        onClick = { onNavigate(AppScreen.SUBJECTS) }
      )
    }

    item {
      StepActionCard(
        stepNumber = "4",
        title = "Formulir Resmi Lampiran 2",
        subtitle = "Formulir pernyataan siswa & persetujuan orang tua / BK",
        icon = Icons.Default.Description,
        statusBadge = "Lihat Form",
        isCompleted = false,
        onClick = { onNavigate(AppScreen.OFFICIAL_FORM) }
      )
    }

    // Official Rules Highlights
    item {
      Text(
        text = "Ketentuan Penting Kurikulum Merdeka",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
      )
    }

    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(
          modifier = Modifier.padding(16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          RuleItem(
            icon = Icons.Default.Schedule,
            title = "Beban Belajar: 20 - 25 JP per Minggu",
            desc = "Setiap mapel pilihan berbobot 5 JP/minggu, kecuali PKWU (2 JP/minggu). Siswa mengambil 4 sampai 5 mapel."
          )
          HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
          RuleItem(
            icon = Icons.Default.SwapHoriz,
            title = "Batas Penggantian Mapel: Kelas XI Sem 2",
            desc = "Siswa dapat mengganti mapel paling lambat akhir semester 2 kelas XI dengan bimbingan Guru BK dan program matrikulasi."
          )
          HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
          RuleItem(
            icon = Icons.Default.Star,
            title = "SNBP PTN: 50% Rapor + 50% Mapel Pendukung",
            desc = "Nilai rapor rata-rata seluruh mapel berbobot minimal 50%, dan komponen minat bakat/mapel pendukung berbobot hingga 50%."
          )
        }
      }
    }

    // Inspiration Case Study: Dian
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            Icon(
              imageVector = Icons.Default.Lightbulb,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.tertiary
            )
            Text(
              text = "Studi Kasus Dian (Bab 1 Panduan)",
              style = MaterialTheme.typography.titleSmall,
              fontWeight = FontWeight.Bold
            )
          }

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "Dian bercita-cita menjadi Arsitek dan suka menggambar, namun sempat ragu dengan Matematika/Fisika. Di sisi lain, sang Ayah menyarankan masuk Polwan dan Ibu menyarankan sanggar seni.\n\nSolusi Kurikulum Merdeka: Melalui bimbingan BK dan dialog keluarga, Dian mengambil Matematika TL, Fisika, Informatika, dan Seni/Bahasa. Kesalahan pilihan bukanlah kegagalan fatal, melainkan proses kematangan karier!",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 18.sp
          )

          Spacer(modifier = Modifier.height(10.dp))

          Button(
            onClick = { onNavigate(AppScreen.GUIDE_INFO) },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
          ) {
            Text("Pelajari Buku Panduan", fontSize = 12.sp)
          }
        }
      }
    }
  }
}

@Composable
fun StepActionCard(
  stepNumber: String,
  title: String,
  subtitle: String,
  icon: ImageVector,
  statusBadge: String,
  isCompleted: Boolean,
  onClick: () -> Unit
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clickable(onClick = onClick)
      .testTag("step_card_$stepNumber"),
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(44.dp)
          .clip(CircleShape)
          .background(
            if (isCompleted) MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
            else MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
          ),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = if (isCompleted) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary,
          modifier = Modifier.size(24.dp)
        )
      }

      Spacer(modifier = Modifier.width(12.dp))

      Column(modifier = Modifier.weight(1f)) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          Surface(
            shape = RoundedCornerShape(4.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
          ) {
            Text(
              text = "Langkah $stepNumber",
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              color = MaterialTheme.colorScheme.primary,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }

          if (isCompleted) {
            Surface(
              shape = RoundedCornerShape(4.dp),
              color = Color(0xFF10B981).copy(alpha = 0.15f)
            ) {
              Text(
                text = "✓ Selesai",
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF059669),
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(2.dp))

        Text(
          text = title,
          fontWeight = FontWeight.SemiBold,
          fontSize = 14.sp,
          color = MaterialTheme.colorScheme.onSurface
        )

        Text(
          text = subtitle,
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          maxLines = 1
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
        contentDescription = "Buka Langkah $stepNumber",
        tint = MaterialTheme.colorScheme.outline
      )
    }
  }
}

@Composable
fun RuleItem(
  icon: ImageVector,
  title: String,
  desc: String
) {
  Row(
    verticalAlignment = Alignment.Top,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = MaterialTheme.colorScheme.primary,
      modifier = Modifier.size(20.dp)
    )
    Column {
      Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.onSurface
      )
      Spacer(modifier = Modifier.height(2.dp))
      Text(
        text = desc,
        fontSize = 12.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        lineHeight = 16.sp
      )
    }
  }
}
