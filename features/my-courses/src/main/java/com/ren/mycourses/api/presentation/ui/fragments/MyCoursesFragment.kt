package com.ren.mycourses.api.presentation.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.mycourses.R
import com.ren.mycourses.api.presentation.ui.viewmodels.MyCoursesViewModel
import com.ren.mycourses.databinding.FragmentMyCoursesBinding
import com.ren.presentation.base.BaseFragment

class MyCoursesFragment :
    BaseFragment<FragmentMyCoursesBinding, MyCoursesViewModel>(R.layout.fragment_my_courses) {

    override val binding by viewBinding(FragmentMyCoursesBinding::bind)
    override val viewModel by viewModels<MyCoursesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}