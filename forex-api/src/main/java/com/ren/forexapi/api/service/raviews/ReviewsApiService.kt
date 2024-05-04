package com.ren.forexapi.api.service.raviews

import com.ren.forexapi.api.models.ForexResponse
import com.ren.forexapi.api.models.auth.NewsDTO
import com.ren.forexapi.api.models.reviews.ReviewsDTO
import retrofit2.http.GET

private const val END_POINT = "reviews/"

interface ReviewsApiService {

    @GET(END_POINT)
    suspend fun fetchReviews(): ForexResponse<ReviewsDTO>
}