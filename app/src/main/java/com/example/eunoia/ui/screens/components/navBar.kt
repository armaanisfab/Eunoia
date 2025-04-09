package com.example.eunoia.ui.screens.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.eunoia.ui.screens.Routes
import com.example.eunoia.ui.theme.ThemePurple1
import com.example.eunoia.ui.theme.ThemePurple2

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = ThemePurple1
    ) {
        val currentRoute = navController.currentBackStackEntryAsState()?.value?.destination?.route
        Routes.allRoutes.forEach { route ->
            NavigationBarItem(
                icon = { Icon(imageVector = route.icon, contentDescription = route.title) },
                label = { Text(text = route.title) },
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
