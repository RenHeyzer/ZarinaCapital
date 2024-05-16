package com.ren.menu.internal.presentation.ui.rules

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentEditProfileBinding
import com.ren.menu.databinding.FragmentRulesBinding
import com.ren.menu.internal.presentation.ui.profile.viewmodel.EditProfileViewModel
import com.ren.menu.internal.presentation.ui.rules.viewmodel.RulesViewModel
import com.ren.presentation.base.BaseFragment

class RulesFragment :
    BaseFragment<FragmentRulesBinding, RulesViewModel>(R.layout.fragment_rules) {

    override val binding: FragmentRulesBinding by viewBinding(FragmentRulesBinding::bind)
    override val viewModel: RulesViewModel by viewModels<RulesViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}