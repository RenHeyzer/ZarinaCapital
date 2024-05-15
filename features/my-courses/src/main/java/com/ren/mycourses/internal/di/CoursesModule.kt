package com.ren.mycourses.internal.di

import com.ren.common.Mapper
import com.ren.mycourses.internal.data.mappers.MyCoursesMapper
import com.ren.mycourses.internal.data.repositories.MyCoursesDataRepository
import com.ren.forexapi.api.models.courses.CoursesDTO
import com.ren.mycourses.internal.domain.entities.courses.Courses
import com.ren.mycourses.internal.domain.repositories.MyCoursesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface CoursesModule {

    @Binds
    fun bindCoursesMapper(mapperImpl: MyCoursesMapper): Mapper<CoursesDTO, Courses>

    @Binds
    @ViewModelScoped
    fun bindCoursesRepository(repositoryImpl: MyCoursesDataRepository): MyCoursesRepository
}
