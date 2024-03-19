package com.ren.mycourses.internal.presentation.ui.fragments.lessons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.mycourses.R
import com.ren.mycourses.databinding.FragmentMyLessonsBinding
import com.ren.mycourses.internal.presentation.ui.fragments.tasks.viewmodel.MyTasksViewModel
import com.ren.presentation.base.BaseFragment

class MyLessonsFragment : BaseFragment<FragmentMyLessonsBinding, MyTasksViewModel>(R.layout.fragment_my_lessons) {

    override val binding by viewBinding(FragmentMyLessonsBinding::bind)
    override val viewModel by viewModels<MyTasksViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() = with(binding){
        rbVisit.setOnClickListener {
            findNavController().navigate(R.id.action_myLessonsFragment_to_visitFragment)
        }
        rbExercise.setOnClickListener {
            findNavController().navigate(R.id.action_myLessonsFragment_to_tasksFragment)
        }
    }
}