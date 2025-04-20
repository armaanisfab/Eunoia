package com.example.eunoia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eunoia.ui.screens.components.BottomNavigationBar
import com.example.eunoia.ui.screens.HomeScreen
import com.example.eunoia.ui.screens.InsightsScreen
import com.example.eunoia.ui.screens.JournalScreen
import com.example.eunoia.ui.screens.LoginScreen
import com.example.eunoia.ui.screens.MeScreen
import com.example.eunoia.ui.screens.MeditationScreen
import com.example.eunoia.ui.screens.MoodScreen
import com.example.eunoia.ui.screens.ProgressScreen
import com.example.eunoia.ui.screens.Routes
import com.example.eunoia.ui.theme.EunoiaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EunoiaTheme {
                EunoiaScaffold()
            }
        }
    }
}

@Composable
fun EunoiaScaffold() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        bottomBar = {
            // Display bottom navigation only for main routes
            if (currentRoute in listOf(Routes.Home.route, Routes.Progress.route, Routes.Me.route)) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Main pages
            composable(Routes.Login.route) { LoginScreen(navController = navController) }
            composable(Routes.Home.route) { HomeScreen(navController = navController) }
            composable(Routes.Progress.route) { ProgressScreen() }
            composable(Routes.Me.route) { MeScreen(navController = navController) }

            // Auxiliary pages
            composable(Routes.Mood.route) { MoodScreen(navController = navController) }
            composable(Routes.Journal.route) { JournalScreen(navController = navController) }
            composable(Routes.Insights.route) { InsightsScreen(navController = navController) }
            composable(Routes.Meditation.route) { MeditationScreen(navController = navController) }
        }
    }
}
