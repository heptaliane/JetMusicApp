package com.example.jetmusic.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetmusic.R
import com.example.jetmusic.data.MusicMetadata
import com.example.jetmusic.formatTime

@Composable
fun PlayListViewMusicList(
    metadataList: List<MusicMetadata>,
    cursor: MutableState<Int?>,
    onClick: (Int) -> Unit
) {
    metadataList.forEachIndexed { index, metadata ->
        val selected = cursor.value == index
        Row(
            modifier = Modifier
                .clickable { onClick(index) }
                .fillMaxWidth()
                .padding(8.dp)
                .background(
                    when (selected) {
                        true -> Color.LightGray
                        false -> Color.Transparent
                    }
                )
        ) {
            when {
                selected -> Image(
                    painter = painterResource(id = R.drawable.baseline_play_arrow_24),
                    contentDescription = "Album Artwork",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
                metadata.artwork != null -> Image(
                    bitmap = metadata.artwork.asImageBitmap(),
                    contentDescription = "Album Artwork",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
                else -> Image(
                    painter = painterResource(id = R.drawable.baseline_music_note),
                    contentDescription = "Album Artwork",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = metadata.title,
                    fontSize = 12.sp,
                    color = Color.Black
                )
                Text(
                    text = "${metadata.artist} - ${metadata.album}",
                    fontSize = 10.sp,
                    color = Color.Gray
                )
                Text(
                    text = formatTime(totalMilliSeconds = metadata.duration),
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
        }
        Divider()
    }
}