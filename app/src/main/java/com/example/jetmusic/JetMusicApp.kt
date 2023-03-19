package com.example.jetmusic

import android.app.Application
import android.media.MediaPlayer
import com.example.jetmusic.data.PlayList

class JetMusicApp: Application() {
    val player: MediaPlayer = MediaPlayer()
    var playList: PlayList = PlayList(emptyArray())
}