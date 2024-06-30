package com.ren.menu.internal.domain.entities.profile

import android.net.Uri
import com.ren.common.Mappable

data class PUTProfile(
    val username: String,
    val avatar: String? = null,
    val prefix: String,
    val phone: String,
    val email: String
):Mappable
