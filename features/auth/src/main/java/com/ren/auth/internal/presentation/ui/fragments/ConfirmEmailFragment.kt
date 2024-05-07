package com.ren.auth.internal.presentation.ui.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.presentation.utils.hideKeyboard
import com.ren.presentation.utils.notNullContext
import com.ren.auth.R
import com.ren.auth.databinding.FragmentConfirmEmailBinding
import com.ren.auth.internal.presentation.ui.viewmodels.ConfirmEmailViewModel
import com.ren.presentation.base.BaseFragment
import com.ren.presentation.utils.trimmedText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class ConfirmEmailFragment :
    BaseFragment<FragmentConfirmEmailBinding, ConfirmEmailViewModel>(R.layout.fragment_confirm_email) {

    override val binding by viewBinding(FragmentConfirmEmailBinding::bind)
    override val viewModel by viewModels<ConfirmEmailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inputs = with(binding) {
            listOf(etInputOne, etInputTwo, etInputThree, etInputFour)
        }
        nextInput(inputs)
        previousInput(inputs)
        setupNextButton()
        onNextButtonPressed()
        subscribeToResult()
    }

    private fun nextInput(inputs: List<EditText>) {
        inputs.forEachIndexed { index, input ->
            input.addTextChangedListener(
                afterTextChanged = {
                    if (!it.isNullOrEmpty()) {
                        if (index + 1 <= inputs.lastIndex) {
                            input.clearFocus()
                            inputs[index + 1].requestFocus()
                        }
                    }
                    if (inputs.all { et -> et.trimmedText().isNotEmpty() }) {
                        inputs.forEach { et -> et.clearFocus() }
                        hideKeyboard()
                        binding.btnNext.isEnabled = true
                    } else {
                        binding.btnNext.isEnabled = false
                    }
                }
            )
        }
    }

    private fun previousInput(inputs: List<EditText>) {
        inputs.forEachIndexed { index, input ->
            input.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_DEL && input.text.isNullOrEmpty()) {
                    if (index - 1 >= 0) {
                        input.clearFocus()
                        inputs[index - 1].requestFocus()
                    }
                }
                false
            }
        }
    }

    private fun setupNextButton() = with(binding) {
        btnNext.backgroundTintList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_enabled),
                intArrayOf(-android.R.attr.state_enabled),
            ),
            intArrayOf(
                notNullContext.getColor(com.ren.theme.R.color.red),
                notNullContext.getColor(com.ren.theme.R.color.black),
            )
        )
    }

    private fun onNextButtonPressed() = with(binding) {
        btnNext.setOnClickListener {
            val code1 = etInputOne.trimmedText()
            val code2 = etInputTwo.trimmedText()
            val code3 = etInputThree.trimmedText()
            val code4 = etInputFour.trimmedText()
            val code = "$code1$code2$code3$code4".toInt()
            viewModel.confirmEmail(code)
        }
    }

    private fun subscribeToResult() = with(binding) {
        viewModel.resultState.subscribeAsState(
            whileLoading = {
                loading.isVisible = it
            },
            onLoading = { loading ->
                Log.d("confirm", loading.toString())
            },
            onError = { error ->
                error.message?.let { Log.d("confirm", it) }
            },
            onSuccess = {
                findNavController().navigate(R.id.action_confirm_email_to_sign_in)
            }
        )
    }
}