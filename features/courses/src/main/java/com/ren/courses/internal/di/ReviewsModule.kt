package com.ren.courses.internal.di

import com.ren.common.Mapper
import com.ren.courses.internal.data.mappers.ReviewsMapper
import com.ren.courses.internal.data.mappers.ReviewsUserMapper
import com.ren.courses.internal.data.repositories.ReviewsDataRepository
import com.ren.courses.internal.domain.entities.reviews.Reviews
import com.ren.courses.internal.domain.entities.reviews.ReviewsUser
import com.ren.courses.internal.domain.repositories.ReviewsRepository
import com.ren.forexapi.api.models.reviews.ReviewsDTO
import com.ren.forexapi.api.models.reviews.ReviewsUserDTO
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface ReviewsModule {

    @Binds
    fun bindReviewsMapper(mapperImpl: ReviewsMapper): Mapper<ReviewsDTO, Reviews>

    @Binds
    fun bindReviewsUserMapper(mapperImpl: ReviewsUserMapper): Mapper<ReviewsUserDTO, ReviewsUser>

    @Binds
    @ViewModelScoped
    fun bindReviewsRepository(repositoryImpl: ReviewsDataRepository): ReviewsRepository
}
