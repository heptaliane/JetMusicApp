package com.example.jetmusic.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jetmusic.R

@Composable
fun MusicPlayerArtwork(bitmap: Bitmap?) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = 4.dp
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Album artwork",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.baseline_music_note),
                contentDescription = "Album artwork",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
