package com.example.eunoia.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.eunoia.ui.components.HeadingText
import com.example.eunoia.ui.components.VerticalSpacer
import com.example.eunoia.ui.theme.ThemePurple2
import com.example.eunoia.ui.theme.ThemePurple3
import com.example.eunoia.ui.theme.space2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@Composable
fun MeditationScreen(navController: NavController) {
    // Create and initialize ExoPlayer
    val exoPlayer = remember {
        ExoPlayer.Builder(navController.context).build().apply {
            setMediaItem(MediaItem.fromUri("android.resource://${navController.context.packageName}/raw/meditation1"))
            prepare()
        }
    }

    // State variables
    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableStateOf(0L) }
    val duration = exoPlayer.duration.coerceAtLeast(0L)
    val coroutineScope = rememberCoroutineScope()
    var amplitude by remember { mutableStateOf(0) }
    var smoothAmplitude by remember { mutableStateOf(0f) }

    // Animation update logic
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            coroutineScope.launch {
                while (isPlaying) {
                    currentPosition = exoPlayer.currentPosition
                    amplitude = (Math.sin(currentPosition.toDouble() / duration * Math.PI) * 255).toInt()
                    delay(16)  // Faster updates (approx. 60fps)
                }
            }
        }
    }

    // Smooth interpolation for amplitude
    LaunchedEffect(amplitude) {
        coroutineScope.launch {
            while (isPlaying) {
                smoothAmplitude = lerp(smoothAmplitude, amplitude.toFloat(), 0.1f) // Smooth transition
                delay(16) // Keep syncing at high frequency
            }
        }
    }

    // Main UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top container: Back icon and heading
        Column {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back Icon",
                tint = Color.Black,
                modifier = Modifier
                    .clickable {
                        // Stop audio playback and release the player
                        exoPlayer.stop()
                        exoPlayer.release()
                        navController.navigate(Routes.Home.route) // Navigate back to home
                    }
            )
            VerticalSpacer(space = space2.dp)
            HeadingText(text = "Meditate")
        }

        // Middle container: Purple abstract animation
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
        ) {
            drawAbstractAnimation(smoothAmplitude)
        }

        // Bottom container: Audio controls
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Duration display with updated styling
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = formatTime(currentPosition),
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
                Text(
                    text = formatTime(duration),
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 4.dp)
                )
            }

            // Slider with updated styling
            Slider(
                value = currentPosition.toFloat(),
                onValueChange = {
                    exoPlayer.seekTo(it.toLong())
                    currentPosition = it.toLong()
                },
                valueRange = 0f..duration.toFloat(),
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                colors = SliderDefaults.colors(
                    activeTrackColor = ThemePurple2,
                    thumbColor = ThemePurple3
                )
            )

            // Play/Pause button
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        if (isPlaying) {
                            exoPlayer.pause()
                        } else {
                            exoPlayer.play()
                        }
                        isPlaying = !isPlaying
                    }
            )
        }
    }
}

// Custom lerp function
fun lerp(start: Float, end: Float, fraction: Float): Float {
    return start + (end - start) * fraction
}

fun DrawScope.drawAbstractAnimation(amplitude: Float) {
    val normalizedAmplitude = amplitude / 255f
    val minRadius = size.minDimension * 0.1f
    val maxRadius = size.minDimension * 0.5f
    val radius = minRadius + (maxRadius - minRadius) * normalizedAmplitude
    val color = ThemePurple3.copy(alpha = normalizedAmplitude)
    drawCircle(color = color, radius = radius)
}

fun formatTime(milliseconds: Long): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
    val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Preview(showBackground = true)
@Composable
fun PreviewMeditationScreen() {
    val mockNavController = rememberNavController()
    MeditationScreen(navController = mockNavController)
}
