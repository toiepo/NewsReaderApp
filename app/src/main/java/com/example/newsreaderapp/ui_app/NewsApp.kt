package com.example.newsreaderapp.ui_app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.newsreaderapp.ui_app.screen.NewsRoute

@Composable
fun NewsApp() {
    MaterialTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            NewsRoute()
        }
    }
}