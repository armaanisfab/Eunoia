package com.example.eunoia.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.eunoia.ui.theme.ThemePurple1
import com.example.eunoia.ui.theme.ThemePurple2
import com.example.eunoia.ui.theme.ThemePurple3
import com.example.eunoia.ui.theme.ThemePurple4
import com.example.eunoia.ui.theme.space2

@Composable
fun icon_heading_subheading(
    heading: String,
    subheading: String,
    iconResId: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick() }
            .border(width = 1.dp, color = ThemePurple4, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(ThemePurple1),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(ThemePurple2),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = iconResId),
                    modifier = Modifier
                        .size(25.dp),
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
                VerticalSpacer(space = 3.dp)
                Text(
                    text = subheading
                )
            }
        }
    }
}

@Composable
fun number_text_number_text(
    number1: Int,
    text1: String,
    number2: Int,
    text2: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable { onClick() }
            .border(width = 1.dp, color = ThemePurple4, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(ThemePurple2),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            HeadingText(text = number1.toString())
            HorizontalSpacer(space = space2.dp)
            BoldText(text = text1)
            HorizontalSpacer(space = space2.dp)
            HeadingText(text = number2.toString())
            HorizontalSpacer(space = space2.dp)
            BoldText(text = text2)
            HorizontalSpacer(space = space2.dp)
        }
    }
}
