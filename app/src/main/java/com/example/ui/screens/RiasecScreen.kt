package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.example.data.model.RiasecType
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.SelectionViewModel

@Composable
fun RiasecScreen(
  viewModel: SelectionViewModel,
  onNavigate: (AppScreen) -> Unit,
  modifier: Modifier = Modifier
) {
  val riasecMode by viewModel.riasecMode.collectAsState()
  val riasec1Answers by viewModel.riasec1Answers.collectAsState()
  val riasec2Skills by viewModel.riasec2SelectedSkills.collectAsState()
  val riasecResult by viewModel.riasecResult.collectAsState()

  var snackbarMessage by remember { mutableStateOf<String?>(null) }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("riasec_screen_content"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Header Info Card
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
              imageVector = Icons.Default.Psychology,
              contentDescription = null,
              tint = MaterialTheme.colorScheme.primary
            )
            Text(
              text = "Instrumen Pemetaan Minat & Bakat RIASEC",
              fontWeight = FontWeight.Bold,
              fontSize = 16.sp,
              color = MaterialTheme.colorScheme.onPrimaryContainer
            )
          }

          Spacer(modifier = Modifier.height(6.dp))

          Text(
            text = "Berdasarkan Lampiran 2 Panduan Kurikulum Merdeka: John Holland RIASEC memetakan kepribadian karier (Realistic, Investigative, Artistic, Social, Enterprising, Conventional).",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f),
            lineHeight = 17.sp
          )

          Spacer(modifier = Modifier.height(12.dp))

          // Mode Selector Tabs (RIASEC 1 vs RIASEC 2)
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            FilterChip(
              selected = riasecMode == 1,
              onClick = { viewModel.setRiasecMode(1) },
              label = { Text("RIASEC 1 (Minat 42 Butir)", fontSize = 12.sp) },
              modifier = Modifier.testTag("tab_riasec_1")
            )
            FilterChip(
              selected = riasecMode == 2,
              onClick = { viewModel.setRiasecMode(2) },
              label = { Text("RIASEC 2 (Bakat 8 Pilihan)", fontSize = 12.sp) },
              modifier = Modifier.testTag("tab_riasec_2")
            )
          }
        }
      }
    }

    // Result Card if evaluated
    if (riasecResult != null) {
      item {
        val result = riasecResult!!
        Card(
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
          elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
          modifier = Modifier
            .fillMaxWidth()
            .testTag("riasec_result_card")
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Column {
                Text(
                  text = "Hasil Pemetaan Karier Siswa",
                  style = MaterialTheme.typography.titleMedium,
                  fontWeight = FontWeight.Bold
                )
                Text(
                  text = "Holland 3-Letter Code: ${result.hollandCode}",
                  color = MaterialTheme.colorScheme.primary,
                  fontWeight = FontWeight.ExtraBold,
                  fontSize = 18.sp
                )
              }

              Surface(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.size(48.dp)
              ) {
                Box(contentAlignment = Alignment.Center) {
                  Text(
                    text = result.hollandCode,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary
                  )
                }
              }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Score Bars
            Text(
              text = "Distribusi Skor 6 Dimensi Holland:",
              fontWeight = FontWeight.SemiBold,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            RiasecType.entries.forEach { type ->
              val score = result.scores[type] ?: 0
              val isTop = result.topCodes.contains(type)
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = "${type.code} - ${type.title}",
                  fontSize = 12.sp,
                  fontWeight = if (isTop) FontWeight.Bold else FontWeight.Normal,
                  color = if (isTop) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                  modifier = Modifier.width(115.dp)
                )

                Box(
                  modifier = Modifier
                    .weight(1f)
                    .height(14.dp)
                    .clip(RoundedCornerShape(7.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                  Box(
                    modifier = Modifier
                      .fillMaxHeight()
                      .fillMaxWidth(fraction = (score / 10f).coerceIn(0.05f, 1f))
                      .clip(RoundedCornerShape(7.dp))
                      .background(
                        if (isTop) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                      )
                  )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                  text = "$score poin",
                  fontSize = 12.sp,
                  fontWeight = if (isTop) FontWeight.Bold else FontWeight.Normal,
                  modifier = Modifier.width(50.dp)
                )
              }
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(12.dp))

            // Interpretation for Top Code
            val dominant = result.topCodes.firstOrNull() ?: RiasecType.REALISTIC
            Text(
              text = "Karakteristik Dominan: ${dominant.title} (${dominant.nickname})",
              fontWeight = FontWeight.Bold,
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.primary
            )
            Text(
              text = dominant.description,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant,
              lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
              text = "Rekomendasi Profesi Sesuai Panduan:",
              fontWeight = FontWeight.SemiBold,
              fontSize = 12.sp
            )
            Text(
              text = dominant.typicalCareers.joinToString(", "),
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = "Rumpun Program Studi Lanjutan:",
              fontWeight = FontWeight.SemiBold,
              fontSize = 12.sp
            )
            Text(
              text = dominant.studyFields.joinToString(", "),
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.secondary,
              fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.End
            ) {
              Button(
                onClick = { onNavigate(AppScreen.PRODI) },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
              ) {
                Text("Eksplorasi Prodi PTN", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
              }
            }
          }
        }
      }
    }

    // Interactive Questionnaire Section
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = if (riasecMode == 1) "Kuesioner Minat (RIASEC 1)" else "Pilihan Keterampilan (RIASEC 2)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = if (riasecMode == 1)
              "Centang hal yang kamu SUKA (${riasec1Answers.count { it.value }} / 42 terisi)"
            else
              "Pilih 8 hal yang paling bisa kamu lakukan (${riasec2Skills.size} / 8 terpilih)",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        OutlinedButton(
          onClick = { viewModel.fillPredefinedRiasecDemo() },
          contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
          modifier = Modifier.testTag("button_demo_riasec")
        ) {
          Text("Contoh Cepat", fontSize = 11.sp)
        }
      }
    }

    if (riasecMode == 1) {
      // Questions RIASEC 1
      items(GuidanceData.riasec1Questions, key = { it.id }) { question ->
        val isChecked = riasec1Answers[question.id] == true
        Card(
          shape = RoundedCornerShape(10.dp),
          colors = CardDefaults.cardColors(
            containerColor = if (isChecked) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
            else MaterialTheme.colorScheme.surface
          ),
          modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.toggleRiasec1Answer(question.id) }
            .testTag("riasec1_item_${question.id}")
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Checkbox(
              checked = isChecked,
              onCheckedChange = { viewModel.toggleRiasec1Answer(question.id) },
              modifier = Modifier.testTag("checkbox_q_${question.id}")
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "${question.id}. ${question.text}",
              fontSize = 13.sp,
              color = MaterialTheme.colorScheme.onSurface,
              modifier = Modifier.weight(1f)
            )
          }
        }
      }

      item {
        Button(
          onClick = { viewModel.evaluateRiasec1() },
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .testTag("button_evaluate_riasec1"),
          colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
          Icon(Icons.Default.Calculate, contentDescription = null)
          Spacer(modifier = Modifier.width(8.dp))
          Text("Hitung Hasil Holland RIASEC 1", fontWeight = FontWeight.Bold)
        }
      }
    } else {
      // Questions RIASEC 2 (24 skills, pick exactly 8)
      item {
        Text(
          text = "Pilih 8 keterampilan di bawah ini yang kamu rasa paling bisa dilakukan tanpa kesulitan:",
          fontSize = 13.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }

      item {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          GuidanceData.riasec2Skills.chunked(2).forEach { rowPair ->
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              rowPair.forEach { skill ->
                val isSelected = riasec2Skills.contains(skill.id)
                Card(
                  modifier = Modifier
                    .weight(1f)
                    .clickable {
                      val success = viewModel.toggleRiasec2Skill(skill.id)
                      if (!success && !isSelected && riasec2Skills.size >= 8) {
                        snackbarMessage = "Sudah memilih 8 keterampilan maksimal"
                      }
                    }
                    .testTag("riasec2_item_${skill.id}"),
                  shape = RoundedCornerShape(10.dp),
                  colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.secondaryContainer
                    else MaterialTheme.colorScheme.surface
                  ),
                  border = if (isSelected) androidx.compose.foundation.BorderStroke(1.5.dp, MaterialTheme.colorScheme.secondary) else null
                ) {
                  Row(
                    modifier = Modifier
                      .fillMaxWidth()
                      .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Icon(
                      imageVector = if (isSelected) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                      contentDescription = null,
                      tint = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.outline,
                      modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                      text = skill.text,
                      fontSize = 12.sp,
                      fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                      color = MaterialTheme.colorScheme.onSurface
                    )
                  }
                }
              }
              if (rowPair.size == 1) {
                Spacer(modifier = Modifier.weight(1f))
              }
            }
          }
        }
      }

      item {
        Button(
          onClick = { viewModel.evaluateRiasec2() },
          enabled = riasec2Skills.isNotEmpty(),
          modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .testTag("button_evaluate_riasec2"),
          colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
          Icon(Icons.Default.Insights, contentDescription = null)
          Spacer(modifier = Modifier.width(8.dp))
          Text("Hitung Hasil Bakat RIASEC 2 (${riasec2Skills.size}/8)", fontWeight = FontWeight.Bold)
        }
      }
    }

    if (snackbarMessage != null) {
      item {
        Snackbar(
          action = {
            TextButton(onClick = { snackbarMessage = null }) {
              Text("Tutup", color = Color.White)
            }
          }
        ) {
          Text(snackbarMessage ?: "")
        }
      }
    }
  }
}
