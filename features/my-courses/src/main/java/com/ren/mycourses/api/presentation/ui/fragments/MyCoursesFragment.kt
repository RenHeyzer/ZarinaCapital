package com.ren.mycourses.api.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.mycourses.R
import com.ren.mycourses.api.presentation.ui.adapter.CoursesAdapter
import com.ren.mycourses.api.presentation.ui.viewmodels.MyCoursesViewModel
import com.ren.mycourses.databinding.FragmentMyCoursesBinding
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyCoursesFragment :
    BaseFragment<FragmentMyCoursesBinding, MyCoursesViewModel>(R.layout.fragment_my_courses) {

    override val binding by viewBinding(FragmentMyCoursesBinding::bind)
    override val viewModel by viewModels<MyCoursesViewModel>()

    private val coursesAdapter = CoursesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        subscribeToNews()
        binding.recCourses.adapter = coursesAdapter
    }

    private fun subscribeToNews() {
        viewModel.coursesState.collectUIStateFlow(
            onLoading = { loading ->
                Log.d("courses", loading.toString())
            },
            onError = { error ->
                error.message?.let { Log.e("courses", it) }
            },
            onSuccess = { success ->
                success.data?.let {
                    coursesAdapter.submitList(it)
                }
            }
        )
    }

}