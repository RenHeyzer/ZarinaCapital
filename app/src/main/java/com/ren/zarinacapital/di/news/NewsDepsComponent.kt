package com.ren.zarinacapital.di.news

import android.content.Context
import com.ren.common.AppDispatchers
import com.ren.forexapi.api.apiservice.news.NewsApiService
import com.ren.menu.api.dependencies.NewsDeps
import com.ren.presentation.utils.ExceptionMessages
import dagger.BindsInstance
import dagger.Component

interface NewsDepsComponent : NewsDeps {

    override val exceptionMessages: ExceptionMessages
    override val appDispatchers: AppDispatchers
    override val newsApiService: NewsApiService

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun appDispatchers(appDispatchers: AppDispatchers): Builder

        @BindsInstance
        fun newsApiService(newsApiService: NewsApiService): Builder

        fun build(): NewsDepsComponent
    }
}