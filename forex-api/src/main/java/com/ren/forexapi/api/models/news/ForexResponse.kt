package com.ren.forexapi.api.models.news

import com.google.gson.annotations.SerializedName

data class ForexResponse<T> (
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val results: List<T>
)