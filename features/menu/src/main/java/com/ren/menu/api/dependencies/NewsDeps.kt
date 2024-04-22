package com.ren.menu.api.dependencies

import com.ren.common.AppDispatchers
import com.ren.di.dependencies.FeatureDependencies
import com.ren.forexapi.api.apiservice.news.NewsApiService
import com.ren.presentation.utils.ExceptionMessages

interface NewsDeps : FeatureDependencies {
    val exceptionMessages: ExceptionMessages
    val appDispatchers: AppDispatchers
    val newsApiService: NewsApiService
}