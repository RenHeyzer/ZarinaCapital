package com.ren.menu.internal.presentation.ui.forex_course.lessons

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentLessonsBinding
import com.ren.menu.internal.presentation.ui.forex_course.adapter.LessonsAdapter
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LessonsFragment :
    BaseFragment<FragmentLessonsBinding, LessonsViewModel>(R.layout.fragment_lessons) {

    override val binding by viewBinding(FragmentLessonsBinding::bind)
    override val viewModel by viewModels<LessonsViewModel>()
    private val lessonsAdapter = LessonsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        clSchedule.setDate(120210L)
        subscribeToLessons()
        binding.recSchedule.adapter = lessonsAdapter
    }

    private fun subscribeToLessons() {
        viewModel.scheduleState.collectUIStateFlow(
            onLoading = { loading ->
                Log.d("lesson", loading.toString())
            },
            onError = { error ->
                error.message?.let { Log.e("lesson", it) }
            },
            onSuccess = { success ->
                success.data?.let {
                    lessonsAdapter.submitList(it)
                }
            }
        )
    }
}