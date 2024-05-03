package com.ren.menu.internal.domain.repositories

import com.ren.common.Either
import com.ren.menu.internal.domain.entities.news.News
import kotlinx.coroutines.flow.Flow

internal interface NewsRepository {

    fun fetchNews(): Flow<Either<Throwable, List<News>>>
}