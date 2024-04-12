package com.ren.forexapi.api.di

import com.ren.forexapi.api.auth.AuthApiService

interface NetworkApi {

    val authApiService: AuthApiService
}