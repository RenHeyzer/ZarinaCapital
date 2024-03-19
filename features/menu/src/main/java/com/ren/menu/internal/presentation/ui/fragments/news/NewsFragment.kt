package com.ren.menu.internal.presentation.ui.fragments.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentNewsBinding
import com.ren.menu.internal.presentation.ui.fragments.news.viewmodel.NewsViewModel
import com.ren.presentation.base.BaseFragment

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>(R.layout.fragment_news) {

    override val binding by viewBinding(FragmentNewsBinding::bind)
    override val viewModel by viewModels<NewsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}