package com.example.newsreaderapp.ui_app.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newsreaderapp.ui_app.event.NewsUiEvent
import com.example.newsreaderapp.ui_app.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    viewModel: NewsViewModel,
    onEvent: (NewsUiEvent) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            uiState.error != null -> {
                ErrorView(
                    message = uiState.error!!, onRetry = { })
            }

            else -> {
                NewsList(
                    news = uiState.news,
                    isRefreshing = isRefreshing,
                    onRefresh = { onEvent(NewsUiEvent.Refresh) },
                    onNewClick = { onEvent(NewsUiEvent.OnNewsClick(it)) }
                )
            }
        }
    }
}
