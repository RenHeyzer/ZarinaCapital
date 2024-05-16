package com.ren.menu.internal.data.mappers

import com.ren.common.Mapper
import com.ren.forexapi.api.models.profile.PUTProfileDTO
import com.ren.menu.internal.domain.entities.profile.PUTProfile
import javax.inject.Inject

class PUTProfileMapper @Inject constructor() : Mapper<PUTProfileDTO, PUTProfile> {

    override fun to(model: PUTProfileDTO) = with(model) {
        PUTProfile(
            username = username,
            prefix = "+996",
            phone = phone,
            email = email
        )
    }

    override fun from(model: PUTProfile) = with(model) {
        PUTProfileDTO(
            username = username,
            phone = "$prefix$phone",
            email = email
        )
    }
}