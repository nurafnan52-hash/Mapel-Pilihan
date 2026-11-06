package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.GuidanceData
import com.example.data.model.ElectiveSubject
import com.example.data.model.SubjectGroup
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.SelectionViewModel

@Composable
fun SubjectSelectionScreen(
  viewModel: SelectionViewModel,
  onNavigate: (AppScreen) -> Unit,
  modifier: Modifier = Modifier
) {
  val selectedSubjectIds by viewModel.selectedSubjectIds.collectAsState()
  val backupSubjectIds by viewModel.backupSubjectIds.collectAsState()
  val targetStudyProgram by viewModel.targetStudyProgram.collectAsState()

  var selectedGroupFilter by remember { mutableStateOf<SubjectGroup?>(null) }
  var errorSnackbar by remember { mutableStateOf<String?>(null) }
  var showPresetDialog by remember { mutableStateOf(false) }

  val filteredSubjects = remember(selectedGroupFilter) {
    if (selectedGroupFilter == null) GuidanceData.electiveSubjects
    else GuidanceData.electiveSubjects.filter { it.group == selectedGroupFilter }
  }

  val totalJp = selectedSubjectIds.sumOf { id ->
    GuidanceData.electiveSubjects.find { it.id == id }?.jpPerWeek ?: 0
  }

  val isValidJp = totalJp in 20..25

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("subject_selection_screen"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // JP Status Header Card
    item {
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
          containerColor = if (isValidJp) MaterialTheme.colorScheme.primaryContainer
          else MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.8f)
        ),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("jp_status_card")
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "Simulasi Beban Jam Pelajaran",
                fontSize = 13.sp,
                color = if (isValidJp) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onErrorContainer
              )
              Row(verticalAlignment = Alignment.Bottom) {
                Text(
                  text = "$totalJp",
                  fontSize = 28.sp,
                  fontWeight = FontWeight.ExtraBold,
                  color = if (isValidJp) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                )
                Text(
                  text = " / 20 - 25 JP per minggu",
                  fontSize = 13.sp,
                  fontWeight = FontWeight.Medium,
                  modifier = Modifier.padding(bottom = 4.dp, start = 4.dp),
                  color = if (isValidJp) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onErrorContainer
                )
              }
            }

            Surface(
              shape = RoundedCornerShape(8.dp),
              color = if (isValidJp) Color(0xFF10B981) else MaterialTheme.colorScheme.error
            ) {
              Text(
                text = if (isValidJp) "✓ Sesuai Aturan" else "Belum Sesuai",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))

          LinearProgressIndicator(
            progress = { (totalJp / 25f).coerceIn(0f, 1f) },
            modifier = Modifier
              .fillMaxWidth()
              .height(8.dp)
              .clip(RoundedCornerShape(4.dp)),
            color = if (isValidJp) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
            trackColor = Color.White.copy(alpha = 0.4f)
          )

          Spacer(modifier = Modifier.height(10.dp))

          Text(
            text = when {
              totalJp < 20 -> "Beban belajar minimal adalah 20 JP (4 mapel pilihan @5 JP). Silakan tambah mapel pilihan."
              totalJp == 20 -> "Kombinasi ideal 20 JP: 4 mata pelajaran pilihan utama @5 JP."
              totalJp == 22 -> "Kombinasi 22 JP: 4 mata pelajaran @5 JP + 1 Prakarya (PKWU) 2 JP."
              totalJp == 25 -> "Kombinasi maksimal 25 JP: 5 mata pelajaran pilihan utama @5 JP."
              else -> "Beban belajar melebihi batas maksimal 25 JP. Kurangi mata pelajaran."
            },
            fontSize = 12.sp,
            color = if (isValidJp) MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.9f) else MaterialTheme.colorScheme.onErrorContainer,
            lineHeight = 16.sp
          )
        }
      }
    }

    // Target Study Program Reminder
    if (targetStudyProgram != null) {
      item {
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Icon(
              imageVector = Icons.Default.School,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary,
              modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "Target Prodi: ${targetStudyProgram?.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
              )
              Text(
                text = "Mapel Pendukung SNBP: ${targetStudyProgram?.supportingSubjectsText}",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
              )
            }
          }
        }
      }
    }

    // Quick Actions: Preset Package & Reset
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        OutlinedButton(
          onClick = { showPresetDialog = true },
          modifier = Modifier.testTag("btn_show_preset_packages")
        ) {
          Icon(Icons.Default.DashboardCustomize, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text("Paket Menu Sekolah", fontSize = 12.sp)
        }

        TextButton(
          onClick = { viewModel.clearSelectedSubjects() },
          colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
        ) {
          Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(4.dp))
          Text("Reset Pilihan", fontSize = 12.sp)
        }
      }
    }

    // Selected Subjects Chips
    item {
      Text(
        text = "Mata Pelajaran Pilihan Utama (${selectedSubjectIds.size} / maks 5)",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
      )
    }

    item {
      if (selectedSubjectIds.isEmpty()) {
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = "Belum ada mapel pilihan utama yang dipilih. Silakan pilih dari daftar di bawah.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(14.dp)
          )
        }
      } else {
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
          selectedSubjectIds.forEach { id ->
            val subject = GuidanceData.electiveSubjects.find { it.id == id }
            if (subject != null) {
              Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)),
                modifier = Modifier.fillMaxWidth()
              ) {
                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                  ) {
                    Text(
                      text = "${subject.jpPerWeek} JP",
                      fontSize = 11.sp,
                      fontWeight = FontWeight.Bold,
                      color = MaterialTheme.colorScheme.primary,
                      modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                  }
                  Spacer(modifier = Modifier.width(10.dp))
                  Column(modifier = Modifier.weight(1f)) {
                    Text(
                      text = subject.name,
                      fontWeight = FontWeight.Bold,
                      fontSize = 13.sp
                    )
                    Text(
                      text = subject.group.displayName,
                      fontSize = 11.sp,
                      color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                  }
                  IconButton(
                    onClick = { viewModel.toggleMainSubject(subject.id) }
                  ) {
                    Icon(
                      imageVector = Icons.Default.Close,
                      contentDescription = "Hapus ${subject.name}",
                      tint = MaterialTheme.colorScheme.error,
                      modifier = Modifier.size(18.dp)
                    )
                  }
                }
              }
            }
          }
        }
      }
    }

    // Backup Subjects (Cadangan)
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Mata Pelajaran Cadangan (${backupSubjectIds.size} / maks 2)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = "Antisipasi bila kuota rombel mapel utama terpenuhi (Lampiran 2)",
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }

    item {
      if (backupSubjectIds.isEmpty()) {
        Surface(
          shape = RoundedCornerShape(10.dp),
          color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = "Pilih 1 atau 2 mata pelajaran cadangan.",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(14.dp)
          )
        }
      } else {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          backupSubjectIds.forEach { id ->
            val subject = GuidanceData.electiveSubjects.find { it.id == id }
            if (subject != null) {
              AssistChip(
                onClick = { viewModel.toggleBackupSubject(subject.id) },
                label = { Text("${subject.name} (${subject.jpPerWeek} JP)") },
                trailingIcon = { Icon(Icons.Default.Close, contentDescription = null, modifier = Modifier.size(14.dp)) }
              )
            }
          }
        }
      }
    }

    // Subject Catalog Header & Filter Chips
    item {
      Text(
        text = "Katalog Mata Pelajaran Pilihan Resmi",
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold
      )
    }

    item {
      LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
          FilterChip(
            selected = selectedGroupFilter == null,
            onClick = { selectedGroupFilter = null },
            label = { Text("Semua (${GuidanceData.electiveSubjects.size})", fontSize = 12.sp) }
          )
        }
        items(SubjectGroup.entries) { group ->
          FilterChip(
            selected = selectedGroupFilter == group,
            onClick = { selectedGroupFilter = if (selectedGroupFilter == group) null else group },
            label = { Text(group.code, fontSize = 12.sp) }
          )
        }
      }
    }

    // List of All Elective Subjects
    items(filteredSubjects, key = { it.id }) { subject ->
      val isMainSelected = selectedSubjectIds.contains(subject.id)
      val isBackupSelected = backupSubjectIds.contains(subject.id)
      val isSupportingTarget = targetStudyProgram?.recommendedElectiveIds?.contains(subject.id) == true

      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
          containerColor = when {
            isMainSelected -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
            isBackupSelected -> MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.45f)
            else -> MaterialTheme.colorScheme.surface
          }
        ),
        border = when {
          isMainSelected -> androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
          isBackupSelected -> androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.secondary)
          else -> null
        },
        modifier = Modifier
          .fillMaxWidth()
          .testTag("elective_subject_card_${subject.id}")
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
              ) {
                Surface(
                  shape = RoundedCornerShape(4.dp),
                  color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                  Text(
                    text = subject.group.code,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                  )
                }

                if (isSupportingTarget) {
                  Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color(0xFFFEF3C7)
                  ) {
                    Text(
                      text = "★ Pendukung Prodi Target",
                      fontSize = 10.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFFB45309),
                      modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                  }
                }
              }

              Spacer(modifier = Modifier.height(4.dp))

              Text(
                text = subject.name,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
              )
            }

            Surface(
              shape = RoundedCornerShape(6.dp),
              color = MaterialTheme.colorScheme.secondaryContainer
            ) {
              Text(
                text = "${subject.jpPerWeek} JP/mgg",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = subject.description,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = 16.sp
          )

          Spacer(modifier = Modifier.height(10.dp))

          // Action Buttons: Pilih Utama / Cadangan
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
          ) {
            // Backup button
            OutlinedButton(
              onClick = {
                val err = viewModel.toggleBackupSubject(subject.id)
                if (err != null) errorSnackbar = err
              },
              contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
              colors = ButtonDefaults.outlinedButtonColors(
                contentColor = if (isBackupSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onSurface
              ),
              modifier = Modifier.testTag("btn_backup_${subject.id}")
            ) {
              Icon(
                imageVector = if (isBackupSelected) Icons.Default.Check else Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(14.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(if (isBackupSelected) "Cadangan ✓" else "+ Cadangan", fontSize = 11.sp)
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Main choice button
            Button(
              onClick = {
                val err = viewModel.toggleMainSubject(subject.id)
                if (err != null) errorSnackbar = err
              },
              contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
              colors = ButtonDefaults.buttonColors(
                containerColor = if (isMainSelected) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
              ),
              modifier = Modifier.testTag("btn_main_${subject.id}")
            ) {
              Icon(
                imageVector = if (isMainSelected) Icons.Default.Check else Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(14.dp)
              )
              Spacer(modifier = Modifier.width(4.dp))
              Text(if (isMainSelected) "Hapus Utama" else "Pilih Utama", fontSize = 11.sp)
            }
          }
        }
      }
    }

    // Bottom Navigation to Official Form
    item {
      Spacer(modifier = Modifier.height(8.dp))
      Button(
        onClick = { onNavigate(AppScreen.OFFICIAL_FORM) },
        enabled = selectedSubjectIds.isNotEmpty(),
        modifier = Modifier
          .fillMaxWidth()
          .height(50.dp)
          .testTag("btn_proceed_to_form"),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
      ) {
        Text("Lanjut ke Formulir Resmi Lampiran 2", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(8.dp))
        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
      }
    }
  }

  // Preset Packages Dialog
  if (showPresetDialog) {
    AlertDialog(
      onDismissRequest = { showPresetDialog = false },
      title = {
        Text("Paket Menu Sekolah (Inspirasi Panduan)")
      },
      text = {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          items(GuidanceData.samplePackages) { pkg ->
            Card(
              shape = RoundedCornerShape(10.dp),
              colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
              modifier = Modifier
                .fillMaxWidth()
                .clickable {
                  viewModel.applyPackage(pkg)
                  showPresetDialog = false
                }
            ) {
              Column(modifier = Modifier.padding(12.dp)) {
                Text(text = pkg.name, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                Text(text = "Target: ${pkg.targetFocus}", fontSize = 11.sp, color = MaterialTheme.colorScheme.primary)
                Text(text = pkg.description, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                  text = "Mapel: " + pkg.subjectIds.mapNotNull { id ->
                    GuidanceData.electiveSubjects.find { it.id == id }?.shortName
                  }.joinToString(", "),
                  fontWeight = FontWeight.SemiBold,
                  fontSize = 11.sp
                )
              }
            }
          }
        }
      },
      confirmButton = {
        TextButton(onClick = { showPresetDialog = false }) {
          Text("Tutup")
        }
      }
    )
  }

  // Error Snackbar
  if (errorSnackbar != null) {
    Snackbar(
      modifier = Modifier.padding(16.dp),
      action = {
        TextButton(onClick = { errorSnackbar = null }) {
          Text("OK", color = Color.White)
        }
      }
    ) {
      Text(errorSnackbar ?: "")
    }
  }
}
