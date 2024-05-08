package com.ren.forexapi.api.models.auth

import com.google.gson.annotations.SerializedName
import com.ren.common.Mappable

data class NewsDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("start_datetime")
    val startDatetime: String
) : Mappable
