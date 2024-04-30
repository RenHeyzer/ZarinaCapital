package com.ren.auth.api.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.auth.R
import com.ren.auth.api.presentation.ui.viewmodels.ConfirmEmailViewModel
import com.ren.auth.databinding.FragmentConfirmEmailBinding
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmEmailFragment :
    BaseFragment<FragmentConfirmEmailBinding, ConfirmEmailViewModel>(R.layout.fragment_confirm_email) {

    override val binding by viewBinding(FragmentConfirmEmailBinding::bind)
    override val viewModel by viewModels<ConfirmEmailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}