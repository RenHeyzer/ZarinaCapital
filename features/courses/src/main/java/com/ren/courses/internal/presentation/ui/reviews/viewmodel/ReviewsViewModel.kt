package com.ren.courses.internal.presentation.ui.reviews.viewmodel

import com.ren.courses.internal.domain.entities.reviews.Reviews
import com.ren.courses.internal.domain.repositories.ReviewsRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ReviewsViewModel @Inject constructor(
    private val reviewsRepository: ReviewsRepository
) : BaseViewModel() {

    private val _reviewsState = MutableStateFlow<UIState<List<Reviews>>>(UIState.Loading)
    val reviewsState = _reviewsState.asStateFlow()

    init {
        reviewsRepository.fetchReviews().collectFlowAsState(_reviewsState)
    }
}