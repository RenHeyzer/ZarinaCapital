package com.ren.menu.internal.data.mappers

import com.ren.common.Mapper
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.models.profile.PUTProfileDTO
import com.ren.forexapi.api.models.profile.ProfileDTO
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.entities.profile.PUTProfile
import com.ren.menu.internal.domain.entities.profile.Profile
import javax.inject.Inject

class PUTProfileMapper @Inject constructor() : Mapper<PUTProfileDTO, PUTProfile> {

    override fun to(model: PUTProfileDTO) = with(model) {
        PUTProfile(
            username = username,
            phone = phone,
            email = email
        )
    }

    override fun from(model: PUTProfile) = with(model) {
        PUTProfileDTO(
            username = username,
            phone = phone,
            email = email
        )
    }
}