package com.ren.menu.internal.presentation.ui.forex_course.detail

import com.ren.menu.internal.domain.entities.lessons.Lessons
import com.ren.menu.internal.domain.repositories.LessonsRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LessonsDetailViewModel @Inject constructor(
    private val lessonsRepository: LessonsRepository
) : BaseViewModel() {

    private val _lessonsState = MutableStateFlow<UIState<Lessons>>(UIState.Loading)
    val lessonsState = _lessonsState.asStateFlow()

    init {
//        lessonsRepository.fetchLessons(LessonsDetailFragment.endPoint).collectFlowAsState(_lessonsState)
    }
}