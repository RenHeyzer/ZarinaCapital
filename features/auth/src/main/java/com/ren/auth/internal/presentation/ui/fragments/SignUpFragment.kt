package com.ren.auth.internal.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.auth.R
import com.ren.auth.databinding.FragmentSignUpBinding
import com.ren.auth.internal.domain.exceptions.EmptyFieldsException
import com.ren.auth.internal.domain.exceptions.PasswordMismatchException
import com.ren.auth.internal.presentation.ui.viewmodels.SignUpViewModel
import com.ren.presentation.base.BaseFragment
import com.ren.presentation.utils.gone
import com.ren.presentation.utils.isErrorEnable
import com.ren.presentation.utils.trimmedText
import com.ren.presentation.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SignUpFragment :
    BaseFragment<FragmentSignUpBinding, SignUpViewModel>(R.layout.fragment_sign_up) {

    override val binding by viewBinding(FragmentSignUpBinding::bind)
    override val viewModel by viewModels<SignUpViewModel>()

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
                phone = "${tilPhone.prefixText.toString().trim()}${etPhone.trimmedText()}",
                password = etPassword.trimmedText(),
                confirmPassword = etConfirmPassword.trimmedText()
            )
        }
    }

    private fun subscribeToResult() = with(binding) {
        viewModel.resultState.subscribeAsState(
            onLoading = {
                loading.visible()
            },
            onError = { state ->
                loading.gone()
                when (state.throwable) {
                    is EmptyFieldsException -> {
                        val emptyFields = (state.throwable as EmptyFieldsException).emptyFields
                        val exceptionMessages =
                            (state.throwable as EmptyFieldsException).exceptionMessages

                        val fields = listOf(
                            tilFullName,
                            tilPhone,
                            tilEmail,
                            tilPassword,
                            tilConfirmPassword
                        )
                        emptyFields.keys.forEachIndexed { index, field ->
                            fields[index].isErrorEnable(
                                isEnabled = emptyFields[field] == true,
                                message = exceptionMessages[field]
                            )
                        }
                    }

                    is PasswordMismatchException -> {
                        tilPassword.isErrorEnable(
                            isEnabled = true,
                            message = state.message
                        )
                        tilConfirmPassword.isErrorEnable(
                            isEnabled = true,
                            message = state.message
                        )
                    }

                    else -> {
                        Log.e("error", state.message, state.throwable)
                    }
                }
            },
            onSuccess = {
                loading.gone()
                findNavController().navigate(R.id.confirm_email)
            }
        )
    }
}