package com.ren.courses.api.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.courses.R
import com.ren.courses.api.presentation.ui.adapter.CoursesAdapter
import com.ren.courses.api.presentation.ui.viewmodels.CoursesViewModel
import com.ren.courses.databinding.FragmentCoursesBinding
import com.ren.courses.internal.domain.entities.courses.Courses
import com.ren.forexapi.api.models.courses.CoursesDTO
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoursesFragment :
    BaseFragment<FragmentCoursesBinding, CoursesViewModel>(R.layout.fragment_courses) {

    override val binding by viewBinding(FragmentCoursesBinding::bind)
    override val viewModel by viewModels<CoursesViewModel>()
    private val coursesAdapter = CoursesAdapter(this::setItemClickListener)

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

    private fun setItemClickListener(id: Int) {
        findNavController().navigate(
            CoursesFragmentDirections.actionCoursesFragmentToCoursesDetailFragment(id)
        )
    }
}