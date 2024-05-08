package com.ren.forexapi.api.service.news

import com.ren.forexapi.api.models.ForexResponse
import com.ren.forexapi.api.models.auth.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Path

private const val END_POINT = "news/"
private const val END_POINT_DETAIL = "news/{id}"

interface NewsApiService {

    @GET(END_POINT)
    suspend fun fetchNews(): ForexResponse<NewsDTO>

    @GET(END_POINT_DETAIL)
    suspend fun fetchNewsDetail(
        @Path("id") id: Int
    ): NewsDTO
}