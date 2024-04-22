package com.ren.di

import androidx.lifecycle.ViewModel

class ComponentViewModel<C>(val component: C) : ViewModel() {

    var _component: C? = component

    override fun onCleared() {
        _component = null
    }
}

//fun <T> ViewModelStoreOwner.getComponent(createComponent: () -> T): T {
//    val viewModel = ViewModelProvider(this)[ComponentViewModel::class.java]
//    viewModel.apply {
//        if (_component == null) {
//            _component = createComponent()
//        }
//    }
//
//    @Suppress("UNCHECKED_CAST")
//    return viewModel.component as T
//}