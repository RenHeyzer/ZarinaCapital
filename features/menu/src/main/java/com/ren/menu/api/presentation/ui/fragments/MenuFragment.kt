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
import com.ren.menu.internal.domain.entities.profile.Profile
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class
MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>(R.layout.fragment_menu) {

    override val binding by viewBinding(FragmentMenuBinding::bind)
    override val viewModel by viewModels<MenuViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupSwipeRefresh()
        subscribeToProfile()
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateProfile()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun subscribeToProfile() = with(binding) {
        viewModel.profileState.collectUIStateFlow(
            onLoading = { loading ->
                Log.d("MenuFragment", "Loading profile state: $loading")
            },
            onError = { error ->
                Log.e("MenuFragment", "Error loading profile: ${error.message}")
            },
            onSuccess = { success ->
                success.data?.let { profile ->
                    loadProfileData(profile)
                    imChange.setOnClickListener {
                        findNavController().navigate(
                            MenuFragmentDirections.actionMenuFragmentToEditProfileFragment(profile)
                        )
                    }
                }
            }
        )
    }

    private fun loadProfileData(profile: Profile) {
        Glide.with(binding.imProfile)
            .load("file:///storage/emulated/0/Android/data/com.ren.zarinacapital/files/DCIM/IMG_20240629_165908437.jpg")
            .fallback(R.drawable.ic_launcher_background)
            .into(binding.imProfile)

        binding.tvName.text = profile.username
        binding.tvEmail.text = profile.email
        binding.tvPhone.text = profile.phone
    }

    private fun setupListeners() {
        binding.apply {
            tvNews.setOnClickListener { navigateTo(R.id.action_menuFragment_to_newsFragment) }
            tvSettings.setOnClickListener { navigateTo(R.id.action_menuFragment_to_settingFragment) }
            tvHistory.setOnClickListener { navigateTo(R.id.action_menuFragment_to_paymentHistoryFragment) }
            tvSchedule.setOnClickListener { navigateTo(R.id.action_menuFragment_to_forexCourseFragment) }
            tvRules.setOnClickListener { navigateTo(R.id.action_menuFragment_to_rulesFragment2) }
            tvChangePassword.setOnClickListener { navigateTo(R.id.action_menuFragment_to_editPasswordFragment) }
        }
    }

    private fun navigateTo(destinationId: Int) {
        findNavController().navigate(destinationId)
    }
}