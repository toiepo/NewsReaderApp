package com.example.newsreaderapp.ui_app.screen

import NewsDetailScreen
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsreaderapp.ui_app.event.NewsUiEvent
import com.example.newsreaderapp.ui_app.viewmodel.NewsViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NewsRoute(
    viewModel: NewsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedNews by viewModel.selectedNews.collectAsState()

    AnimatedContent(
        targetState = selectedNews, transitionSpec = {
            if (targetState != null) {
                // List -> Detail
                slideInHorizontally { fullWidth -> fullWidth } + fadeIn() with slideOutHorizontally { fullWidth -> -fullWidth } + fadeOut()
            } else {
                // Detail -> List (Back)
                slideInHorizontally { fullWidth -> -fullWidth } + fadeIn() with slideOutHorizontally { fullWidth -> fullWidth } + fadeOut()
            }.using(SizeTransform(clip = false))
        }, label = "ListToDetailAnimation"
    ) { selectedNews ->

        if (selectedNews == null) {
            NewsListScreen(
                viewModel, onEvent = viewModel::onEvent
            )
        } else {
            val news = uiState.news.first { it.title == selectedNews.title }

            NewsDetailScreen(
                news = news, onBack = {
                    viewModel.onEvent(NewsUiEvent.OnBackClick)
                })
        }
    }

}