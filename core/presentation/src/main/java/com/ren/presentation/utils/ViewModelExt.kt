package com.ren.presentation.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ren.di.ComponentViewModel
import com.ren.di.ComponentViewModelFactory

inline fun <reified T : ViewModel> Fragment.buildViewModelWithFactory(
    factory: ComponentViewModelFactory<T>
): T {
    return ViewModelProvider(this, factory)[T::class.java]
}

fun <T> Fragment.component(creator: () -> T): Lazy<T> {
    return lazy {
        val factory = ComponentViewModelFactory { ComponentViewModel(creator()) }
        buildViewModelWithFactory(factory).component
    }
}