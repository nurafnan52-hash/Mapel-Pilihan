package com.example.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.GuidanceData
import com.example.data.model.StudentProfile
import com.example.ui.viewmodel.SelectionViewModel

@Composable
fun OfficialFormScreen(
  viewModel: SelectionViewModel,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val studentProfile by viewModel.studentProfile.collectAsState()
  val dreamProfession by viewModel.dreamProfession.collectAsState()
  val dreamReason by viewModel.dreamReason.collectAsState()
  val selectedSubjectIds by viewModel.selectedSubjectIds.collectAsState()
  val backupSubjectIds by viewModel.backupSubjectIds.collectAsState()
  val targetStudyProgram by viewModel.targetStudyProgram.collectAsState()
  val isParentApproved by viewModel.isParentApproved.collectAsState()
  val isCounselorApproved by viewModel.isCounselorApproved.collectAsState()

  var showEditProfileDialog by remember { mutableStateOf(false) }

  val mainSubjects = selectedSubjectIds.mapNotNull { id ->
    GuidanceData.electiveSubjects.find { it.id == id }
  }

  val backupSubjects = backupSubjectIds.mapNotNull { id ->
    GuidanceData.electiveSubjects.find { it.id == id }
  }

  val totalJp = mainSubjects.sumOf { it.jpPerWeek }

  fun shareFormAsText() {
    val parentStatus = if (isParentApproved) "[Disetujui Orang Tua]" else "[Belum Disetujui]"
    val counselorStatus = if (isCounselorApproved) "[Disetujui Guru BK]" else "[Dalam Pendampingan]"

    val builder = StringBuilder()
    builder.appendLine("==================================================")
    builder.appendLine("FORMULIR PEMILIHAN MATA PELAJARAN PILIHAN FASE F")
    builder.appendLine("KEMENTERIAN PENDIDIKAN, KEBUDAYAAN, RISET, DAN TEKNOLOGI")
    builder.appendLine("==================================================")
    builder.appendLine("Nama Lengkap  : " + studentProfile.fullName)
    builder.appendLine("Kelas         : " + studentProfile.studentClass)
    builder.appendLine("NISN          : " + studentProfile.nisn)
    builder.appendLine("TTL           : " + studentProfile.birthPlaceDate)
    builder.appendLine("Alamat        : " + studentProfile.address)
    builder.appendLine("No. HP        : " + studentProfile.phoneNumber)
    builder.appendLine("--------------------------------------------------")
    builder.appendLine("A. CITA-CITA PROFESI & ALASAN:")
    builder.appendLine("Profesi: " + dreamProfession)
    if (targetStudyProgram != null) {
      builder.appendLine("Rencana Studi PTN: " + targetStudyProgram?.name)
    }
    builder.appendLine("Alasan : " + dreamReason)
    builder.appendLine("--------------------------------------------------")
    builder.appendLine("B. MATA PELAJARAN PILIHAN UTAMA (" + totalJp + " JP):")
    mainSubjects.forEachIndexed { index, s ->
      builder.appendLine("${index + 1}. ${s.name} (${s.jpPerWeek} JP) [${s.group.code}]")
    }
    builder.appendLine("\nMATA PELAJARAN CADANGAN:")
    backupSubjects.forEachIndexed { index, s ->
      builder.appendLine("${index + 1}. ${s.name} (${s.jpPerWeek} JP)")
    }
    builder.appendLine("--------------------------------------------------")
    builder.appendLine("MENGETAHUI:")
    builder.appendLine("Orang Tua/Wali : " + studentProfile.parentName + " " + parentStatus)
    builder.appendLine("Wali Kelas     : " + studentProfile.homeroomTeacherName)
    builder.appendLine("Guru BK        : " + studentProfile.counselorName + " " + counselorStatus)
    builder.appendLine("Peserta Didik  : " + studentProfile.fullName)
    builder.appendLine("==================================================")

    val sendIntent = Intent().apply {
      action = Intent.ACTION_SEND
      putExtra(Intent.EXTRA_TEXT, builder.toString())
      type = "text/plain"
    }
    context.startActivity(Intent.createChooser(sendIntent, "Bagikan Formulir Mapel Pilihan"))
  }

  LazyColumn(
    modifier = modifier
      .fillMaxSize()
      .testTag("official_form_screen"),
    contentPadding = PaddingValues(16.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // Toolbar Actions
    item {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = "Formulir Resmi Lampiran 2",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
          )
          Text(
            text = "Format standar Kemendikbudristek untuk penetapan kelas XI",
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
          OutlinedButton(
            onClick = { showEditProfileDialog = true },
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
            modifier = Modifier.testTag("btn_edit_profile")
          ) {
            Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Edit Data", fontSize = 11.sp)
          }

          Button(
            onClick = { shareFormAsText() },
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.testTag("btn_share_form")
          ) {
            Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(14.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("Bagikan", fontSize = 11.sp)
          }
        }
      }
    }

    // Official Printable Paper Card Look
    item {
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("official_paper_card")
      ) {
        Column(
          modifier = Modifier.padding(20.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          // Document Header
          Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(
              text = "FORMULIR PEMILIHAN MATA PELAJARAN PILIHAN",
              fontWeight = FontWeight.ExtraBold,
              fontSize = 14.sp,
              textAlign = TextAlign.Center,
              lineHeight = 18.sp
            )
            Text(
              text = "PEMBELAJARAN FASE F (KELAS XI - XII) TAHUN AJARAN 2024/2025",
              fontWeight = FontWeight.SemiBold,
              fontSize = 11.sp,
              textAlign = TextAlign.Center,
              color = MaterialTheme.colorScheme.primary
            )
            Text(
              text = "Mengacu Panduan Pemilihan Mata Pelajaran Pilihan Edisi Revisi 2024",
              fontSize = 10.sp,
              color = MaterialTheme.colorScheme.outline
            )
          }

          HorizontalDivider(thickness = 1.5.dp, color = MaterialTheme.colorScheme.onSurface)

          // Identity Table
          Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            FormRow(label = "Nama Peserta Didik", value = studentProfile.fullName)
            FormRow(label = "Kelas Asal", value = studentProfile.studentClass)
            FormRow(label = "NISN", value = studentProfile.nisn)
            FormRow(label = "Tempat, Tgl. Lahir", value = studentProfile.birthPlaceDate)
            FormRow(label = "Alamat Rumah", value = studentProfile.address)
            FormRow(label = "No. Handphone/WA", value = studentProfile.phoneNumber)
          }

          HorizontalDivider()

          // Bagian A
          Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
              text = "A. Apa profesi yang kamu cita-citakan? Mengapa kamu memilih profesi tersebut?",
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.primary
            )

            Surface(
              shape = RoundedCornerShape(8.dp),
              color = MaterialTheme.colorScheme.surfaceVariant,
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(10.dp)) {
                Text(
                  text = "Cita-cita Profesi: $dreamProfession",
                  fontWeight = FontWeight.Bold,
                  fontSize = 12.sp
                )
                if (targetStudyProgram != null) {
                  Text(
                    text = "Target Program Studi: ${targetStudyProgram?.name} (${targetStudyProgram?.supportingSubjectsText})",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.SemiBold
                  )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = "Alasan: $dreamReason",
                  fontSize = 11.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant,
                  lineHeight = 15.sp
                )
              }
            }
          }

          HorizontalDivider()

          // Bagian B - Mata Pelajaran Pilihan
          Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
              text = "B. Mata pelajaran pilihan apa yang akan kamu pilih di Fase F yang membekali profesi cita-cita kamu?",
              fontWeight = FontWeight.Bold,
              fontSize = 12.sp,
              color = MaterialTheme.colorScheme.primary
            )

            if (mainSubjects.isEmpty()) {
              Text(
                text = "(Belum ada mata pelajaran pilihan utama yang ditetapkan)",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.error
              )
            } else {
              mainSubjects.forEachIndexed { index, s ->
                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp),
                  horizontalArrangement = Arrangement.SpaceBetween,
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text(
                    text = "${index + 1}. ${s.name}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                  )
                  Text(
                    text = "${s.jpPerWeek} JP [${s.group.code}]",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.outline
                  )
                }
              }

              Text(
                text = "Total Beban: $totalJp JP per minggu (Ketentuan: 20-25 JP)",
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                color = if (totalJp in 20..25) Color(0xFF059669) else MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 4.dp)
              )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
              text = "Mata pelajaran pilihan lain (cadangan):",
              fontWeight = FontWeight.SemiBold,
              fontSize = 12.sp
            )

            if (backupSubjects.isEmpty()) {
              Text(
                text = "1. ........................................................\n2. ........................................................",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.outline
              )
            } else {
              backupSubjects.forEachIndexed { index, s ->
                Text(
                  text = "${index + 1}. ${s.name} (${s.jpPerWeek} JP)",
                  fontSize = 12.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
          }

          HorizontalDivider()

          // Signatures Section (Mengetahui)
          Text(
            text = "Lembar Persetujuan & Komitmen Bersama:",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
          )

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            SignatureBox(
              title = "Orang Tua / Wali",
              name = studentProfile.parentName,
              isApproved = isParentApproved,
              onToggle = { viewModel.toggleParentApproval() }
            )

            SignatureBox(
              title = "Guru BK / Konselor",
              name = studentProfile.counselorName,
              isApproved = isCounselorApproved,
              onToggle = { viewModel.toggleCounselorApproval() }
            )
          }

          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            SignatureBox(
              title = "Wali Kelas",
              name = studentProfile.homeroomTeacherName,
              isApproved = true,
              onToggle = {}
            )

            SignatureBox(
              title = "Peserta Didik",
              name = studentProfile.fullName,
              isApproved = true,
              onToggle = {}
            )
          }
        }
      }
    }
  }

  // Dialog Edit Data
  if (showEditProfileDialog) {
    var nameInput by remember { mutableStateOf(studentProfile.fullName) }
    var classInput by remember { mutableStateOf(studentProfile.studentClass) }
    var nisnInput by remember { mutableStateOf(studentProfile.nisn) }
    var ttlInput by remember { mutableStateOf(studentProfile.birthPlaceDate) }
    var phoneInput by remember { mutableStateOf(studentProfile.phoneNumber) }
    var parentInput by remember { mutableStateOf(studentProfile.parentName) }
    var professionInput by remember { mutableStateOf(dreamProfession) }
    var reasonInput by remember { mutableStateOf(dreamReason) }

    AlertDialog(
      onDismissRequest = { showEditProfileDialog = false },
      title = { Text("Edit Formulir Peserta Didik", fontWeight = FontWeight.Bold) },
      text = {
        LazyColumn(
          modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          item {
            OutlinedTextField(
              value = nameInput,
              onValueChange = { nameInput = it },
              label = { Text("Nama Siswa") },
              modifier = Modifier.fillMaxWidth()
            )
          }
          item {
            OutlinedTextField(
              value = classInput,
              onValueChange = { classInput = it },
              label = { Text("Kelas") },
              modifier = Modifier.fillMaxWidth()
            )
          }
          item {
            OutlinedTextField(
              value = nisnInput,
              onValueChange = { nisnInput = it },
              label = { Text("NISN") },
              modifier = Modifier.fillMaxWidth()
            )
          }
          item {
            OutlinedTextField(
              value = ttlInput,
              onValueChange = { ttlInput = it },
              label = { Text("Tempat, Tanggal Lahir") },
              modifier = Modifier.fillMaxWidth()
            )
          }
          item {
            OutlinedTextField(
              value = phoneInput,
              onValueChange = { phoneInput = it },
              label = { Text("No. HP / WA") },
              modifier = Modifier.fillMaxWidth()
            )
          }
          item {
            OutlinedTextField(
              value = parentInput,
              onValueChange = { parentInput = it },
              label = { Text("Nama Orang Tua / Wali") },
              modifier = Modifier.fillMaxWidth()
            )
          }
          item {
            OutlinedTextField(
              value = professionInput,
              onValueChange = { professionInput = it },
              label = { Text("Cita-cita Profesi") },
              modifier = Modifier.fillMaxWidth()
            )
          }
          item {
            OutlinedTextField(
              value = reasonInput,
              onValueChange = { reasonInput = it },
              label = { Text("Alasan Memilih Profesi") },
              modifier = Modifier.fillMaxWidth()
            )
          }
        }
      },
      confirmButton = {
        Button(
          onClick = {
            viewModel.updateStudentProfile(
              studentProfile.copy(
                fullName = nameInput,
                studentClass = classInput,
                nisn = nisnInput,
                birthPlaceDate = ttlInput,
                phoneNumber = phoneInput,
                parentName = parentInput
              )
            )
            viewModel.updateAspirations(professionInput, reasonInput)
            showEditProfileDialog = false
          }
        ) {
          Text("Simpan")
        }
      },
      dismissButton = {
        TextButton(onClick = { showEditProfileDialog = false }) {
          Text("Batal")
        }
      }
    )
  }
}

@Composable
fun FormRow(label: String, value: String) {
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = "$label:",
      fontSize = 11.sp,
      color = MaterialTheme.colorScheme.onSurfaceVariant,
      modifier = Modifier.width(130.dp)
    )
    Text(
      text = value.ifBlank { "-" },
      fontSize = 11.sp,
      fontWeight = FontWeight.Medium,
      modifier = Modifier.weight(1f)
    )
  }
}

@Composable
fun SignatureBox(
  title: String,
  name: String,
  isApproved: Boolean,
  onToggle: () -> Unit
) {
  Surface(
    shape = RoundedCornerShape(8.dp),
    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
    modifier = Modifier
      .width(140.dp)
      .padding(vertical = 4.dp)
  ) {
    Column(
      modifier = Modifier.padding(8.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = title, fontSize = 10.sp, color = MaterialTheme.colorScheme.outline)
      Spacer(modifier = Modifier.height(18.dp))
      if (isApproved) {
        Surface(
          shape = RoundedCornerShape(4.dp),
          color = Color(0xFF10B981).copy(alpha = 0.2f)
        ) {
          Text(
            text = "✓ DISETUJUI",
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF059669),
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
          )
        }
      } else {
        TextButton(
          onClick = onToggle,
          contentPadding = PaddingValues(2.dp)
        ) {
          Text("Klik Setujui", fontSize = 10.sp)
        }
      }
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "($name)",
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center
      )
    }
  }
}
