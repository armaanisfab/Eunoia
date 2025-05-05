package com.example.eunoia.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.eunoia.R
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.components.icon_heading_subheading
import com.example.eunoia.ui.theme.space1
import com.example.eunoia.ui.theme.space2

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MeScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        VerticalSpacer(space = space1.dp)
        HeadingText(text = "Your Eunoia")
        VerticalSpacer(space = space1.dp)

        icon_heading_subheading(
            heading = "Log out",
            subheading = "See you later!",
            iconResId = R.drawable.logout_icon,
            onClick = {
                navController.navigate(Routes.Login.route)
            }
        )
        VerticalSpacer(space = space2.dp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showDialog = true
                }
        ) {
            icon_heading_subheading(
                heading = "Delete your account forever",
                subheading = "We're sorry to see you go!",
                iconResId = R.drawable.delete_forever_icon,
                onClick = {
                    showDialog = true
                }
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Delete account?") },
                text = { Text("Are you sure? This cannot be undone.") },
                confirmButton = {
                    Button(
                        onClick = {
                            navController.navigate(Routes.Login.route)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Red
                        )
                    ) {
                        Text("YES, DELETE")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showDialog = false },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        )
                    ) {
                        Text("NO, CANCEL")
                    }
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewMeScreen() {
    val mockNavController = rememberNavController()
    MeScreen(navController = mockNavController)
}
