package com.example.eunoia.ui.screens.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.eunoia.ui.components.NormalText
import com.example.eunoia.ui.screens.Routes
import com.example.eunoia.ui.theme.ThemePurple1
import com.example.eunoia.ui.theme.ThemePurple2

@Composable
fun BottomNavigationBar(navController: NavController) {
    // Filter only main routes for bottom navigation
    val mainRoutes = listOf(Routes.Home, Routes.Progress, Routes.Me)

    NavigationBar(
        containerColor = ThemePurple1
    ) {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        mainRoutes.forEach { route ->
            NavigationBarItem(
                icon = { Icon(imageVector = route.icon, contentDescription = route.title) },
                label = { NormalText(text = route.title) },
                selected = currentRoute == route.route,
                onClick = {
                    navController.navigate(route.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                    indicatorColor = ThemePurple2
                )
            )
        }
    }
}