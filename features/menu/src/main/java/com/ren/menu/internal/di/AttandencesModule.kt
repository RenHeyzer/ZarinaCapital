package com.ren.menu.internal.di

import com.ren.common.Mapper
import com.ren.forexapi.api.models.attandences.AttandencesDTO
import com.ren.forexapi.api.models.attandences.UserAttandencesDTO
import com.ren.menu.internal.data.mappers.AttandencesMapper
import com.ren.menu.internal.data.mappers.AttandencesUserMapper
import com.ren.menu.internal.data.repositories.AttandencesDataRepository
import com.ren.menu.internal.data.repositories.NewsDataRepository
import com.ren.menu.internal.domain.entities.attandences.Attandences
import com.ren.menu.internal.domain.entities.attandences.UserAttandences
import com.ren.menu.internal.domain.repositories.AttandencesRepository
import com.ren.menu.internal.domain.repositories.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal interface AttandencesModule {

    @Binds
    fun bindAttandencesMapper(mapperImpl: AttandencesMapper): Mapper<AttandencesDTO, Attandences>

    @Binds
    fun bindAttandencesUserMapper(mapperImpl: AttandencesUserMapper): Mapper<UserAttandencesDTO, UserAttandences>

    @Binds
    @ViewModelScoped
    fun bindAttandencesRepository(repositoryImpl: AttandencesDataRepository): AttandencesRepository

}