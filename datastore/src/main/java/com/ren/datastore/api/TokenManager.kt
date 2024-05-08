package com.ren.datastore.api

import kotlinx.coroutines.flow.Flow

interface TokenManager {

    suspend fun saveTokens(tokens: Tokens)
    fun getTokens(): Flow<Tokens>
    suspend fun clearAllToken()
}