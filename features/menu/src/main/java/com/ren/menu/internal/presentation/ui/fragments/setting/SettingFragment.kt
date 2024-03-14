package com.ren.menu.internal.presentation.ui.fragments.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentSettingBinding
import com.ren.menu.internal.presentation.ui.fragments.setting.viewmodel.SettingViewModel
import com.ren.presentation.base.BaseFragment

class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>(R.layout.fragment_setting) {

    override val binding by viewBinding(FragmentSettingBinding::bind)
    override val viewModel by viewModels<SettingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() = with(binding){
        btnDelete.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_deleteAccountFragment)
        }
    }
}