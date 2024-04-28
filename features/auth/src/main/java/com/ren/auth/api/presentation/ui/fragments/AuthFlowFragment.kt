package com.ren.auth.api.presentation.ui.fragments

import com.ren.auth.R
import com.ren.auth.internal.di.DaggerAuthComponent
import com.ren.di.dependencies.findComponentDependencies
import com.ren.presentation.base.BaseFlowFragment
import com.ren.presentation.utils.component

internal class AuthFlowFragment :
    BaseFlowFragment(R.layout.fragment_auth_flow, R.id.auth_nav_host_fragment) {

    val component by component {
        DaggerAuthComponent.builder().authDeps(findComponentDependencies()).build()
    }
}