package com.ren.forexapi.api.apiservice.news

import com.ren.forexapi.api.models.news.ForexResponse
import com.ren.forexapi.api.models.news.NewsDTO
import retrofit2.http.GET

private const val NEWS_END_POINT = "news/"

interface NewsApiService {

    @GET(NEWS_END_POINT)
    suspend fun fetchNews(): ForexResponse<NewsDTO>
}