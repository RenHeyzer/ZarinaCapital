package com.ren.auth.internal.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.auth.R
import com.ren.auth.databinding.FragmentResetPassowrdBinding
import com.ren.auth.internal.presentation.ui.viewmodels.ResetPasswordViewModel
import com.ren.presentation.base.BaseFragment
import com.ren.presentation.utils.isErrorEnable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class ResetPasswordFragment :
    BaseFragment<FragmentResetPassowrdBinding, ResetPasswordViewModel>(R.layout.fragment_reset_passowrd) {

    override val binding by viewBinding(FragmentResetPassowrdBinding::bind)
    override val viewModel by viewModels<ResetPasswordViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
        resetPassword()
    }

    private fun getArgs() = with(binding) {
        binding.btnResetPassword.setOnClickListener {
            // очищает ошибки в edit text'ax
            val errorList = mutableListOf(newPassword, confirmNewPassword)
            errorList.forEach {
                it.error = null
            }
            val args: ResetPasswordFragmentArgs by navArgs()
            val newPassword = etNewPassword.text.toString().trim()
            val confirmNewPassword = etConfirmNewPassword.text.toString().trim()

            args.code?.let { code ->
                viewModel.resetPassword(code, newPassword, confirmNewPassword)
            }
        }
    }

    private fun resetPassword() = with(binding) {
        viewModel.resultState.subscribeAsState(
            whileLoading = {},
            onLoading = {

            },
            onError = { state ->
                // обработка ошибок
                val fields = mapOf(
                    PASSWORD_KEY to newPassword,
                    CONFIRM_PASSWORD_KEY to confirmNewPassword,
                )
                if (state.errorList != null) {
                    state.errorList?.forEach {
                        fields[it]?.isErrorEnable(
                            isEnabled = true,
                            message = state.message
                        )
                    }
                } else {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            },
            onSuccess = {
                findNavController().navigate(R.id.action_resetPasswordFragment_to_sign_in)
            },
        )
    }

    companion object {
        const val PASSWORD_KEY = "password key"
        const val CONFIRM_PASSWORD_KEY = "confirm password key"
    }
}