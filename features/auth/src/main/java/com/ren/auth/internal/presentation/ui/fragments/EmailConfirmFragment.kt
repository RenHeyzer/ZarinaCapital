package com.ren.auth.internal.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.auth.R
import com.ren.auth.databinding.FragmentEmailConfirmBinding
import com.ren.auth.internal.presentation.ui.viewmodels.EmailConfirmViewModel
import com.ren.presentation.base.BaseFragment
import com.ren.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailConfirmFragment :
    BaseFragment<FragmentEmailConfirmBinding, EmailConfirmViewModel>(R.layout.fragment_email_confirm) {

    override val binding by viewBinding(FragmentEmailConfirmBinding::bind)
    override val viewModel by viewModels<EmailConfirmViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToResetPasswordCode()
        setupSubscribes()
    }

    private fun navigateToResetPasswordCode() {
        binding.btnConfirmEmail.setOnClickListener {
            val email = binding.etConfirmEmail.text.toString().trim()
            viewModel.checkEmail(email)
        }
    }

    private fun setupSubscribes() {
        viewModel.resultState.observe(viewLifecycleOwner) {uiState ->
            when(uiState) {
                is UIState.Error -> {
                    Log.e("result", "setupSubscribes: ${uiState.message}")
                }
                UIState.Loading -> {

                }
                is UIState.Success -> {
                    findNavController().navigate(R.id.action_emailConfirmFragment_to_resetPasswordCodeFragment)
                }
            }
        }
    }
}
