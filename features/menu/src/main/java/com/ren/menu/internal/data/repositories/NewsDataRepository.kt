package com.ren.menu.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Either
import com.ren.common.Mapper
import com.ren.data.base.BaseRepository
import com.ren.forexapi.api.apiservice.news.NewsApiService
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.menu.api.domain.entities.News
import com.ren.menu.api.domain.repositories.NewsRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class NewsDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val newsApiService: NewsApiService,
    @JvmSuppressWildcards private val newsMapper: Mapper<NewsDTO, News>
) : BaseRepository(appDispatchers), NewsRepository {

    override fun fetchNews() = doRequest(
        request ={
            newsApiService.fetchNews().results
        },
        map = {response->
            response.map(newsMapper::to)
        }
    )
}