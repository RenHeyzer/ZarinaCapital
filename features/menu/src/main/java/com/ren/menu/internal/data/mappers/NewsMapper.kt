package com.ren.menu.internal.data.mappers

import com.ren.common.Mapper
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.models.profile.ProfileDTO
import com.ren.menu.internal.domain.entities.news.News
import javax.inject.Inject

internal class NewsMapper @Inject constructor() : Mapper<NewsDTO, News> {

    override fun to(model: NewsDTO) = with(model) {
        News(
            id = id,
            title = title,
            image = image,
            startDatetime = startDatetime,
            description = description
        )
    }

    override fun from(model: News) = with(model) {
        NewsDTO(
            id = id,
            title = title,
            image = image,
            startDatetime = startDatetime,
            description = description
        )
    }
}