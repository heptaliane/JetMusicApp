package com.example.jetmusic

import android.media.MediaPlayer
import com.example.jetmusic.data.PlayList

fun resetMediaPlayer(player: MediaPlayer, playList: PlayList) {
    player.seekTo(0)
    when (val path = playList.get()) {
        null -> player.reset()
        else -> player.setDataSource(path)
    }
}