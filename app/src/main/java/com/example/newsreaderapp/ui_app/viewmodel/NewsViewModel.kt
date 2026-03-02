package com.example.newsreaderapp.ui_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreaderapp.ui_app.event.NewsUiEvent
import com.example.newsreaderapp.ui_app.interfaces.NewsRepository
import com.example.newsreaderapp.ui_app.model.NewsUiModel
import com.example.newsreaderapp.ui_app.state.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState(isLoading = true))
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()
    private val _navigationEvent = MutableSharedFlow<Unit>()
    val navigationEvent: SharedFlow<Unit> = _navigationEvent.asSharedFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()
    private val _selectedNews = MutableStateFlow<NewsUiModel?>(null)
    val selectedNews: StateFlow<NewsUiModel?> = _selectedNews

    init {
        viewModelScope.launch {
            refresh()
            observeNews()
        }
    }

    fun onEvent(event: NewsUiEvent) {
        when (event) {
            is NewsUiEvent.OnNewsClick -> {
                _selectedNews.value = _uiState.value.news.firstOrNull { it.title == event.title }
            }

            NewsUiEvent.OnBackClick -> {
                _selectedNews.value = null
                viewModelScope.launch {
                    _navigationEvent.emit(Unit)
                }
            }

            NewsUiEvent.Refresh -> {
                refresh()
            }
        }
    }

    private fun observeNews() {
        viewModelScope.launch {
            repository.getNews().collect { newsList ->
                _uiState.update { currentState ->
                    currentState.copy(
                        news = newsList.map { news ->
                            NewsUiModel(
                                title = news.title,
                                description = news.description,
                                imageUrl = news.imageUrl,
                                url = news.url,
                                publishedAt = news.publishedAt,
                                sourceName = news.sourceName
                            )
                        }, isLoading = false
                    )
                }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            repository.refreshNews()
            _isRefreshing.value = false
        }
    }
}