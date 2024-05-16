package com.ren.menu.internal.presentation.ui.deleteaccount

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
import com.ren.menu.databinding.FragmentDeleteAccountBinding
import com.ren.menu.databinding.FragmentNewsBinding
import com.ren.menu.databinding.FragmentNewsDetailBinding
import com.ren.menu.internal.presentation.ui.news.NewsViewModel
import com.ren.presentation.base.BaseFragment

class DeleteAccountFragment  :
    BaseFragment<FragmentDeleteAccountBinding, DeleteAccountViewModel>(R.layout.fragment_delete_account) {

    override val binding: FragmentDeleteAccountBinding by viewBinding(FragmentDeleteAccountBinding::bind)
    override val viewModel: DeleteAccountViewModel by viewModels<DeleteAccountViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.btnDelete.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}