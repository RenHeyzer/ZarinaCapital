package com.ren.menu.internal.presentation.ui.fragments.lessons

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
import com.ren.menu.api.presentation.ui.viewmodels.MenuViewModel
import com.ren.menu.databinding.FragmentLessonsBinding
import com.ren.menu.databinding.FragmentMenuBinding
import com.ren.presentation.base.BaseFragment

class LessonsFragment:
    BaseFragment<FragmentLessonsBinding, LessonsViewModel>(R.layout.fragment_lessons) {

    override val binding by viewBinding(FragmentLessonsBinding::bind)
    override val viewModel by viewModels<LessonsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() = with(binding){
        rbVisit.setOnClickListener {
            findNavController().navigate(R.id.action_lessonsFragment_to_scheduleFragment)
        }
    }
}