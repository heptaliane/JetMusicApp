package com.example.jetmusic

import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import com.example.jetmusic.data.PlayList

fun resetMediaPlayer(player: MediaPlayer, playList: PlayList) {
    player.seekTo(0)
    when (val path = playList.get()) {
        null -> player.reset()
        else -> player.setDataSource(path)
    }
}
const val MILLIS_IN_SECOND = 1000
const val SECONDS_IN_MINUTE = 60
const val SECONDS_IN_HOUR = 3600  // 60 * 60

@Composable
fun formatTime(totalMilliSeconds: Int, hasHour: Boolean? = null): String {
    val totalSeconds = totalMilliSeconds / MILLIS_IN_SECOND
    val seconds = totalSeconds % SECONDS_IN_MINUTE
    val minutes = (totalSeconds % SECONDS_IN_HOUR) / SECONDS_IN_MINUTE

    return when (
        when (hasHour) {
            null -> totalSeconds > SECONDS_IN_HOUR
            else -> hasHour
        }
    ) {
        true -> {
            val hours = totalSeconds / SECONDS_IN_HOUR
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
        false -> {
            String.format("%02d:%02d", minutes, seconds)
        }
    }
}
