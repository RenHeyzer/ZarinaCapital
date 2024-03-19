package com.ren.menu.internal.presentation.ui.fragments.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentScheduleBinding
import com.ren.menu.databinding.FragmentSettingBinding
import com.ren.menu.internal.presentation.ui.fragments.schedule.viewmodel.ScheduleViewModel
import com.ren.menu.internal.presentation.ui.fragments.setting.viewmodel.SettingViewModel
import com.ren.presentation.base.BaseFragment

internal class ScheduleFragment : BaseFragment<FragmentScheduleBinding,ScheduleViewModel>(R.layout.fragment_schedule) {

    override val binding by viewBinding(FragmentScheduleBinding::bind)
    override val viewModel by viewModels<ScheduleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() = with(binding){
        rbLessons.setOnClickListener {
            findNavController().navigate(R.id.action_scheduleFragment_to_lessonsFragment)
        }
    }
}