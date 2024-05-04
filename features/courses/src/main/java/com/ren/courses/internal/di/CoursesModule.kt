package com.ren.courses.internal.di

import com.ren.common.Mapper
import com.ren.courses.internal.data.mappers.CoursesMapper
import com.ren.courses.internal.data.repositories.CoursesDataRepository
import com.ren.courses.internal.domain.entities.courses.Courses
import com.ren.courses.internal.domain.repositories.CoursesRepository
import com.ren.forexapi.api.models.auth.NewsDTO
import com.ren.forexapi.api.models.courses.CoursesDTO
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface CoursesModule {

    @Binds
    fun bindNewsMapper(mapperImpl: CoursesMapper): Mapper<CoursesDTO, Courses>

    @Binds
    @ViewModelScoped
    fun bindCoursesRepository(repositoryImpl: CoursesDataRepository): CoursesRepository
}
