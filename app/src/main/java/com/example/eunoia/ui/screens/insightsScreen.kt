package com.example.eunoia.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.eunoia.ui.components.GradientSubheadingText
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.NormalText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.theme.ThemePurple1
import com.example.eunoia.ui.theme.ThemePurple2
import com.example.eunoia.ui.theme.ThemePurple3
import com.example.eunoia.ui.theme.ThemePurple4
import com.example.eunoia.ui.theme.space1
import com.example.eunoia.ui.theme.space2

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InsightsScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Sticky header: Top section with app background color
        stickyHeader {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background) // Use app's background color
                    .fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Icon",
                    tint = Color.Black,
                    modifier = Modifier
                        .clickable { navController.navigate(Routes.Home.route) }
                )
                VerticalSpacer(space = space2.dp)
                HeadingText(text = "Explore personalized AI insights")
                VerticalSpacer(space = space1.dp)
            }
        }

        // Scrollable content: Middle section
        item {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                GradientSubheadingText(text = "Embrace Your Happiness")
                VerticalSpacer(space = space2.dp)
                NormalText(text = "It’s great to hear you’re feeling relaxed and calm today! Recognize and savor the happiness you're feeling—it's a powerful emotion that can help ground you amidst the stress. Take a moment to reflect on what’s bringing you joy, even if it’s something small. Practicing gratitude can amplify those positive feelings.")
                VerticalSpacer(space = space2.dp)
                GradientSubheadingText(text = "Address the Stress")
                VerticalSpacer(space = space2.dp)
                NormalText(text = "Identify the source of your stress and break it down into manageable parts. Is it a specific task, responsibility, or lingering worry? Once you pinpoint it, tackle what you can step-by-step. Remember, it’s okay to delegate or ask for help if you’re feeling overwhelmed—balancing joy and stress becomes easier when you’re not carrying the load alone.")
                VerticalSpacer(space = space2.dp)
                GradientSubheadingText(text = "Create Balance")
                VerticalSpacer(space = space2.dp)
                NormalText(text = "Find activities that let you ride the wave of your happiness while calming the stress. Deep breathing exercises, a short walk in nature, or even dancing to your favorite song can work wonders. Strive for moments of balance where you’re not suppressing your stress, but acknowledging it while fully embracing your joy. This harmony can create a powerful sense of well-being.")
            }
        }

        // Bottom section: Submit button
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                VerticalSpacer(space = space1.dp)
                Button(
                    onClick = { navController.navigate(Routes.Home.route) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ThemePurple3,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Done")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInsightsScreen() {
    val mockNavController = rememberNavController()
    InsightsScreen(navController = mockNavController)
}