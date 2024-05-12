package com.ren.menu.internal.presentation.ui.news.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.ren.menu.R
import com.ren.menu.databinding.FragmentNewsDetailBinding
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment :
    BaseFragment<FragmentNewsDetailBinding, NewsDetailViewModel>(R.layout.fragment_news_detail) {

    override val binding by viewBinding(FragmentNewsDetailBinding::bind)
    override val viewModel by viewModels<NewsDetailViewModel>()
    private val args by navArgs<NewsDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        endPoint = args.id
        subscribeToNews()
    }

    private fun subscribeToNews() {
        viewModel.newsState.collectUIStateFlow(
            onLoading = { loading ->
                Log.d("courses", loading.toString())
            }, onError = { error ->
                error.message?.let { Log.e("courses", it) }
            }, onSuccess = { success ->
                success.data?.let { model ->
                    Glide.with(binding.imIcon.context).load(model.image).into(binding.imIcon)
                    binding.tvData.text = model.startDatetime
                    binding.tvName.text = model.title
                    binding.tvRules.text = model.description
                }
            })
    }

    companion object {
        var endPoint = 0
    }
}