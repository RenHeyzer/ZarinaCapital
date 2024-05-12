package com.ren.courses.internal.presentation.ui.reviews.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.ren.courses.R
import com.ren.courses.api.presentation.ui.adapter.CoursesAdapter
import com.ren.courses.api.presentation.ui.viewmodels.CoursesViewModel
import com.ren.courses.databinding.FragmentCoursesBinding
import com.ren.courses.databinding.FragmentReviewsBinding
import com.ren.courses.internal.presentation.ui.reviews.adapter.ReviewsAdapter
import com.ren.courses.internal.presentation.ui.reviews.viewmodel.ReviewsViewModel
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewsFragment  :
    BaseFragment<FragmentReviewsBinding, ReviewsViewModel>(R.layout.fragment_reviews) {

    override val binding by viewBinding(FragmentReviewsBinding::bind)
    override val viewModel by viewModels<ReviewsViewModel>()
    private val reviewsAdapter = ReviewsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        subscribeToNews()
        binding.rvReviews.adapter = reviewsAdapter
    }

    private fun subscribeToNews() {
        viewModel.reviewsState.collectUIStateFlow(
            onLoading = { loading ->
                Log.d("courses", loading.toString())
            },
            onError = { error ->
                error.message?.let { Log.e("courses", it) }
            },
            onSuccess = { success ->
                success.data?.let {
                    reviewsAdapter.submitList(it)
                }
            }
        )
    }
}