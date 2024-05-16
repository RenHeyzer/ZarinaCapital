package com.ren.menu.internal.presentation.ui.profile

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
import com.ren.menu.databinding.FragmentEditProfileBinding
import com.ren.menu.databinding.FragmentNewsBinding
import com.ren.menu.internal.domain.entities.profile.PUTProfile
import com.ren.menu.internal.presentation.ui.news.NewsViewModel
import com.ren.menu.internal.presentation.ui.profile.viewmodel.EditProfileViewModel
import com.ren.presentation.base.BaseFragment
import com.ren.presentation.utils.gone
import com.ren.presentation.utils.isErrorEnable
import com.ren.presentation.utils.trimmedText
import com.ren.presentation.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

internal class EditProfileFragment :
    BaseFragment<FragmentEditProfileBinding, EditProfileViewModel>(R.layout.fragment_edit_profile) {

    override val binding: FragmentEditProfileBinding by viewBinding(FragmentEditProfileBinding::bind)
    override val viewModel: EditProfileViewModel by viewModels<EditProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        updateProfile()
        subscribeToProfile()
    }

    private fun updateProfile() = with(binding){
        btnSave.setOnClickListener {
            viewModel.updateProfile(
                data = PUTProfile(
                    username = etName.text.toString().trim(),
                    prefix = tilPhone.prefixText.toString().trim(),
                    phone = etNumber.text.toString().trim(),
                    email = etEmail.text.toString().trim()
                )
            )
        }
    }
    private fun subscribeToProfile(){
        viewModel.resultState.subscribeAsState(
            onLoading = { loading->
                Log.d("put", loading.toString())
            },
            onError = {  error ->
                error.message?.let { Log.e("put", it) }
            },
            onSuccess = {
                Toast.makeText(requireContext(), "Измененно", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        )
    }
}