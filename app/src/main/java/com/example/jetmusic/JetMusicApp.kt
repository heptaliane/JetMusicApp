package com.example.jetmusic

import android.app.Application
import android.media.MediaPlayer
import com.example.jetmusic.data.PlayList
import com.example.jetmusic.data.PlayListManager

class JetMusicApp : Application() {
    val player: MediaPlayer = MediaPlayer()
    val manager: PlayListManager = PlayListManager()
    var playList: PlayList = PlayList(emptyList())
}