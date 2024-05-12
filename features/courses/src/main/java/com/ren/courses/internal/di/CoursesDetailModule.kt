package com.ren.courses.internal.di

import com.ren.common.Mapper
import com.ren.courses.internal.data.mappers.CoursesAuthorMapper
import com.ren.courses.internal.data.mappers.CoursesDetailMapper
import com.ren.courses.internal.data.mappers.CoursesLecturesItemMapper
import com.ren.courses.internal.data.mappers.CoursesReviewsItemMapper
import com.ren.courses.internal.data.mappers.CoursesUserMapper
import com.ren.courses.internal.data.repositories.DetailCoursesDataRepository
import com.ren.courses.internal.domain.entities.courses.detail.CoursesAuthor
import com.ren.courses.internal.domain.entities.courses.detail.CoursesDetail
import com.ren.courses.internal.domain.entities.courses.detail.CoursesLecturesItem
import com.ren.courses.internal.domain.entities.courses.detail.CoursesReviewsItem
import com.ren.courses.internal.domain.entities.courses.detail.CoursesUser
import com.ren.courses.internal.domain.repositories.CoursesDetailRepository
import com.ren.forexapi.api.models.courses.detail.CoursesAuthorDTO
import com.ren.forexapi.api.models.courses.detail.CoursesDetailDTO
import com.ren.forexapi.api.models.courses.detail.CoursesLecturesItemDTO
import com.ren.forexapi.api.models.courses.detail.CoursesReviewsItemDTO
import com.ren.forexapi.api.models.courses.detail.CoursesUserDTO
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface CoursesDetailModule {

    @Binds
    fun bindCoursesDetailMapper(mapperImpl: CoursesDetailMapper): Mapper<CoursesDetailDTO, CoursesDetail>

    @Binds
    fun bindCoursesAuthorDetailMapper(mapperImpl: CoursesAuthorMapper): Mapper<CoursesAuthorDTO, CoursesAuthor>

    @Binds
    fun bindCoursesLecturesDetailMapper(mapperImpl: CoursesLecturesItemMapper): Mapper<CoursesLecturesItemDTO, CoursesLecturesItem>

    @Binds
    fun bindCoursesReviewsDetailMapper(mapperImpl: CoursesReviewsItemMapper): Mapper<CoursesReviewsItemDTO, CoursesReviewsItem>

    @Binds
    fun bindCoursesUserDetailMapper(mapperImpl: CoursesUserMapper): Mapper<CoursesUserDTO, CoursesUser>

    @Binds
    @ViewModelScoped
    fun bindCoursesDetailRepository(repositoryImpl: DetailCoursesDataRepository): CoursesDetailRepository
}
