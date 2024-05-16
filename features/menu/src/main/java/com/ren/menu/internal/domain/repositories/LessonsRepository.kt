package com.ren.menu.internal.domain.repositories

import com.ren.common.Either
import com.ren.forexapi.api.models.lessons.LessonsDTO
import com.ren.menu.internal.domain.entities.lessons.Lessons
import com.ren.menu.internal.domain.entities.news.News
import kotlinx.coroutines.flow.Flow

interface LessonsRepository {

    fun fetchLessons(id:Int): Flow<Either<Throwable, Lessons>>
}