package com.ren.menu.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.data.base.BaseRepository
import com.ren.forexapi.api.models.profile.PUTProfileDTO
import com.ren.forexapi.api.models.profile.ProfileDTO
import com.ren.forexapi.api.service.profile.ProfileApiService
import com.ren.menu.internal.domain.entities.profile.PUTProfile
import com.ren.menu.internal.domain.entities.profile.Profile
import com.ren.menu.internal.domain.repositories.ProfileRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val profileMapper: Mapper<ProfileDTO, Profile>,
    private val putProfileMapper: Mapper<PUTProfileDTO, PUTProfile>,
    private val profileApiService: ProfileApiService
) : BaseRepository(appDispatchers), ProfileRepository {

    override fun fetchProfile() = doRequest(
        request = {
            profileApiService.fetchProfile()
        },
        map = { news ->
            profileMapper.to(news)
        }
    )

    override suspend fun putProfile(data: PUTProfile): Profile {
        val profile = withContext(appDispatchers.io) {
            profileApiService.putProfile(
                putProfileMapper.from(data)
            )
        }
        return profileMapper.to(profile)
    }
}