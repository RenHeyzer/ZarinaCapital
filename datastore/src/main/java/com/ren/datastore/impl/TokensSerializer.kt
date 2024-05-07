package com.ren.datastore.impl

import androidx.datastore.core.Serializer
import com.ren.datastore.api.Tokens
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

internal class TokensSerializer @Inject constructor(private val cryptoManager: CryptoManager) :
    Serializer<Tokens> {

    override val defaultValue: Tokens
        get() = Tokens()

    override suspend fun readFrom(input: InputStream): Tokens {
        val decryptedBytes = cryptoManager.decrypt(input)
        return try {
            Json.decodeFromString(
                deserializer = Tokens.serializer(),
                string = decryptedBytes.decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: Tokens, output: OutputStream) {
        cryptoManager.encrypt(
            bytes = Json.encodeToString(
                serializer = Tokens.serializer(),
                value = t
            ).encodeToByteArray(),
            outputStream = output
        )
    }
}