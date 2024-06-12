package com.ren.auth.internal.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.auth.R
import com.ren.auth.databinding.FragmentResetPassowrdBinding
import com.ren.auth.internal.presentation.ui.viewmodels.ResetPasswordViewModel
import com.ren.auth.internal.presentation.ui.viewmodels.SignUpViewModel
import com.ren.presentation.base.BaseFragment
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

    private fun getArgs() {
        binding.btnResetPassword.setOnClickListener {
            val args: ResetPasswordFragmentArgs by navArgs()
            val newPassword = binding.etNewPassword.text.toString().trim()
            val confirmNewPassword = binding.etConfirmNewPassword.text.toString().trim()
            if (newPassword.equals(confirmNewPassword)) {
                args.code?.let { code ->
                    viewModel.resetPassword(code, newPassword)
                    Log.e("rest", "$code $newPassword " )
                }
            } else {
                binding.etNewPassword.error = "Разные пароли"
                binding.etConfirmNewPassword.error = "Разные пароли"
                Log.e("password", "difference password")
            }
        }
    }

    private fun resetPassword() {
        viewModel.resultState.subscribeAsState(
            whileLoading = {},
            onLoading = {

            },
            onError = {
                Log.e("error", it.toString())
            },
            onSuccess = {
                findNavController().navigate(R.id.action_resetPasswordFragment_to_sign_in)
            },
        )
    }
}