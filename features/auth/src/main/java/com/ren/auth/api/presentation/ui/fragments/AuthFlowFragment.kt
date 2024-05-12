package com.ren.auth.api.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.auth.R
import com.ren.auth.databinding.FragmentAuthFlowBinding
import com.ren.presentation.base.BaseFlowFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFlowFragment :
    BaseFlowFragment(R.layout.fragment_auth_flow, R.id.auth_nav_host_fragment) {

    private val binding by viewBinding(FragmentAuthFlowBinding::bind)
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.sign_in,
                R.id.sign_up,
            )
        )
        binding.authFlowToolbar.setupWithNavController(navController, appBarConfiguration)
    }
}