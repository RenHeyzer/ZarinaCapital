package com.ren.auth.internal.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geeks.di.dependencies.findComponentDependencies
import com.geeks.di.getComponent
import com.ren.auth.R
import com.ren.auth.databinding.FragmentSignUpBinding
import com.ren.auth.entities.SignUpField
import com.ren.auth.exceptions.EmptyFieldException
import com.ren.auth.exceptions.PasswordMismatchException
import com.ren.auth.internal.di.DaggerAuthComponent
import com.ren.auth.internal.presentation.ui.viewmodels.SignUpViewModel
import com.ren.presentation.base.BaseFragment
import com.ren.presentation.utils.UIState
import com.ren.presentation.utils.enableError
import com.ren.presentation.utils.gone
import com.ren.presentation.utils.trimmedText
import com.ren.presentation.utils.visible

internal class SignUpFragment :
    BaseFragment<FragmentSignUpBinding, SignUpViewModel>(R.layout.fragment_sign_up) {

    private val component by lazy {
        getComponent(R.id.auth) {
            DaggerAuthComponent.builder().deps(findComponentDependencies()).build()
        }.signUpComponent().create()
    }

    override val binding by viewBinding(FragmentSignUpBinding::bind)
    override val viewModel by viewModels<SignUpViewModel> { component.viewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUp()
        subscribeToResult()
    }

    private fun signUp() = with(binding) {
        btnSingUp.setOnClickListener {
            viewModel.signUp(
                username = etFullName.trimmedText(),
                email = etEmail.trimmedText(),
                phone = etPhone.trimmedText(),
                password = etPassword.trimmedText(),
                confirmPassword = etConfirmPassword.trimmedText()
            )
        }
    }

    private fun subscribeToResult() = with(binding) {
        viewModel.resultState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Error -> {
                    loading.gone()
                    when (state.throwable) {
                        is EmptyFieldException -> {
                            when ((state.throwable as EmptyFieldException).field) {
                                SignUpField.USERNAME ->
                                    tilFullName.enableError(state.message)

                                SignUpField.PHONE ->
                                    tilPhone.enableError(state.message)

                                SignUpField.EMAIL ->
                                    tilEmail.enableError(state.message)

                                SignUpField.PASSWORD ->
                                    tilPassword.enableError(state.message)

                                SignUpField.CONFIRM_PASSWORD ->
                                    tilConfirmPassword.enableError(state.message)
                            }
                        }

                        is PasswordMismatchException -> {
                            tilPassword.enableError(state.message)
                            tilConfirmPassword.enableError(state.message)
                        }
                    }
                }

                is UIState.Loading -> loading.visible()
                is UIState.Success -> {
                    loading.gone()
                }
            }
        }
    }
}