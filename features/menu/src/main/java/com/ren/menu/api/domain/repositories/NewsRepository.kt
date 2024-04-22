package com.ren.menu.api.domain.repositories

import com.ren.common.Either
import com.ren.forexapi.api.models.news.ForexResponse
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.menu.api.domain.entities.News
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    fun fetchNews(): Flow<Either<Throwable, List<News>>>

}