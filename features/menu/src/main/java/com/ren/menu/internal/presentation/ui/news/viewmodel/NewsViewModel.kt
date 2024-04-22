package com.ren.menu.internal.presentation.ui.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ren.di.scopes.ScreenScope
import com.ren.menu.api.domain.entities.News
import com.ren.menu.api.domain.repositories.NewsRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import javax.inject.Inject

@ScreenScope
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseViewModel() {

    init {
        getNews()
    }

    private val _newsState = MutableLiveData<UIState<List<News>>>(UIState.Loading)
    val newsState: LiveData<UIState<List<News>>> = _newsState

    private fun getNews() {
       newsRepository.fetchNews().collectFlowAsState(_newsState)
    }
}