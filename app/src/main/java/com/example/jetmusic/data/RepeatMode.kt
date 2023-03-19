package com.example.jetmusic.data

enum class RepeatMode {
    NONE,
    PLAYLIST,
    ONE,
}

fun nextRepeatMode(repeatMode: RepeatMode): RepeatMode {
    return when (repeatMode) {
        RepeatMode.NONE -> RepeatMode.PLAYLIST
        RepeatMode.PLAYLIST -> RepeatMode.ONE
        RepeatMode.ONE -> RepeatMode.NONE
    }
}