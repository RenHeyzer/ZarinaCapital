package com.ren.menu.api.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.ren.menu.R
import com.ren.menu.api.presentation.ui.viewmodels.MenuViewModel
import com.ren.menu.databinding.FragmentMenuBinding
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment :
    BaseFragment<FragmentMenuBinding, MenuViewModel>(R.layout.fragment_menu) {

    override val binding by viewBinding(FragmentMenuBinding::bind)
    override val viewModel by viewModels<MenuViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListener()
        subscribeToProfile()
    }

    private fun subscribeToProfile() {
        viewModel.profileState.collectUIStateFlow(
            onLoading = { loading ->
                Log.d("profile", loading.toString())
            }, onError = { error ->
                error.message?.let { Log.e("profile", it) }
            }, onSuccess = { success ->
                success.data?.let { model ->
                    Glide.with(binding.imProfile.context).load(model.avatar).into(binding.imProfile)
                    binding.tvName.text = model.username
                    binding.tvEmail.text = model.email
                    binding.tvPhone.text = model.phone
                }
            })
    }

    private fun setUpListener() = with(binding) {
        tvNews.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_newsFragment)
        }
        tvSettings.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_settingFragment)
        }
        tvHistory.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_paymentHistoryFragment)
        }
        imChange.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_editProfileFragment)
        }
        tvSchedule.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_lessonsFragment)
        }
    }
}