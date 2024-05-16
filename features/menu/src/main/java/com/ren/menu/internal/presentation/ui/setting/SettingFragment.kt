package com.ren.menu.internal.presentation.ui.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentSettingBinding
import com.ren.menu.internal.presentation.ui.setting.preference.SharedPreferenceLanguages
import com.ren.menu.internal.presentation.ui.setting.viewmodel.SettingViewModel
import com.ren.presentation.base.BaseFragment
import java.util.Locale

class SettingFragment :
    BaseFragment<FragmentSettingBinding, SettingViewModel>(R.layout.fragment_setting) {

    override val binding by viewBinding(FragmentSettingBinding::bind)
    override val viewModel by viewModels<SettingViewModel>()
    private val languagesSharedPreferenceLanguages: SharedPreferenceLanguages by lazy {
        SharedPreferenceLanguages(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
        translateEnglish()
        translateRussian()
        translateKazakh()
    }

    private fun setOnClickListeners() = with(binding) {
        btnDelete.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_deleteAccountFragment)
        }
    }

    private fun translateEnglish() = with(binding) {
        rbEnglish.setOnClickListener {
            val locale = Locale("en")
            Locale.setDefault(locale)
            val config = resources.configuration
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
            languagesSharedPreferenceLanguages.saveLanguage("en")
            requireActivity().recreate()
        }
    }

    private fun translateRussian() {
        binding.rbRussian.setOnClickListener {
            val locale = Locale("ru")
            Locale.setDefault(locale)
            val config = resources.configuration
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
            languagesSharedPreferenceLanguages.saveLanguage("ru")
            requireActivity().recreate()
        }
    }

    private fun translateKazakh() {
        binding.rbKazak.setOnClickListener {
            val locale = Locale("kk")
            Locale.setDefault(locale)

            val config = resources.configuration
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
            languagesSharedPreferenceLanguages.saveLanguage("kk")
            requireActivity().recreate()
        }
    }
}