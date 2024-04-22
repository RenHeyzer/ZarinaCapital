package com.ren.menu.internal.data.mappers

import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.menu.api.domain.entities.News
import javax.inject.Inject

internal class NewsMapper @Inject constructor() :
    NewsMap<NewsDTO, News> {

    override fun to(model: NewsDTO) = News(
        id = model.id,
        title = model.title,
        image = model.image,
        startDatetime = model.startDatetime
    )

    override fun from(model: News) = NewsDTO(
        id = model.id,
        title = model.title,
        image = model.image,
        startDatetime = model.startDatetime
    )
}