package com.example.eunoia.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank //Signifies no icon
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val route: String, val title: String, val icon: ImageVector) {
    object Home : Routes("home", "Home", Icons.Filled.Home)
    object Progress : Routes("progress", "Progress", Icons.Filled.CheckCircle)
    object Me : Routes("me", "Me", Icons.Filled.Person)
    object Mood : Routes("mood", "Mood", Icons.Filled.CheckBoxOutlineBlank)
    object Journal : Routes("journal", "Journal", Icons.Filled.CheckBoxOutlineBlank)
    object Insights : Routes("insights", "Insights", Icons.Filled.CheckBoxOutlineBlank)
    object Meditation : Routes("meditation", "Meditation", Icons.Filled.CheckBoxOutlineBlank)

    // Update `allRoutes` to include the new routes:
    companion object {
        val allRoutes = listOf(Home, Progress, Me, Mood, Journal, Insights, Meditation)
    }
}
