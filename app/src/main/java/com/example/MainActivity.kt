package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.SelectionViewModel

class MainActivity : ComponentActivity() {

  private val viewModel: SelectionViewModel by viewModels()

  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      MyApplicationTheme {
        val currentScreen by viewModel.currentScreen.collectAsState()

        // Handle Back button to return to Home screen if on another tab
        BackHandler(enabled = currentScreen != AppScreen.HOME) {
          viewModel.navigateTo(AppScreen.HOME)
        }

        Scaffold(
          modifier = Modifier
            .fillMaxSize()
            .testTag("main_scaffold"),
          topBar = {
            TopAppBar(
              title = {
                Column {
                  Text(
                    text = "PilihMapel SMA",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                  )
                  Text(
                    text = "Panduan Pemilihan Mapel Fase F • Revisi 2024",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                  )
                }
              },
              actions = {
                IconButton(
                  onClick = { viewModel.navigateTo(AppScreen.GUIDE_INFO) },
                  modifier = Modifier.testTag("topbar_info_button")
                ) {
                  Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Panduan Regulasi",
                    tint = MaterialTheme.colorScheme.primary
                  )
                }
              },
              colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onSurface
              )
            )
          },
          bottomBar = {
            NavigationBar(
              modifier = Modifier.testTag("bottom_navigation_bar"),
              tonalElevation = 6.dp
            ) {
              NavigationBarItem(
                selected = currentScreen == AppScreen.HOME,
                onClick = { viewModel.navigateTo(AppScreen.HOME) },
                icon = { Icon(Icons.Default.Home, contentDescription = "Beranda") },
                label = { Text("Beranda", fontSize = 10.sp) },
                modifier = Modifier.testTag("nav_home")
              )
              NavigationBarItem(
                selected = currentScreen == AppScreen.RIASEC,
                onClick = { viewModel.navigateTo(AppScreen.RIASEC) },
                icon = { Icon(Icons.Default.Psychology, contentDescription = "RIASEC") },
                label = { Text("RIASEC", fontSize = 10.sp) },
                modifier = Modifier.testTag("nav_riasec")
              )
              NavigationBarItem(
                selected = currentScreen == AppScreen.PRODI,
                onClick = { viewModel.navigateTo(AppScreen.PRODI) },
                icon = { Icon(Icons.Default.School, contentDescription = "59 Prodi") },
                label = { Text("59 Prodi", fontSize = 10.sp) },
                modifier = Modifier.testTag("nav_prodi")
              )
              NavigationBarItem(
                selected = currentScreen == AppScreen.SUBJECTS,
                onClick = { viewModel.navigateTo(AppScreen.SUBJECTS) },
                icon = { Icon(Icons.Default.Tune, contentDescription = "Pilih Mapel") },
                label = { Text("Mapel", fontSize = 10.sp) },
                modifier = Modifier.testTag("nav_subjects")
              )
              NavigationBarItem(
                selected = currentScreen == AppScreen.OFFICIAL_FORM,
                onClick = { viewModel.navigateTo(AppScreen.OFFICIAL_FORM) },
                icon = { Icon(Icons.Default.Description, contentDescription = "Formulir") },
                label = { Text("Formulir", fontSize = 10.sp) },
                modifier = Modifier.testTag("nav_form")
              )
            }
          }
        ) { innerPadding ->
          Box(
            modifier = Modifier
              .fillMaxSize()
              .padding(innerPadding)
          ) {
            when (currentScreen) {
              AppScreen.HOME -> HomeScreen(
                viewModel = viewModel,
                onNavigate = { viewModel.navigateTo(it) }
              )
              AppScreen.RIASEC -> RiasecScreen(
                viewModel = viewModel,
                onNavigate = { viewModel.navigateTo(it) }
              )
              AppScreen.PRODI -> StudyProgramCatalogScreen(
                viewModel = viewModel,
                onNavigate = { viewModel.navigateTo(it) }
              )
              AppScreen.SUBJECTS -> SubjectSelectionScreen(
                viewModel = viewModel,
                onNavigate = { viewModel.navigateTo(it) }
              )
              AppScreen.OFFICIAL_FORM -> OfficialFormScreen(
                viewModel = viewModel
              )
              AppScreen.GUIDE_INFO -> GuideInfoScreen()
            }
          }
        }
      }
    }
  }
}
