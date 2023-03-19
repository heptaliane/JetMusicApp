package com.example.jetmusic.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MusicPlayerMusicInfo(
    title: String,
    artist: String,
    album: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = artist,
            color = Color.Gray,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = album,
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}
