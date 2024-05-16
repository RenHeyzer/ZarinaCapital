package com.ren.menu.internal.data.mappers

import com.ren.common.Mapper
import com.ren.forexapi.api.models.attandences.UserAttandencesDTO
import com.ren.menu.internal.domain.entities.attandences.UserAttandences
import javax.inject.Inject

internal class AttandencesUserMapper @Inject constructor() :
    Mapper<UserAttandencesDTO, UserAttandences> {

    override fun to(model: UserAttandencesDTO) = with(model) {
        UserAttandences(
            id = id,
            totalAttendances = totalAttendances,
            username = username
        )
    }

    override fun from(model: UserAttandences) = with(model) {
        UserAttandencesDTO(
            id = id,
            totalAttendances = totalAttendances,
            username = username
        )
    }
}