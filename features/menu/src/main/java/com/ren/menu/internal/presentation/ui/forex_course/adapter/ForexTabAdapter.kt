package com.ren.menu.internal.presentation.ui.forex_course.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ForexTabAdapter(
    private val mainFragment: Fragment,
    private val fragmentList: List<Fragment>
) :
    FragmentStateAdapter(mainFragment) {
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}