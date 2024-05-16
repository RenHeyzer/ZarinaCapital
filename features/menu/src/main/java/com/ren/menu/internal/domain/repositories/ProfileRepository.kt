package com.ren.menu.internal.domain.repositories

import com.ren.common.Either
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.entities.profile.PUTProfile
import com.ren.menu.internal.domain.entities.profile.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun fetchProfile(): Flow<Either<Throwable, Profile>>

    suspend fun putProfile(data: PUTProfile): Profile
}