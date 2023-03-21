package com.example.jetmusic.ui.components

import androidx.compose.runtime.Composable
import com.example.jetmusic.data.PlayListManager
import com.example.jetmusic.data.PlayListType

@Composable
fun EntryView(
    manager: PlayListManager,
    onSelect: (PlayListType) -> Unit,
) {
    EntrySelectionList(
        allPlayList = manager.allPlayList,
        albumPlayList = manager.albumPlayList,
        artistPlayList = manager.artistPlayList,
        onSelect = onSelect
    )
}