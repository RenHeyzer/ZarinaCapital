package com.ren.menu.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.data.base.BaseRepository
import com.ren.forexapi.api.models.lessons.LessonsDTO
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.service.lessons.LessonsApiService
import com.ren.forexapi.api.service.news.NewsApiService
import com.ren.menu.internal.domain.entities.lessons.Lessons
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.repositories.LessonsRepository
import com.ren.menu.internal.domain.repositories.NewsRepository
import javax.inject.Inject

class LessonsDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val lessonsMapper: Mapper<LessonsDTO, Lessons>,
    private val lessonsApiService: LessonsApiService
) : BaseRepository(appDispatchers), LessonsRepository {

    override fun fetchLessons(id:Int) = doRequest(
        request = {
            lessonsApiService.fetchLessons(id)
        },
        map = {
            lessonsMapper.to(it)
        }
    )
}