package com.ren.menu.internal.presentation.ui.news

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.menu.R
import com.ren.menu.databinding.FragmentNewsBinding
import com.ren.menu.internal.presentation.ui.news.adapters.NewsAdapter
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class NewsFragment :
    BaseFragment<FragmentNewsBinding, NewsViewModel>(R.layout.fragment_news) {

    override val binding: FragmentNewsBinding by viewBinding(FragmentNewsBinding::bind)
    override val viewModel: NewsViewModel by viewModels<NewsViewModel>()
    private val newsAdapter = NewsAdapter(this::setItemClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        subscribeToNews()
        binding.rvNews.adapter = newsAdapter
    }

    private fun subscribeToNews() {
        viewModel.newsState.collectUIStateFlow(
            onLoading = { loading ->
                Log.d("news", loading.toString())
            },
            onError = { error ->
                error.message?.let { Log.e("news", it) }
            },
            onSuccess = { success ->
                success.data?.let {
                    newsAdapter.submitList(it)
                }
            }
        )
    }

    private fun setItemClickListener(id: Int) {
        findNavController().navigate(
            NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment2(id)
        )
    }
}
