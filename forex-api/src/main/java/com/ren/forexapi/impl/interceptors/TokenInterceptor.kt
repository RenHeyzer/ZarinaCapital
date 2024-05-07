package com.ren.forexapi.impl.interceptors

import com.ren.datastore.api.TokenManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class TokenInterceptor @Inject constructor(
    private val tokenManager: TokenManager
): Interceptor {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getTokens().first().accessToken
        }
        val request = chain.request().newBuilder()
        request.addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
        return chain.proceed(request.build())
    }
}