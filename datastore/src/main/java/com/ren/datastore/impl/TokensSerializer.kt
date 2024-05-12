package com.ren.datastore.impl

import androidx.datastore.core.Serializer
import com.ren.datastore.api.Tokens
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

internal class TokensSerializer @Inject constructor() :
    Serializer<Tokens> {

    override val defaultValue: Tokens
        get() = Tokens()

    override suspend fun readFrom(input: InputStream): Tokens {
        return try {
            Json.decodeFromString(
                deserializer = Tokens.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: Tokens, output: OutputStream) {
        output.write(Json.encodeToString(Tokens.serializer(), t).encodeToByteArray())
    }
}