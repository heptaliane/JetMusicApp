package com.example.jetmusic.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.jetmusic.R
import com.example.jetmusic.data.RepeatMode
import com.example.jetmusic.data.nextRepeatMode

@Composable
fun MusicPlayerControlPanel(
    isPlaying: MutableState<Boolean>,
    isShuffled: MutableState<Boolean>,
    repeatMode: MutableState<RepeatMode>,
    onChangeRepeatMode: (RepeatMode) -> Unit,
    onChangeShuffleState: (Boolean) -> Unit,
    onChangePlayingState: (Boolean) -> Unit,
    onSkipToPrevious: () -> Unit,
    onSkipToNext: () -> Unit,
) {
    val shuffleIcon = when (isShuffled.value) {
        true -> ImageVector.vectorResource(id = R.drawable.baseline_shuffle_on_24)
        false -> ImageVector.vectorResource(id = R.drawable.baseline_shuffle_24)
    }
    val playIcon = when (isPlaying.value) {
        true -> ImageVector.vectorResource(id = R.drawable.baseline_play_arrow_24)
        false -> ImageVector.vectorResource(id = R.drawable.baseline_pause_24)
    }
    val repeatIcon = when (repeatMode.value) {
        RepeatMode.NONE -> ImageVector.vectorResource(id = R.drawable.baseline_repeat_24)
        RepeatMode.PLAYLIST -> ImageVector.vectorResource(id = R.drawable.baseline_repeat_on_24)
        RepeatMode.ONE -> ImageVector.vectorResource(id = R.drawable.baseline_repeat_one_on_24)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(onClick = { onChangeShuffleState(!isShuffled.value) }) {
            Image(
                imageVector = shuffleIcon,
                contentDescription = "Shuffle",
                modifier = Modifier
                    .padding(horizontal = 7.dp, vertical = 4.dp)
                    .size(23.dp)
            )
        }
        IconButton(onClick = onSkipToPrevious) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_skip_previous_24),
                contentDescription = "Previous",
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .size(36.dp)
            )
        }
        IconButton(onClick = { onChangePlayingState(!isPlaying.value) }) {
            Image(
                imageVector = playIcon,
                contentDescription = "Play",
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .size(48.dp)
            )
        }
        IconButton(onClick = { onSkipToNext() }) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_skip_next_24),
                contentDescription = "Next",
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .size(36.dp)
            )
        }
        IconButton(onClick = { onChangeRepeatMode(nextRepeatMode(repeatMode.value)) }) {
            Image(
                imageVector = repeatIcon,
                contentDescription = "Repeat",
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .size(24.dp)
            )
        }
    }
}