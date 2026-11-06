package com.example.ui.screens

import androidx.compose.foundation.background
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
import com.example.data.model.StudyProgram
import com.example.data.model.StudyProgramCluster
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.SelectionViewModel

@Composable
fun StudyProgramCatalogScreen(
  viewModel: SelectionViewModel,
  onNavigate: (AppScreen) -> Unit,
  modifier: Modifier = Modifier
) {
  var searchQuery by remember { mutableStateOf("") }
  var selectedCluster by remember { mutableStateOf<StudyProgramCluster?>(null) }
  val targetStudyProgram by viewModel.targetStudyProgram.collectAsState()

  val filteredPrograms = remember(searchQuery, selectedCluster) {
    GuidanceData.studyPrograms.filter { prog ->
      val matchesSearch = searchQuery.isBlank() ||
        prog.name.contains(searchQuery, ignoreCase = true) ||
        prog.supportingSubjectsText.contains(searchQuery, ignoreCase = true)
      val matchesCluster = selectedCluster == null || prog.cluster == selectedCluster
      matchesSearch && matchesCluster
    }
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .testTag("prodi_catalog_screen")
  ) {
    // Header & Search Bar
    Surface(
      color = MaterialTheme.colorScheme.surface,
      tonalElevation = 2.dp,
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Text(
          text = "Katalog 59 Program Studi PTN",
          style = MaterialTheme.typography.titleMedium,
          fontWeight = FontWeight.Bold
        )
        Text(
          text = "Mata pelajaran pendukung resmi SNBP (Kepmendikbudristek No. 345/M/2022)",
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
          value = searchQuery,
          onValueChange = { searchQuery = it },
          placeholder = { Text("Cari program studi atau mapel pendukung...") },
          leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Cari") },
          trailingIcon = {
            if (searchQuery.isNotEmpty()) {
              IconButton(onClick = { searchQuery = "" }) {
                Icon(Icons.Default.Close, contentDescription = "Hapus")
              }
            }
          },
          singleLine = true,
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("search_prodi_input")
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Cluster Filter Chips
        LazyRow(
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          item {
            FilterChip(
              selected = selectedCluster == null,
              onClick = { selectedCluster = null },
              label = { Text("Semua (${GuidanceData.studyPrograms.size})", fontSize = 12.sp) }
            )
          }
          items(StudyProgramCluster.entries) { cluster ->
            val count = GuidanceData.studyPrograms.count { it.cluster == cluster }
            FilterChip(
              selected = selectedCluster == cluster,
              onClick = { selectedCluster = if (selectedCluster == cluster) null else cluster },
              label = { Text("${cluster.title} ($count)", fontSize = 12.sp) }
            )
          }
        }
      }
    }

    // List of Study Programs
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .weight(1f),
      contentPadding = PaddingValues(16.dp),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      items(filteredPrograms, key = { it.id }) { program ->
        val isSelectedAsTarget = targetStudyProgram?.id == program.id

        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(
            containerColor = if (isSelectedAsTarget) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
            else MaterialTheme.colorScheme.surface
          ),
          border = if (isSelectedAsTarget) androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary) else null,
          elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("prodi_item_${program.id}")
        ) {
          Column(modifier = Modifier.padding(14.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.Top
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Surface(
                  shape = RoundedCornerShape(6.dp),
                  color = when (program.cluster) {
                    StudyProgramCluster.HUMANIORA -> Color(0xFFF3E8FF)
                    StudyProgramCluster.ILMU_SOSIAL -> Color(0xFFFEF3C7)
                    StudyProgramCluster.ILMU_ALAM -> Color(0xFFE0F2FE)
                    StudyProgramCluster.ILMU_FORMAL -> Color(0xFFDCFCE7)
                    StudyProgramCluster.ILMU_TERAPAN -> Color(0xFFFEE2E2)
                  }
                ) {
                  Text(
                    text = program.cluster.title,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = when (program.cluster) {
                      StudyProgramCluster.HUMANIORA -> Color(0xFF7E22CE)
                      StudyProgramCluster.ILMU_SOSIAL -> Color(0xFFB45309)
                      StudyProgramCluster.ILMU_ALAM -> Color(0xFF0369A1)
                      StudyProgramCluster.ILMU_FORMAL -> Color(0xFF15803D)
                      StudyProgramCluster.ILMU_TERAPAN -> Color(0xFFB91C1C)
                    },
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                  )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                  text = "${program.id}. ${program.name}",
                  fontWeight = FontWeight.Bold,
                  fontSize = 15.sp,
                  color = MaterialTheme.colorScheme.onSurface
                )
              }

              if (isSelectedAsTarget) {
                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = MaterialTheme.colorScheme.primary
                ) {
                  Text(
                    text = "Target Utama",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Mapel Pendukung Banner
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = MaterialTheme.colorScheme.surfaceVariant,
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(10.dp)) {
                Text(
                  text = "Mata Pelajaran Pendukung (Kepmen 345/M/2022):",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = program.supportingSubjectsText,
                  fontSize = 12.sp,
                  fontWeight = FontWeight.SemiBold,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = "Rekomendasi Mapel Kurikulum Merdeka:",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.outline
              )

              if (!isSelectedAsTarget) {
                TextButton(
                  onClick = { viewModel.setTargetStudyProgram(program) },
                  contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                  modifier = Modifier.testTag("btn_select_target_${program.id}")
                ) {
                  Icon(Icons.Default.BookmarkAdd, contentDescription = null, modifier = Modifier.size(16.dp))
                  Spacer(modifier = Modifier.width(4.dp))
                  Text("Pilih Target Ini", fontSize = 12.sp)
                }
              } else {
                Button(
                  onClick = { onNavigate(AppScreen.SUBJECTS) },
                  contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                  colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                  Text("Sesuaikan Mapel", fontSize = 11.sp)
                  Spacer(modifier = Modifier.width(4.dp))
                  Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(14.dp))
                }
              }
            }
          }
        }
      }

      if (filteredPrograms.isEmpty()) {
        item {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .padding(40.dp),
            contentAlignment = Alignment.Center
          ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
              Icon(
                imageVector = Icons.Default.SearchOff,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.size(48.dp)
              )
              Spacer(modifier = Modifier.height(8.dp))
              Text("Tidak ada program studi yang cocok.", color = MaterialTheme.colorScheme.outline)
            }
          }
        }
      }
    }
  }
}
