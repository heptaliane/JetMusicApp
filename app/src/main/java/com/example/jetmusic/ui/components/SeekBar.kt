package com.example.jetmusic.ui.components

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun SeekBar(
    current: Int,
    duration: Int,
    onChange: (Int) -> Unit
) {
    val hasHour = duration >= SECONDS_IN_HOUR * MILLIS_IN_SECOND

    Column {
        Text(
            text = formatTime(current, hasHour),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        BoxWithConstraints {
            Slider(
                value = when (duration) {
                    0 -> 0f
                    else -> current.toFloat() / duration.toFloat()
                },
                onValueChange = { progress ->
                    onChange((duration * progress).toInt())
                },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colors.primary,
                    activeTrackColor = MaterialTheme.colors.primary
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }


        Text(
            text = "-${formatTime(duration - current, hasHour)}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private const val MILLIS_IN_SECOND = 1000
private const val SECONDS_IN_MINUTE = 60
private const val SECONDS_IN_HOUR = 3600  // 60 * 60

@Composable
private fun formatTime(totalMilliSeconds: Int, hasHour: Boolean): String {
    val totalSeconds = totalMilliSeconds / MILLIS_IN_SECOND
    val seconds = totalSeconds % SECONDS_IN_MINUTE
    val minutes = (totalSeconds % SECONDS_IN_HOUR) / SECONDS_IN_MINUTE

    return if (hasHour) {
        val hours = totalSeconds / SECONDS_IN_HOUR
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    } else {
        String.format("%02d:%02d", minutes, seconds)
    }
}