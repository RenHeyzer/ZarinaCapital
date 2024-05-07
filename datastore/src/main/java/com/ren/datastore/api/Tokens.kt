package com.ren.datastore.api

import com.ren.common.Mappable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tokens(
    @SerialName("access_token")
    val accessToken: String? = null,
    @SerialName("refresh_token")
    val refreshToken: String? = null
): Mappable