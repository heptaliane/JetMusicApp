package com.example.jetmusic.ui.components

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.jetmusic.data.PlayList
import com.example.jetmusic.data.getMusicMetadataFromPlayList
import com.example.jetmusic.resetMediaPlayer

@Composable
fun MusicPlayerView(
    player: MediaPlayer,
    playList: PlayList,
) {
    val metadata = remember {
        mutableStateOf(getMusicMetadataFromPlayList(playList))
    }
    var isShuffled = remember { mutableStateOf(!playList.isSorted()) }
    var repeatMode = remember { mutableStateOf(playList.repeatMode) }
    var isPlaying = remember { mutableStateOf(player.isPlaying) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        MusicPlayerArtwork(
            bitmap = metadata.value.artwork
        )
        MusicPlayerMusicInfo(
            title = metadata.value.title,
            artist = metadata.value.artist,
            album = metadata.value.album
        )
        SeekBar(
            current = player.currentPosition,
            duration = player.duration,
            onChange = { newPosition ->
                player.seekTo(newPosition)
            }
        )
        MusicPlayerControlPanel(
            isPlaying = isPlaying,
            isShuffled = isShuffled,
            repeatMode = repeatMode,
            onChangeRepeatMode = { newMode ->
                run {
                    playList.repeatMode = newMode
                    repeatMode.value = playList.repeatMode
                }
            },
            onChangePlayingState = { play ->
                run {
                    when (play) {
                        true -> player.start()
                        false -> player.pause()
                    }
                    isPlaying.value = player.isPlaying
                }
            },
            onChangeShuffleState = { shuffle: Boolean ->
                run {
                    when (shuffle) {
                        true -> playList.shuffle()
                        false -> playList.sort()
                    }
                    isShuffled.value = !playList.isSorted()
                }
            },
            onSkipToNext = {
                playList.next()
                resetMediaPlayer(player, playList)
            },
            onSkipToPrevious = {
                playList.prev()
                resetMediaPlayer(player, playList)
            },
        )
    }
}