package com.ren.menu.internal.presentation.ui.forex_course.exercise

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ren.common.Either
import com.ren.menu.internal.data.repositories.LessonsDataRepository
import com.ren.menu.internal.domain.entities.lessons.HomeworksItem
import com.ren.menu.internal.domain.repositories.ScheduleRepository
import com.ren.presentation.base.BaseViewModel
import com.ren.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val lessonRepository: LessonsDataRepository,
    private val scheduleRepository: ScheduleRepository
) :
    BaseViewModel() {

    private var _exercisesState = MutableStateFlow<UIState<List<HomeworksItem>>>(UIState.Loading)
    val exercisesState = _exercisesState.asStateFlow()

    init {
        getLessonId()
    }

    private fun getLessonId() {
        viewModelScope.launch {
            scheduleRepository.fetchSchedule().collect { either ->
                when (either) {
                    is Either.Left -> {
                        _exercisesState.value = UIState.Error(message = either.left?.message)
                        Log.e("exercise", either.left?.message.toString() )
                    }

                    is Either.Right -> {
                        val lessonIdList = mutableListOf<Int>()
                        either.right?.forEach { schedule ->
                            schedule.lessons?.forEach { lesson ->
                                lessonIdList.add(lesson.id)
                            }
                        }
                        getExerciseByLessonId(lessonIdList)
                    }
                }
            }
        }
    }

    private fun getExerciseByLessonId(idList: List<Int>) {
        viewModelScope.launch {
            val homeworksItemList = mutableListOf<HomeworksItem>()
            idList.forEach { id ->
                lessonRepository.fetchLessons(id).collect { either ->
                    when (either) {
                        is Either.Left -> {
                            _exercisesState.value =
                                UIState.Error(message = either.left?.message.toString())
                        }

                        is Either.Right -> {
                            homeworksItemList += either.right?.homeworks as List<HomeworksItem>
                        }
                    }
                }
            }
            _exercisesState.value =
                UIState.Success(data = homeworksItemList)
        }
    }
}