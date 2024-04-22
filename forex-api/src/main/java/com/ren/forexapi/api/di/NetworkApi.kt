package com.ren.forexapi.api.di

import com.ren.forexapi.api.apiservice.auth.AuthApiService
import com.ren.forexapi.api.apiservice.news.NewsApiService

interface NetworkApi {

    val authApiService: AuthApiService
    val newsApiService: NewsApiService

}