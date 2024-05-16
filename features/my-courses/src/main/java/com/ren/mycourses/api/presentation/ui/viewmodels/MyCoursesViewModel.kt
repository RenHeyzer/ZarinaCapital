package com.ren.mycourses.api.presentation.ui.viewmodels

import com.ren.mycourses.internal.domain.repositories.MyCoursesRepository
import com.ren.mycourses.internal.domain.entities.courses.Courses
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyCoursesViewModel @Inject constructor(
    private val coursesRepository: MyCoursesRepository
) : BaseViewModel() {

    private val _coursesState = MutableStateFlow<UIState<List<Courses>>>(UIState.Loading)
    val coursesState = _coursesState.asStateFlow()

    init {
        coursesRepository.fetchCourses().collectFlowAsState(_coursesState
        )
    }
}