package com.ren.forexapi.api.news

import com.ren.forexapi.api.models.ForexResponse
import com.ren.forexapi.api.models.auth.NewsDTO
import retrofit2.http.GET

private const val END_POINT = "news/"

interface NewsApiService {

    @GET(END_POINT)
    suspend fun fetchNews(): ForexResponse<NewsDTO>
}