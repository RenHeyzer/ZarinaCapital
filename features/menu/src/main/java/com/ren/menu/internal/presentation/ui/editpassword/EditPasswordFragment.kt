package com.ren.menu.internal.presentation.ui.editpassword

import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentEditPasswordBinding
import com.ren.presentation.base.BaseFragment
import com.ren.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class EditPasswordFragment :
    BaseFragment<FragmentEditPasswordBinding, EditPasswordViewModel>(R.layout.fragment_edit_password) {

    override val binding: FragmentEditPasswordBinding by viewBinding(FragmentEditPasswordBinding::bind)
    override val viewModel: EditPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSaveButton()
        setupChangeVisibility(binding.etOldPassword, binding.eyeOldPassword)
        setupChangeVisibility(binding.etNewPassword, binding.eyeNewPassword)
        setupChangeVisibility(binding.etRepeatPassword, binding.eyeRepeatPassword)
        observeViewModel()
    }

    private fun setupChangeVisibility(editText: EditText, toggleView: ImageView) {
        var isPasswordVisible = false

        toggleView.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                toggleView.setImageResource(R.drawable.eys)
            } else {
                editText.transformationMethod = PasswordTransformationMethod.getInstance()
                toggleView.setImageResource(R.drawable.crossed_eys)
            }

            // Move cursor to the end
            editText.setSelection(editText.text.length)
        }
    }


    private fun observeViewModel() {
        viewModel.changePasswordState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Loading -> {
                    // Show loading state, e.g., a progress bar
                    Log.d("EditPasswordFragment", "Loading state")
                }

                is UIState.Success -> {
                    Toast.makeText(requireContext(), "Пароль успешно обновлен", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_editPasswordFragment_to_menuFragment)
                }

                is UIState.Error -> {
                    binding.etOldPassword.error = "Неверный пароль"
                    Log.e("EditPasswordFragment", "Error: ${state.throwable}")

                }
            }
        }
    }

    private fun setupSaveButton() {
        binding.btnSave.setOnClickListener {
            val oldPassword = binding.etOldPassword.text.toString().trim()
            val newPassword = binding.etNewPassword.text.toString().trim()
            val repeatPassword = binding.etRepeatPassword.text.toString().trim()

            if (oldPassword.isNotEmpty() && newPassword.isNotEmpty() && newPassword == repeatPassword) {
                viewModel.changePassword(oldPassword, newPassword)
            } else {
                if (oldPassword.isEmpty()) {
                    binding.etOldPassword.error = "Введите ваш старый пароль"
                }
                if (newPassword.isEmpty()) {
                    binding.etNewPassword.error = "Введите новый пароль"
                }
                if (newPassword != repeatPassword) {
                    binding.etRepeatPassword.error = "Пароли не совпадают"
                }
                Toast.makeText(requireContext(), "Пожалуйста, заполните все поля и убедитесь, что новые пароли совпадают.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

