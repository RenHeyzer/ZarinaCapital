package com.ren.menu.internal.di

import com.ren.common.Mapper
import com.ren.forexapi.api.models.lessons.HomeworksItemDTO
import com.ren.forexapi.api.models.lessons.LessonsDTO
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.menu.internal.data.mappers.HomeWorksMapper
import com.ren.menu.internal.data.mappers.LessonMapper
import com.ren.menu.internal.data.mappers.NewsMapper
import com.ren.menu.internal.data.repositories.LessonsDataRepository
import com.ren.menu.internal.data.repositories.NewsDataRepository
import com.ren.menu.internal.domain.entities.lessons.HomeworksItem
import com.ren.menu.internal.domain.entities.lessons.Lessons
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.repositories.LessonsRepository
import com.ren.menu.internal.domain.repositories.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal interface LessonsModule {

    @Binds
    fun bindLessonsHomeMapper(mapperImpl: LessonMapper): Mapper<LessonsDTO, Lessons>

    @Binds
    fun bindHomeWorkMapper(mapperImpl: HomeWorksMapper): Mapper<HomeworksItemDTO, HomeworksItem>

    @Binds
    @ViewModelScoped
    fun bindLessonsRepository(repositoryImpl: LessonsDataRepository): LessonsRepository
}