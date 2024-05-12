package com.ren.menu.internal.data.mappers

import com.ren.common.Mapper
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.models.profile.ProfileDTO
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.entities.profile.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor() : Mapper<ProfileDTO, Profile> {

    override fun to(model: ProfileDTO) = with(model) {
        Profile(
            user = user,
            avatar = avatar,
            username = username,
            phone = phone,
            email = email
        )
    }

    override fun from(model: Profile) = with(model) {
        ProfileDTO(
            user = user,
            avatar = avatar,
            username = username,
            phone = phone,
            email = email
        )
    }
}