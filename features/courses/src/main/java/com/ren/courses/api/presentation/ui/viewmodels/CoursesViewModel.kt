package com.ren.courses.api.presentation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ren.courses.entities.Course
import com.ren.courses.repositories.CoursesRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import javax.inject.Inject

class CoursesViewModel @Inject constructor(
    private val coursesRepository: CoursesRepository
) : BaseViewModel() {

    private val _coursesState = MutableLiveData<UIState<List<Course>>>(UIState.Loading)
    val coursesState: LiveData<UIState<List<Course>>> = _coursesState

    init {
        coursesRepository.fetchCourses().collectFlowAsState(_coursesState)
    }
}