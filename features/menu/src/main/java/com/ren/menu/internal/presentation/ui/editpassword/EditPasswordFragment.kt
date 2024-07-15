package com.ren.menu.internal.presentation.ui.editpassword

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentEditPasswordBinding
import com.ren.presentation.base.BaseFragment
import com.ren.presentation.utils.CONFIRM_PASSWORD_KEY
import com.ren.presentation.utils.OLD_PASSWORD
import com.ren.presentation.utils.PASSWORD_KEY
import com.ren.presentation.utils.UIState
import com.ren.presentation.utils.isErrorEnable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class EditPasswordFragment :
    BaseFragment<FragmentEditPasswordBinding, EditPasswordViewModel>(R.layout.fragment_edit_password) {

    override val binding: FragmentEditPasswordBinding by viewBinding(FragmentEditPasswordBinding::bind)
    override val viewModel: EditPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSaveButton()
        observeViewModel()
    }

    private fun observeViewModel() = with(binding) {
        viewModel.changePasswordState.observe(viewLifecycleOwner) { state ->
            val fields = mapOf(
                OLD_PASSWORD to oldPassword,
                PASSWORD_KEY to newPassword,
                CONFIRM_PASSWORD_KEY to repeatPassword,
            )

            when (state) {
                is UIState.Loading -> {
                    Log.d("EditPasswordFragment", "Loading state")
                }

                is UIState.Success -> {
                    Toast.makeText(requireContext(), "Пароль успешно обновлен", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_editPasswordFragment_to_menuFragment)
                }

                is UIState.Error -> {
                    state.errorList?.let { errorList ->
                        errorList.forEach {
                            fields[it.key]?.isErrorEnable(true, it.value)
                        }
                    }
                }
            }
        }
    }

    private fun setupSaveButton() {
        binding.btnSave.setOnClickListener {
            val oldPassword = binding.etOldPassword.text.toString().trim()
            val newPassword = binding.etNewPassword.text.toString().trim()
            val repeatPassword = binding.etRepeatPassword.text.toString().trim()

            val fields = listOf(
                binding.oldPassword,
                binding.newPassword,
                binding.repeatPassword
            )
            fields.forEach { field ->
                field.isErrorEnable(false)
            }
            viewModel.changePassword(oldPassword, newPassword, repeatPassword)

        }
    }

}

