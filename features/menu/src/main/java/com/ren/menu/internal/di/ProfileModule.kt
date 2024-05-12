package com.ren.menu.internal.di

import com.ren.common.Mapper
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.models.profile.ProfileDTO
import com.ren.menu.internal.data.mappers.NewsMapper
import com.ren.menu.internal.data.mappers.ProfileMapper
import com.ren.menu.internal.data.repositories.NewsDataRepository
import com.ren.menu.internal.data.repositories.ProfileDataRepository
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.entities.profile.Profile
import com.ren.menu.internal.domain.repositories.NewsRepository
import com.ren.menu.internal.domain.repositories.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal interface ProfileModule {

    @Binds
    fun bindProfileMapper(mapperImpl: ProfileMapper): Mapper<ProfileDTO, Profile>

    @Binds
    @ViewModelScoped
    fun bindProfileRepository(repositoryImpl: ProfileDataRepository): ProfileRepository
}