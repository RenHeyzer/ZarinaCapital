package com.ren.menu.internal.presentation.ui.news

import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.repositories.NewsRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseViewModel() {

    private val _newsState = MutableStateFlow<UIState<List<News>>>(UIState.Loading)
    val newsState = _newsState.asStateFlow()

    init {
        newsRepository.fetchNews().collectFlowAsState(_newsState)
    }
}