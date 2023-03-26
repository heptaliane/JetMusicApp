package com.example.jetmusic.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.jetmusic.data.PlayList
import com.example.jetmusic.data.getMusicMetadataFromPath

@Composable
fun PlayListView(
    title: String,
    playList: PlayList,
    active: Boolean,
    onSelect: () -> Unit,
    onBack: () -> Unit
) {
    val cursor = remember {
        mutableStateOf(
            when (active) {
                true -> playList.cursor
                false -> null
            }
        )
    }
    ViewFrame(title = title, onBack = onBack) {
        PlayListViewMusicList(
            metadataList = playList.list().map {
                getMusicMetadataFromPath(it)
            },
            cursor = cursor
        ) { newCursor ->
            playList.cursor = newCursor
            onSelect()
        }
    }
}