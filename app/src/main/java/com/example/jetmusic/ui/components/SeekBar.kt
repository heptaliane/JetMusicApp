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
import com.example.jetmusic.MILLIS_IN_SECOND
import com.example.jetmusic.SECONDS_IN_HOUR
import com.example.jetmusic.formatTime


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