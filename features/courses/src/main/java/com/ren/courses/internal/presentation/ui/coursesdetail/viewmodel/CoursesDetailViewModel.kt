package com.ren.courses.internal.presentation.ui.coursesdetail.viewmodel

import com.ren.courses.internal.domain.entities.courses.detail.CoursesDetail
import com.ren.courses.internal.domain.repositories.CoursesDetailRepository
import com.ren.courses.internal.presentation.ui.coursesdetail.fragment.CoursesDetailFragment
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CoursesDetailViewModel @Inject constructor(
    private val coursesDetailRepository: CoursesDetailRepository
) : BaseViewModel() {

    private val _coursesDetailState = MutableStateFlow<UIState<CoursesDetail>>(UIState.Loading)
    val coursesDetailState = _coursesDetailState.asStateFlow()

    init {
        coursesDetailRepository.detailCourses(CoursesDetailFragment.endPoint).collectFlowAsState(_coursesDetailState)
    }
}