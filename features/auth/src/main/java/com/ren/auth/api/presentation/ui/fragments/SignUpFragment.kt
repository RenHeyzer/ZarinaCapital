package com.ren.auth.api.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.auth.R
import com.ren.auth.api.presentation.ui.viewmodels.SignUpViewModel
import com.ren.auth.databinding.FragmentSignUpBinding
import com.ren.presentation.base.BaseFragment

class SignUpFragment :
    BaseFragment<FragmentSignUpBinding, SignUpViewModel>(R.layout.fragment_sign_up) {

    override val binding by viewBinding(FragmentSignUpBinding::bind)
    override val viewModel by viewModels<SignUpViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}