package com.ren.auth.internal.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.auth.R
import com.ren.auth.api.presentation.SignInRouter
import com.ren.auth.databinding.FragmentSignInBinding
import com.ren.auth.internal.domain.exceptions.EmptyFieldsException
import com.ren.auth.internal.presentation.ui.viewmodels.SignInViewModel
import com.ren.presentation.base.BaseFragment
import com.ren.presentation.utils.isErrorEnable
import com.ren.presentation.utils.trimmedText
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class SignInFragment :
    BaseFragment<FragmentSignInBinding, SignInViewModel>(R.layout.fragment_sign_in) {

    @Inject
    lateinit var signInRouter: SignInRouter

    override val binding by viewBinding(FragmentSignInBinding::bind)
    override val viewModel by viewModels<SignInViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login()
        subscribeToResult()
        navigateToSignUp()
        navigateToResetPassword()
    }

    private fun login() = with(binding) {
        btnSingIn.setOnClickListener {
            viewModel.login(
                email = etEmail.trimmedText(),
                password = etPassword.trimmedText()
            )
        }
    }

    private fun subscribeToResult() = with(binding) {
        viewModel.resultState.subscribeAsState(
            whileLoading = {
                loading.isVisible = it
            },
            onLoading = { loading -> Log.d("login", loading.toString()) },
            onError = { error ->
                if (error.throwable is EmptyFieldsException) {
                    val emptyFields = (error.throwable as EmptyFieldsException).emptyFields
                    val exceptionMessages =
                        (error.throwable as EmptyFieldsException).exceptionMessages

                    val fields = listOf(
                        tilEmail,
                        tilPassword,
                    )
                    emptyFields.keys.forEachIndexed { index, field ->
                        fields[index].isErrorEnable(
                            isEnabled = emptyFields[field] == true,
                            message = exceptionMessages[field]
                        )
                    }
                } else {
                    Log.e("error", error.message, error.throwable)
                }
            },
            onSuccess = {
                Log.d("login", "success")
                signInRouter.navigateToCourses()
            }
        )
    }

    private fun navigateToSignUp() {
        binding.btnSingUp.setOnClickListener {
            findNavController().navigate(R.id.action_sign_in_to_sign_up)
        }
    }

    private fun navigateToResetPassword() {
        binding.tvForgotYourPassword.setOnClickListener {
            findNavController().navigate(R.id.action_sign_in_to_emailConfirmFragment)
        }
    }
}