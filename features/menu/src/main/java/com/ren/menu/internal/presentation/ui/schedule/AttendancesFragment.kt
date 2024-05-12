package com.ren.menu.internal.presentation.ui.schedule

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentAttendancesBinding
import com.ren.menu.internal.presentation.ui.schedule.viewmodel.AttendancesViewModel
import com.ren.presentation.base.BaseFragment

internal class AttendancesFragment : BaseFragment<FragmentAttendancesBinding, AttendancesViewModel>(R.layout.fragment_attendances) {

    override val binding by viewBinding(FragmentAttendancesBinding::bind)
    override val viewModel by viewModels<AttendancesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() = with(binding){
        rbLessons.setOnClickListener {
            findNavController().navigate(R.id.action_attendancesFragment_to_lessonsFragment)
        }
    }
}