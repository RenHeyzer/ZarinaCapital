package com.ren.menu.internal.di

import com.ren.common.Mapper
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.menu.internal.data.mappers.NewsMapper
import com.ren.menu.internal.data.repositories.NewsDataRepository
import com.ren.menu.internal.domain.entities.news.News
import com.ren.menu.internal.domain.repositories.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal interface NewsModule {

    @Binds
    fun bindNewsMapper(mapperImpl: NewsMapper): Mapper<NewsDTO, News>

    @Binds
    @ViewModelScoped
    fun bindNewsRepository(repositoryImpl: NewsDataRepository): NewsRepository
}