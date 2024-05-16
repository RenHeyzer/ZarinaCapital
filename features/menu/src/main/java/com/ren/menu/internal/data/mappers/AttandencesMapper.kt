package com.ren.menu.internal.data.mappers

import com.ren.common.Mapper
import com.ren.forexapi.api.models.attandences.AttandencesDTO
import com.ren.forexapi.api.models.attandences.UserAttandencesDTO
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.models.profile.ProfileDTO
import com.ren.menu.internal.domain.entities.attandences.Attandences
import com.ren.menu.internal.domain.entities.attandences.UserAttandences
import com.ren.menu.internal.domain.entities.news.News
import javax.inject.Inject

internal class AttandencesMapper @Inject constructor(
    private val attandencesUserMapper: Mapper<UserAttandencesDTO,UserAttandences>
) : Mapper<AttandencesDTO, Attandences> {

    override fun to(model: AttandencesDTO) = with(model) {
        Attandences(
            lesson = lesson,
            id = id,
            status = status,
            user = attandencesUserMapper.to(user)
        )
    }

    override fun from(model: Attandences) = with(model) {
        AttandencesDTO(
            lesson = lesson,
            id = id,
            status = status,
            user = attandencesUserMapper.from(user)
        )
    }
}