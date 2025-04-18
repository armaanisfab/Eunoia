package com.example.eunoia.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.eunoia.R
import com.example.eunoia.ui.components.icon_heading_subheading
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.SubheadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.screens.components.ImageCarousel
import com.example.eunoia.ui.theme.space1
import com.example.eunoia.ui.theme.space2

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        HeadingText(text = "Welcome to Eunoia")
        VerticalSpacer(space = space1.dp)
        val homeScreenImageCarousel = listOf(
            R.drawable.quote1,
            R.drawable.quote2,
            R.drawable.quote3,
            R.drawable.quote4
        )
        ImageCarousel(imageResources = homeScreenImageCarousel)
        VerticalSpacer(space = space1.dp)
        SubheadingText(text = "Check in with yourself")
        VerticalSpacer(space = space2.dp)
        icon_heading_subheading(
            heading = "Log your mood",
            subheading = "How are you feeling today?",
            iconResId = R.drawable.smile_icon,
            onClick = { navController.navigate(Routes.Mood.route) }
        )
        VerticalSpacer(space = space2.dp)
        icon_heading_subheading(
            heading = "Pen your journal",
            subheading = "What's on your mind?",
            iconResId = R.drawable.pen_icon,
            onClick = { navController.navigate(Routes.Journal.route) }
        )
        VerticalSpacer(space = space2.dp)
        icon_heading_subheading(
            heading = "Explore personalized AI insights",
            subheading = "Advice from your wellness coach",
            iconResId = R.drawable.chat_icon,
            onClick = { navController.navigate(Routes.Insights.route) }
        )
        VerticalSpacer(space = space1.dp)
        SubheadingText(text = "Take a breather")
        VerticalSpacer(space = space2.dp)
        icon_heading_subheading(
            heading = "Meditate",
            subheading = "Start a relaxing meditation audio",
            iconResId = R.drawable.shield_icon,
            onClick = { navController.navigate(Routes.Meditation.route) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val mockNavController = rememberNavController()
    HomeScreen(navController = mockNavController)
}