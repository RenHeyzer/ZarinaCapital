package com.ren.menu.internal.data.repositories

import com.ren.common.AppDispatchers
import com.ren.common.Mapper
import com.ren.data.base.BaseRepository
import com.ren.forexapi.api.models.attandences.AttandencesDTO
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.service.attandences.AttandencesApiService
import com.ren.forexapi.api.service.news.NewsApiService
import com.ren.menu.internal.domain.entities.attandences.Attandences
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.repositories.AttandencesRepository
import com.ren.menu.internal.domain.repositories.NewsRepository
import javax.inject.Inject

internal class AttandencesDataRepository @Inject constructor(
    private val appDispatchers: AppDispatchers,
    private val attandencesMapper: Mapper<AttandencesDTO, Attandences>,
    private val attandencesApiService: AttandencesApiService
) : BaseRepository(appDispatchers), AttandencesRepository {

    override fun fetchAttandences() = doRequest(
        request = {
            attandencesApiService.fetchAttandences().results
        },
        map = { news ->
           news.map(attandencesMapper::to)
        }
    )
}