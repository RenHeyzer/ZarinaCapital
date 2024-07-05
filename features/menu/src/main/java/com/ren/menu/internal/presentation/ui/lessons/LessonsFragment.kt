package com.ren.menu.internal.presentation.ui.lessons

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentLessonsBinding
import com.ren.menu.internal.presentation.ui.lessons.adapter.LessonsAdapter
import com.ren.menu.internal.presentation.ui.news.adapters.NewsAdapter
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LessonsFragment:
    BaseFragment<FragmentLessonsBinding, LessonsViewModel>(R.layout.fragment_lessons) {

    override val binding by viewBinding(FragmentLessonsBinding::bind)
    override val viewModel by viewModels<LessonsViewModel>()
    private val lessonsAdapter = LessonsAdapter(this::setOnItemClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
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
                Log.d("news", loading.toString())
            },
            onError = { error ->
                error.message?.let { Log.e("news", it) }
            },
            onSuccess = { success ->
                success.data?.let {
                    lessonsAdapter.submitList(it)
                }
            }
        )
    }

    private fun setOnClickListener() = with(binding){
        rbVisit.setOnClickListener {
            findNavController().navigate(R.id.action_lessonsFragment_to_attendancesFragment)
        }
    }

    private fun setOnItemClickListener(id:Int){
        findNavController().navigate(
        LessonsFragmentDirections.actionLessonsFragmentToLessonsDetailFragment(id))
    }
}