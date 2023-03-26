package com.example.jetmusic.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ViewFrame(
    title: String = "",
    onBack: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Column {
        Header(title = title, onClick = onBack)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                content()
            }
        }
    }
}