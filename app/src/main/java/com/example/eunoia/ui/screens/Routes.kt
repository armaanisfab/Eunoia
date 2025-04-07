package com.example.eunoia.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val route: String, val title: String, val icon: ImageVector) {
    object Home : Routes("home", "Home", Icons.Filled.Home)
    object Progress : Routes("progress", "Progress", Icons.Filled.CheckCircle)
    object Me : Routes("me", "Me", Icons.Filled.Person)

    companion object {
        val allRoutes = listOf(Home, Progress, Me)
    }
}
