package com.ren.menu.api.domain.entities

import com.ren.common.Mappable

data class News(
    val id: Int ,
    val title: String ,
    val image: String,
    val startDatetime: String
) : Mappable