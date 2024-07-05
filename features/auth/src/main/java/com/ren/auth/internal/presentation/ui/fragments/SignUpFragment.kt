package com.ren.auth.internal.presentation.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputLayout
import com.ren.auth.R
import com.ren.auth.databinding.FragmentSignUpBinding
import com.ren.auth.internal.domain.exceptions.EmptyFieldsException
import com.ren.auth.internal.domain.exceptions.PasswordMismatchException
import com.ren.auth.internal.presentation.ui.viewmodels.SignUpViewModel
import com.ren.presentation.base.BaseFragment
import com.ren.presentation.utils.CONFIRM_PASSWORD_KEY
import com.ren.presentation.utils.EMAIL_KEY
import com.ren.presentation.utils.NUMBER_KEY
import com.ren.presentation.utils.PASSWORD_KEY
import com.ren.presentation.utils.USERNAME_KEY
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
        setupTextWatchers()
        signUp()
        subscribeToResult()
        navigateToSignIn()
    }

    private fun setupTextWatchers() = with(binding) {
        etFullName.addTextChangedListener(createTextWatcher(tilFullName))
        etEmail.addTextChangedListener(createTextWatcher(tilEmail))
        etPhone.addTextChangedListener(createTextWatcher(tilPhone))
        etPassword.addTextChangedListener(createTextWatcher(tilPassword))
        etConfirmPassword.addTextChangedListener(createTextWatcher(tilConfirmPassword))
    }

    private fun createTextWatcher(textInputLayout: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textInputLayout.error = null
            }
            override fun afterTextChanged(s: Editable?) {}
        }
    }

    private fun signUp() = with(binding) {
        val etList = listOf(
            tilPassword,
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
                    USERNAME_KEY to tilFullName,
                    NUMBER_KEY to tilPhone,
                    EMAIL_KEY to tilEmail,
                    PASSWORD_KEY to tilPassword,
                    CONFIRM_PASSWORD_KEY to tilConfirmPassword,
                )
                if (state.errorList != null) {
                    state.errorList!!.forEach {
                        fields[it.key]?.isErrorEnable(
                            isEnabled = true,
                            message = it.value
                        )
                    }
                } else {
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
}
