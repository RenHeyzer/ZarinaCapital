package com.ren.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ren.di.scopes.ScreenScope
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@ScreenScope
class ViewModelFactory @Inject constructor(
    @JvmSuppressWildcards private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModelProvider = viewModels[modelClass]
            ?: throw IllegalArgumentException("ViewModel $modelClass not found")
        return viewModelProvider.get() as T
    }
}