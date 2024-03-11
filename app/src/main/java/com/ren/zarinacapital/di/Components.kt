package com.ren.zarinacapital.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.ren.zarinacapital.App
import com.ren.zarinacapital.navigation.MainActivity

val FragmentActivity.appComponent: AppComponent
    get() = (application as App).appComponent

val Fragment.appComponent: AppComponent
    get() = (requireActivity().application as App).appComponent