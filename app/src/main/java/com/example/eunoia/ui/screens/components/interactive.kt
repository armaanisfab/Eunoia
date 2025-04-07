package com.example.eunoia.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.eunoia.ui.theme.ThemeDarkPurple
import com.example.eunoia.ui.theme.ThemeLightPurple

@Composable
fun CustomCard(
    heading: String,
    subheading: String,
    iconResId: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp)
            .height(80.dp) // Fixed card height
            .clickable { onClick() }, // Card is clickable
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(ThemeLightPurple), // Light purple background
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(ThemeDarkPurple),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = "Icon"
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                BoldText(
                    text = heading
                )
                Text(
                    text = subheading
                )
            }
        }
    }
}
