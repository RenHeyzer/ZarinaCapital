package com.ren.auth.internal.data.mappers

import com.ren.common.Mapper
import com.ren.datastore.api.Tokens
import com.ren.forexapi.api.models.LoginResponse
import javax.inject.Inject

class TokensMapper @Inject constructor() : Mapper<LoginResponse, Tokens> {

    override fun to(model: LoginResponse) = with(model) {
        Tokens(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    override fun from(model: Tokens) = with(model) {
        LoginResponse(
            status = 0,
            accessToken = accessToken ?: "",
            refreshToken = refreshToken ?: "",
        )
    }
}