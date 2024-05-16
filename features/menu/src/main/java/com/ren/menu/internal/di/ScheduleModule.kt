package com.ren.menu.internal.di

import com.ren.common.Mapper
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.forexapi.api.models.schedule.LessonsItemDTO
import com.ren.forexapi.api.models.schedule.ScheduleDTO
import com.ren.menu.internal.data.mappers.LessonsItemMapper
import com.ren.menu.internal.data.mappers.NewsMapper
import com.ren.menu.internal.data.mappers.ScheduleMapper
import com.ren.menu.internal.data.repositories.NewsDataRepository
import com.ren.menu.internal.data.repositories.ScheduleDataRepository
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.entities.schedule.LessonsItem
import com.ren.menu.internal.domain.entities.schedule.Schedule
import com.ren.menu.internal.domain.repositories.NewsRepository
import com.ren.menu.internal.domain.repositories.ScheduleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal interface ScheduleModule {

    @Binds
    fun bindScheduleMapper(mapperImpl: ScheduleMapper): Mapper<ScheduleDTO, Schedule>

    @Binds
    fun bindLessonsMapper(mapperImpl: LessonsItemMapper): Mapper<LessonsItemDTO, LessonsItem>

    @Binds
    @ViewModelScoped
    fun bindScheduleRepository(repositoryImpl: ScheduleDataRepository): ScheduleRepository
}