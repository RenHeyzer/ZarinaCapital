package com.ren.zarinacapital.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.ren.zarinacapital.ZarinaApp

val FragmentActivity.appComponent: AppComponent
    get() = (application as ZarinaApp).appComponent

val Fragment.appComponent: AppComponent
    get() = (requireActivity().application as ZarinaApp).appComponent