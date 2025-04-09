package com.example.eunoia.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.eunoia.R
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.SubheadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.components.icon_heading_subheading
import com.example.eunoia.ui.components.number_text_number_text
import com.example.eunoia.ui.screens.components.ImageCarousel
import com.example.eunoia.ui.theme.space1
import com.example.eunoia.ui.theme.space2

@Composable
fun MeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                VerticalSpacer(space = space1.dp)
                HeadingText(text = "Your Eunoia")
                VerticalSpacer(space = space1.dp)
                icon_heading_subheading(
                    heading = "Log out",
                    subheading = "See you later!",
                    iconResId = R.drawable.logout_icon,
                    onClick = {
                        println("Card clicked!")
                    }
                )
                VerticalSpacer(space = space2.dp)
                icon_heading_subheading(
                    heading = "Delete your account forever",
                    subheading = "Long press to confirm",
                    iconResId = R.drawable.delete_forever_icon,
                    onClick = {
                        println("Card clicked!")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMeScreen() {
    MeScreen()
}