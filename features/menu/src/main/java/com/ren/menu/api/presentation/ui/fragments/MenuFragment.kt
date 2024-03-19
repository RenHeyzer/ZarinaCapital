package com.ren.menu.api.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.api.presentation.ui.viewmodels.MenuViewModel
import com.ren.menu.databinding.FragmentMenuBinding
import com.ren.presentation.base.BaseFragment

class MenuFragment :
    BaseFragment<FragmentMenuBinding, MenuViewModel>(R.layout.fragment_menu) {

    override val binding by viewBinding(FragmentMenuBinding::bind)
    override val viewModel by viewModels<MenuViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() = with(binding) {
        imChange.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment2_to_editProfileFragment)
        }
        tvHistory.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment2_to_paymentHistoryFragment)
        }
        tvSchedule.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment2_to_lessonsFragment)
        }
        tvChangePassword.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment2_to_editPasswordFragment)
        }
        tvSettings.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment2_to_settingFragment)
        }
        tvNews.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment2_to_newsFragment)
        }
        tvRules.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment2_to_rulesFragment)
        }
    }
}