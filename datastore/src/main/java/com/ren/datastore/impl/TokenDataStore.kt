package com.ren.datastore.impl

import android.content.Context
import androidx.datastore.dataStore
import com.ren.datastore.api.TokenManager
import com.ren.datastore.api.Tokens
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

private const val TOKENS_DATA_STORE_FILE_NAME = "tokens.json"

@Singleton
internal class TokenDataStore @Inject constructor(
    @ApplicationContext private val context: Context,
    private val tokensSerializer: TokensSerializer
) : TokenManager {

    private val Context.dataStore by dataStore(
        fileName = TOKENS_DATA_STORE_FILE_NAME,
        serializer = tokensSerializer
    )

    override suspend fun saveTokens(tokens: Tokens) {
        context.dataStore.updateData { tokens }
    }

    override fun getTokens(): Flow<Tokens> = context.dataStore.data

    override suspend fun clearAllToken() {
        context.dataStore.updateData { Tokens() }
    }
}