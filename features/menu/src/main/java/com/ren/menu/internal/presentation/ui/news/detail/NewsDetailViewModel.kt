package com.ren.menu.internal.presentation.ui.news.detail

import androidx.lifecycle.ViewModel
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.repositories.NewsRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseViewModel() {

    private val _newsState = MutableStateFlow<UIState<News>>(UIState.Loading)
    val newsState = _newsState.asStateFlow()

    init {
        newsRepository.fetchNewsDetail(NewsDetailFragment.endPoint).collectFlowAsState(_newsState)
    }
}