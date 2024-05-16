package com.ren.menu.internal.domain.repositories

import com.ren.common.Either
import com.ren.menu.internal.domain.entities.lessons.Lessons
import kotlinx.coroutines.flow.Flow

interface LessonsRepository {

    fun fetchLessons(id: Int): Flow<Either<Throwable, Lessons>>
}