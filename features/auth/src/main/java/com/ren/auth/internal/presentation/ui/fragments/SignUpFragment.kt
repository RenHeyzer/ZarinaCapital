package com.ren.auth.internal.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
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
        navigateToSignIn()
    }

    private fun signUp() = with(binding) {
        val etList = listOf(
            tilPassword,
            tilEmail,
            tilEmail,
            tilPhone,
            tilFullName,
            tilConfirmPassword,
        )
        btnSingUp.setOnClickListener {
            etList.forEach {
                it.error = null
            }
            viewModel.signUp(
                username = etFullName.trimmedText(),
                email = etEmail.trimmedText(),
                prefix = tilPhone.prefixText.toString().trim(),
                phone = etPhone.trimmedText(),
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
                val fields = mapOf(
                    FULL_NAME_KEY to tilFullName,
                    PHONE_KEY to tilPhone,
                    EMAIL_KEY to tilEmail,
                    PASSWORD_KEY to tilPassword,
                    CONFIRM_PASSWORD_KEY to tilConfirmPassword,
                )
                if (state.errorList != null) {
                    state.errorList?.forEach {
                        fields[it]?.isErrorEnable(
                            isEnabled = true,
                            message = state.message
                        )
                    }
                }else {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            },
            onSuccess = {
                loading.gone()
                findNavController().navigate(R.id.confirm_email)
            }
        )
    }

    private fun navigateToSignIn() {
        binding.btnSingIn.setOnClickListener {
            findNavController().navigate(R.id.action_sign_up_to_sign_in)
        }
    }

    companion object{
        const val FULL_NAME_KEY = "full name"
        const val PHONE_KEY = "phone"
        const val EMAIL_KEY ="email"
        const val PASSWORD_KEY ="password"
        const val CONFIRM_PASSWORD_KEY = "confirm password"
    }
}