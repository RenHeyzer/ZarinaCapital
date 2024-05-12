package com.ren.menu.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.data.base.BaseRepository
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.models.profile.ProfileDTO
import com.ren.forexapi.api.service.news.NewsApiService
import com.ren.forexapi.api.service.profile.ProfileApiService
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.entities.profile.Profile
import com.ren.menu.internal.domain.repositories.NewsRepository
import com.ren.menu.internal.domain.repositories.ProfileRepository
import javax.inject.Inject

class ProfileDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val profileMapper: Mapper<ProfileDTO, Profile>,
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
}