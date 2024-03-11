package com.ren.zarinacapital

import android.app.Application
import com.ren.zarinacapital.di.AppComponent
import com.ren.zarinacapital.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}