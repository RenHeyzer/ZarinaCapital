package com.ren.di

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.navGraphViewModels

class ComponentViewModel : ViewModel() {

    var component: Any? = null

    override fun onCleared() {
        component = null
    }
}

fun <T> ViewModelStoreOwner.getComponent(createComponent: () -> T): T {
    val viewModel = ViewModelProvider(this)[ComponentViewModel::class.java]
    viewModel.apply {
        if (component == null) {
            component = createComponent()
        }
    }

    @Suppress("UNCHECKED_CAST")
    return viewModel.component as T
}

fun <T> Fragment.getComponent(@IdRes navGraphId: Int, createComponent: () -> T): T {
    val viewModel by navGraphViewModels<ComponentViewModel>(navGraphId)
    viewModel.apply {
        if (component == null) {
            component = createComponent()
        }
    }

    @Suppress("UNCHECKED_CAST")
    return viewModel.component as T
}
