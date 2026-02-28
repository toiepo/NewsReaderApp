package com.example.newsreaderapp.ui_app.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.newsreaderapp.ui_app.model.NewsUiModel

@Composable
fun NewsList(
    articles: List<NewsUiModel>,
    onItemClick: (NewsUiModel) -> Unit,
) {
    LazyColumn {
        items(articles) { article ->
            NewsItem(
                news = article, onClick = { onItemClick(article) })
        }
    }
}