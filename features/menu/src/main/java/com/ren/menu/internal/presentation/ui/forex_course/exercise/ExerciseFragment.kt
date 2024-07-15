package com.ren.menu.internal.presentation.ui.forex_course.exercise

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentExerciseBinding
import com.ren.menu.internal.presentation.ui.forex_course.adapter.ExerciseAdapter
import com.ren.presentation.base.BaseFragment
import com.ren.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ExerciseFragment :
    BaseFragment<FragmentExerciseBinding, ExerciseViewModel>(R.layout.fragment_exercise) {
    override val binding by viewBinding(FragmentExerciseBinding::bind)
    override val viewModel by viewModels<ExerciseViewModel>()
    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setupSubscribes()
    }

    private fun initRecyclerView() {
        exerciseAdapter = ExerciseAdapter(requireContext())
        binding.recyclerViewExercise.adapter = exerciseAdapter
    }

    private fun setupSubscribes() {
        lifecycleScope.launch {
            viewModel.exercisesState.collect { uiState ->
                when (uiState) {
                    is UIState.Error -> {
                        Log.e("exercise", uiState.message.toString())
                    }

                    is UIState.Loading -> {
                        Log.e("exercise", "loading")
                    }

                    is UIState.Success -> {
                        exerciseAdapter.submitList(uiState.data)
                    }
                }
            }
        }
    }
}