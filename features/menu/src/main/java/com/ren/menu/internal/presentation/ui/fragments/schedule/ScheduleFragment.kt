package com.ren.menu.internal.presentation.ui.fragments.schedule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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
        rbVisit.setOnClickListener {
            rbVisit.setBackgroundResource(R.drawable.rounder_button_white)
            rbLessons.setBackgroundResource(R.drawable.rounder_button)
            clSchedule.visibility = View.GONE
            recSchedule.visibility = View.GONE
            cardView.visibility = View.VISIBLE
        }
        rbLessons.setOnClickListener {
            rbLessons.setBackgroundResource(R.drawable.rounder_button_white)
            rbVisit.setBackgroundResource(R.drawable.rounder_button)
            clSchedule.visibility = View.VISIBLE
            recSchedule.visibility = View.VISIBLE
            cardView.visibility = View.GONE
        }
    }
}