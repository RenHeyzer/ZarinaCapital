package com.ren.menu.internal.domain.entities.news

import com.ren.common.Mappable

data class News(
    val id: Int,
    val title: String,
    val image: String,
    val startDatetime: String
) : Mappable