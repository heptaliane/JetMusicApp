package com.example.jetmusic.data

import android.content.Context
import android.media.MediaMetadataRetriever
import android.provider.MediaStore

class PlayListManager {
    var allPlayList = PlayList(emptyList())
        private set

    var albumPlayList = mapOf<String?, PlayList>()
        private set

    var artistPlayList = mapOf<String?, PlayList>()
        private set

    fun reload(context: Context) {
        val paths = getLocalMusicPaths(context)
        this.allPlayList = PlayList(paths)

        val albumPaths = groupByMetadata(
            paths,
            MediaMetadataRetriever.METADATA_KEY_ALBUM
        )
        this.albumPlayList = albumPaths.mapValues { PlayList(it.value) }

        val artistPaths = groupByMetadata(
            paths,
            MediaMetadataRetriever.METADATA_KEY_ARTIST
        )
        this.artistPlayList = artistPaths.mapValues { PlayList(it.value) }
    }
}

private fun getLocalMusicPaths(context: Context): List<String> {
    val musicPaths = mutableListOf<String>()
    val projection = arrayOf(
        MediaStore.Audio.Media.DATA,
        MediaStore.Audio.Media.ALBUM,
        MediaStore.Audio.Media.TRACK
    )
    val selection = "${MediaStore.Audio.Media.IS_MUSIC} != 0"
    val sortOrder = "${MediaStore.Audio.Media.ALBUM} ASC, ${MediaStore.Audio.Media.TRACK} ASC"
    context.contentResolver.query(
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        projection,
        selection,
        null,
        sortOrder
    )?.use { cursor ->
        val dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)
        while (cursor.moveToNext()) {
            val path = cursor.getString(dataIndex)
            musicPaths.add(path)
        }
    }
    return musicPaths
}

private fun groupByMetadata(paths: List<String>, key: Int): Map<String?, List<String>> {
    val grouped = mutableMapOf<String?, MutableList<String>>()
    val retriever = MediaMetadataRetriever()

    paths.forEach { path ->
        retriever.setDataSource(path)
        val value = retriever.extractMetadata(key)
        grouped.putIfAbsent(value, lazy { mutableListOf<String>() }.value)
        grouped[value]!!.add(path)
    }

    return grouped
}