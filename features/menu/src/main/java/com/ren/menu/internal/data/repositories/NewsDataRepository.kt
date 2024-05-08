package com.ren.menu.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.data.base.BaseRepository
import com.ren.forexapi.api.models.auth.NewsDTO
import com.ren.forexapi.api.service.news.NewsApiService
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.repositories.NewsRepository
import javax.inject.Inject

internal class NewsDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val newsMapper: Mapper<NewsDTO, News>,
    private val newsApiService: NewsApiService
) : BaseRepository(appDispatchers), NewsRepository {

    override fun fetchNews() = doRequest(
        request = {
            newsApiService.fetchNews().results
        },
        map = { news ->
           news.map(newsMapper::to)
        }
    )

    override fun fetchNewsDetail(id:Int) = doRequest(
        request = {
            newsApiService.fetchNewsDetail(id)
        },
        map = { news ->
            newsMapper.to(news)
        }
    )
}