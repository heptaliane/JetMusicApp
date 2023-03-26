package com.example.jetmusic

import android.app.Application
import android.media.MediaPlayer
import com.example.jetmusic.data.PlayList
import com.example.jetmusic.data.PlayListManager
import com.example.jetmusic.data.PlayListType

class JetMusicApp : Application() {
    val player: MediaPlayer = MediaPlayer()
    val manager: PlayListManager = PlayListManager()
    var playList: PlayList = PlayList(emptyList())

    init {
        this.player.setOnCompletionListener {
            this.playList.next()
            this.reloadPlayerMusic()
        }
    }

    private fun reloadPlayerMusic() {
        this.player.seekTo(0)

        val music = this.playList.get()
        if (music != null) {
            this.player.setDataSource(music)
            this.player.start()
        }
    }
}