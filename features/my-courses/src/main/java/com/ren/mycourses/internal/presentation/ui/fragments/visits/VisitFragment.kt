package com.ren.mycourses.internal.presentation.ui.fragments.visits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.mycourses.R
import com.ren.mycourses.databinding.FragmentVisitBinding
import com.ren.mycourses.internal.presentation.ui.fragments.lessons.viewmodel.MyLessonsViewModel
import com.ren.mycourses.internal.presentation.ui.fragments.visits.viewmodel.MyVisitViewModel
import com.ren.presentation.base.BaseFragment

class VisitFragment : BaseFragment<FragmentVisitBinding, MyVisitViewModel>(R.layout.fragment_visit) {

    override val binding by viewBinding(FragmentVisitBinding::bind)
    override val viewModel by viewModels<MyVisitViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    private fun setOnClickListeners() = with(binding) {
        rbLessons.setOnClickListener {
            findNavController().navigate(R.id.action_visitFragment_to_myLessonsFragment)
        }
        rbExercise.setOnClickListener {
            findNavController().navigate(R.id.action_visitFragment_to_tasksFragment)
        }
    }
}