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
import androidx.navigation.compose.rememberNavController
import com.example.eunoia.ui.screens.components.BottomNavigationBar
import com.example.eunoia.ui.screens.HomeScreen
import com.example.eunoia.ui.screens.MeScreen
import com.example.eunoia.ui.screens.ProgressScreen
import com.example.eunoia.ui.screens.Routes
import com.example.eunoia.ui.theme.EunoiaTheme

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
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.Home.route) { HomeScreen() }
            composable(Routes.Progress.route) { ProgressScreen() }
            composable(Routes.Me.route) { MeScreen() }
        }
    }
}
