package com.ren.menu.internal.presentation.ui.forex_course

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.ren.menu.R
import com.ren.menu.databinding.FragmentForexCourseBinding
import com.ren.menu.internal.presentation.ui.forex_course.adapter.ForexTabAdapter
import com.ren.menu.internal.presentation.ui.forex_course.exercise.ExerciseFragment
import com.ren.menu.internal.presentation.ui.forex_course.lessons.LessonsFragment
import com.ren.menu.internal.presentation.ui.forex_course.visit.VisitFragment
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForexCourseFragment : Fragment(R.layout.fragment_forex_course) {

    private val binding by viewBinding(FragmentForexCourseBinding::bind)

    private fun tabList() = listOf(
        getString(com.ren.theme.R.string.lesson),
        getString(com.ren.theme.R.string.visit),
        getString(com.ren.theme.R.string.exercise)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayoutListeners()
    }

    private fun tabLayoutListeners() = with(binding) {
        val adapter = ForexTabAdapter(
            this@ForexCourseFragment,
            listOf(LessonsFragment(), VisitFragment(), ExerciseFragment())
        )
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabList()[position]
        }.attach()
    }
}