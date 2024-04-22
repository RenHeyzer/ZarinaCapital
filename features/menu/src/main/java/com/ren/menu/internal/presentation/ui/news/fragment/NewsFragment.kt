package com.ren.menu.internal.presentation.ui.news.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.di.dependencies.findComponentDependencies
import com.ren.di.getComponent
import com.ren.menu.R
import com.ren.menu.databinding.FragmentNewsBinding
import com.ren.menu.internal.di.DaggerNewsComponent
import com.ren.menu.internal.presentation.ui.news.viewmodel.NewsViewModel
import com.ren.presentation.base.BaseFragment

internal class NewsFragment :
    BaseFragment<FragmentNewsBinding, NewsViewModel>(R.layout.fragment_news) {

    private val component by lazy {
        getComponent {
            DaggerNewsComponent.builder().deps(findComponentDependencies()).build()
        }
    }

    override val binding by viewBinding(FragmentNewsBinding::bind)
    override val viewModel by viewModels<NewsViewModel> { component.viewModelFactory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

