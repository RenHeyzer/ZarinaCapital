package com.ren.menu.internal.presentation.ui.news.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.ren.menu.R
import com.ren.menu.databinding.FragmentNewsDetailBinding
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
                    val originalFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
                    // Желаемый формат даты для tvData "d MMMM"
                    val dateFormat = SimpleDateFormat("d MMMM", Locale("ru"))
                    // Желаемый формат времени для tvTime "HH:mm"
                    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                    val date: Date = originalFormat.parse(model.startDatetime)

                    // Форматирование даты
                    val formattedDate: String = dateFormat.format(date)
                    // Форматирование времени
                    val formattedTime: String = timeFormat.format(date)

                    binding.tvData.text = formattedDate
                    binding.tvTime.text = formattedTime
                    binding.tvName.text = model.title
                    binding.tvRules.text = model.description
                    Glide.with(binding.imIcon.context).load(model.image).into(binding.imIcon)
                }
            })
    }

    companion object {
        var endPoint = 0
    }
}
