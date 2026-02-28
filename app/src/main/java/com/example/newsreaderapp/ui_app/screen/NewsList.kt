package com.example.newsreaderapp.ui_app.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newsreaderapp.ui_app.model.NewsUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsList(
    news : List<NewsUiModel>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onNewClick: (String) -> Unit,
) {
    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(), isRefreshing = isRefreshing, onRefresh = {
            onRefresh()
        }, contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(news) { item ->
                NewsItem(
                    news = item, onClick = {
                       onNewClick(item.title)
                    })
            }
        }
    }
}