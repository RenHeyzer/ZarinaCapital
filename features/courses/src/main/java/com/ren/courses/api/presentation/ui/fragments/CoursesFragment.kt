package com.ren.courses.api.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.courses.R
import com.ren.courses.api.presentation.ui.viewmodels.CoursesViewModel
import com.ren.courses.databinding.FragmentCoursesBinding
import com.ren.courses.internal.di.DaggerCoursesFeatureComponent
import com.ren.di.dependencies.findComponentDependencies
import com.ren.di.getComponent
import com.ren.presentation.base.BaseFragment

class CoursesFragment :
    BaseFragment<FragmentCoursesBinding, CoursesViewModel>(R.layout.fragment_courses) {

    private val component by lazy {
        getComponent(R.id.courses_graph) {
            DaggerCoursesFeatureComponent.builder().deps(findComponentDependencies()).build()
        }.coursesComponent().create()
    }

    override val binding by viewBinding(FragmentCoursesBinding::bind)
    override val viewModel by viewModels<CoursesViewModel> { component.viewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToCourses()
    }

    private fun subscribeToCourses() {
        viewModel.coursesState.subscribeAsState(
            onLoading = {

            },
            onError = {
                Log.e("courses", it.message, it.throwable)
            },
            onSuccess = {
                Log.e("courses", it.data.toString())
            }
        )
    }
}