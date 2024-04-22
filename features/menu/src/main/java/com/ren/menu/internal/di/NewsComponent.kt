package com.ren.menu.internal.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ren.di.keys.ViewModelKey
import com.ren.di.scopes.ScreenScope
import com.ren.forexapi.api.models.news.NewsDTO
import com.ren.menu.api.dependencies.NewsDeps
import com.ren.menu.api.domain.entities.News
import com.ren.menu.api.domain.repositories.NewsRepository
import com.ren.menu.internal.data.mappers.NewsMap
import com.ren.menu.internal.data.mappers.NewsMapper
import com.ren.menu.internal.data.repositories.NewsDataRepository
import com.ren.menu.internal.presentation.ui.news.viewmodel.NewsViewModel
import com.ren.presentation.base.ViewModelFactory
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@[ScreenScope Component(
    modules = [NewsModule::class],
    dependencies = [NewsDeps::class]
)]
internal interface NewsComponent {

    val viewModelFactory: ViewModelProvider.Factory

    @Component.Builder
    interface Builder {
        fun deps(deps: NewsDeps): Builder

        fun build(): NewsComponent
    }
}

@Module
internal interface NewsModule {

    @Binds
    fun bindNewsMapper(newsMapper: NewsMapper): NewsMap<NewsDTO, News>

    @Binds
    fun bindNewsRepository(newsDataRepository: NewsDataRepository): NewsRepository

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    fun newsViewModel(viewModel: NewsViewModel): ViewModel
}

// рашка лучший если че котко кирген жокмун