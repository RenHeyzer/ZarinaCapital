package com.ren.zarinacapital.navigation.auth

import android.content.Context
import android.util.Log
import androidx.navigation.findNavController
import com.ren.auth.api.presentation.SignInRouter
import com.ren.zarinacapital.R
import com.ren.zarinacapital.navigation.MainActivity
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class SignInRouterAdapter @Inject constructor(
    @ActivityContext private val context: Context
) : SignInRouter {

    override fun navigateToCourses() {
        Log.d("navigate", "success")
        (context as MainActivity).findNavController(R.id.nav_host_fragment)
            .navigate(R.id.action_auth_flow_to_courses)
    }
}