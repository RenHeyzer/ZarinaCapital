package com.ren.mycourses.internal.presentation.ui.fragments.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.mycourses.R
import com.ren.mycourses.databinding.FragmentMyLessonsBinding
import com.ren.mycourses.databinding.FragmentTasksBinding
import com.ren.mycourses.internal.presentation.ui.fragments.tasks.viewmodel.MyTasksViewModel
import com.ren.presentation.base.BaseFragment

class TasksFragment : BaseFragment<FragmentTasksBinding, MyTasksViewModel>(R.layout.fragment_tasks) {

    override val binding by viewBinding(FragmentTasksBinding::bind)
    override val viewModel by viewModels<MyTasksViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() = with(binding){
        rbVisit.setOnClickListener {
            findNavController().navigate(R.id.action_tasksFragment_to_visitFragment)
        }
        rbLessons.setOnClickListener {
            findNavController().navigate(R.id.action_tasksFragment_to_myLessonsFragment)
        }
    }
}