package com.example.jetmusic.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetmusic.data.PlayList
import com.example.jetmusic.data.PlayListType

@Composable
fun EntrySelectionList(
    allPlayList: PlayList,
    albumPlayList: Map<String?, PlayList>,
    artistPlayList: Map<String?, PlayList>,
    onSelect: (PlayListType) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable { onSelect(PlayListType.ALL) }
                .fillMaxWidth()
                .padding(16.dp),
        ) {

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "All Musics",
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(
                    text = "${allPlayList.size()} Musics",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
        Divider()
        Row(
            modifier = Modifier
                .clickable { onSelect(PlayListType.ALBUM) }
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Album",
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(
                    text = "${albumPlayList.size} albums",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
        Divider()
        Row(
            modifier = Modifier
                .clickable { onSelect(PlayListType.ARTIST) }
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Artist",
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(
                    text = "${artistPlayList.size} artists",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}