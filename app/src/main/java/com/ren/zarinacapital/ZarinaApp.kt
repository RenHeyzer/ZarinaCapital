package com.ren.zarinacapital

import android.app.Application
import com.ren.di.dependencies.FeatureDependenciesProvider
import com.ren.di.dependencies.HasFeatureDependencies
import com.ren.forexapi.impl.di.DaggerNetworkComponent
import com.ren.zarinacapital.di.AppComponent
import com.ren.zarinacapital.di.DaggerAppComponent

class ZarinaApp : Application(), HasFeatureDependencies {

    override lateinit var featureDependencies: FeatureDependenciesProvider

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .applicationContext(applicationContext)
            .networkApi(DaggerNetworkComponent.factory().create(BuildConfig.FOREX_BASE_URL))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        featureDependencies = appComponent.featureDependencies
    }
}