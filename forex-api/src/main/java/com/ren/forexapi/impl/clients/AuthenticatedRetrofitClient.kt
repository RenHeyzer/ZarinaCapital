package com.ren.forexapi.impl.clients

import com.ren.forexapi.api.service.auth.AuthApiService
import com.ren.forexapi.api.service.profile.ProfileApiService
import com.ren.forexapi.api.service.raviews.ReviewsApiService
import com.ren.forexapi.impl.interceptors.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

internal class AuthenticatedRetrofitClient(
    private val baseUrl: String,
    private val tokenInterceptor: TokenInterceptor
) {

    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(tokenInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val profileApiService: ProfileApiService = retrofit.create()

}