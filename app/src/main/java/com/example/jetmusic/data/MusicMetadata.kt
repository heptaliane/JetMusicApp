package com.example.jetmusic.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import java.io.File

data class MusicMetadata(
    val title: String = "",
    val artist: String = "",
    val album: String = "",
    val artwork: Bitmap? = null,
    val duration: Int = 0,
    val track: Int? = null,
)

fun getMusicMetadataFromPath(path: String): MusicMetadata {
    val retriever = MediaMetadataRetriever()
    retriever.setDataSource(path)

    val artwork = when (val rawArtwork = retriever.embeddedPicture) {
        null -> null
        else -> BitmapFactory.decodeByteArray(rawArtwork, 0, rawArtwork.size)
    }

    return MusicMetadata(
        title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            ?: File(path).name,
        artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            ?: "",
        album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
            ?: "",
        artwork = artwork,
        duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toInt()
            ?: 0,
        track = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER)
            ?.toInt(),
    )
}

fun getMusicMetadataFromPlayList(playList: PlayList): MusicMetadata {
    return when (val path = playList.get()) {
        null -> MusicMetadata()
        else -> getMusicMetadataFromPath(path)
    }
}