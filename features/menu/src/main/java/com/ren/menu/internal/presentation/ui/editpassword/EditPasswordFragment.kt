package com.ren.menu.internal.presentation.ui.editpassword

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentEditPasswordBinding
import com.ren.menu.databinding.FragmentEditProfileBinding
import com.ren.menu.internal.domain.entities.changepassword.ChangePassword
import com.ren.menu.internal.domain.entities.profile.PUTProfile
import com.ren.menu.internal.presentation.ui.profile.viewmodel.EditProfileViewModel
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPasswordFragment  :
    BaseFragment<FragmentEditPasswordBinding, EditPasswordViewModel>(R.layout.fragment_edit_password) {

    override val binding: FragmentEditPasswordBinding by viewBinding(FragmentEditPasswordBinding::bind)
    override val viewModel: EditPasswordViewModel by viewModels<EditPasswordViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        changePassword()
        subscribeToPassword()
    }

    private fun changePassword()= with(binding){
        btnSave.setOnClickListener {
            viewModel.changePasswordProfile(
                data = ChangePassword(
                    oldPassword = etOldPassword.text.toString().trim(),
                    newPassword = etNewPassword.text.toString().trim(),
                )
            )
            findNavController().navigateUp()
        }
    }

    private fun subscribeToPassword() {
        viewModel.resultState.subscribeAsState(
            onLoading = { loading->
                Log.d("change", loading.toString())
            },
            onError = {  error ->
                error.message?.let { Log.e("change", it) }
            },
            onSuccess = {
                Toast.makeText(requireContext(), "Измененно", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        )
    }
}