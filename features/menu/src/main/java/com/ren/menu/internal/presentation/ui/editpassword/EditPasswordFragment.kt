package com.ren.menu.internal.presentation.ui.editpassword

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentEditPasswordBinding
import com.ren.menu.databinding.FragmentEditProfileBinding
import com.ren.menu.internal.presentation.ui.profile.viewmodel.EditProfileViewModel
import com.ren.presentation.base.BaseFragment

class EditPasswordFragment  :
    BaseFragment<FragmentEditPasswordBinding, EditPasswordViewModel>(R.layout.fragment_edit_password) {

    override val binding: FragmentEditPasswordBinding by viewBinding(FragmentEditPasswordBinding::bind)
    override val viewModel: EditPasswordViewModel by viewModels<EditPasswordViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnCLickListeners()
    }

    private fun setOnCLickListeners() {
        binding.btnSave.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}