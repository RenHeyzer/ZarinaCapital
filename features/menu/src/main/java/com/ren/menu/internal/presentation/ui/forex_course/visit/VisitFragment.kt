package com.ren.menu.internal.presentation.ui.forex_course.visit

import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentVisitBinding
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VisitFragment : BaseFragment<FragmentVisitBinding, VisitViewModel>(R.layout.fragment_visit) {
    override val binding by viewBinding(FragmentVisitBinding::bind)
}