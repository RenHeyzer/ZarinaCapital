package com.ren.courses.internal.presentation.ui.coursesdetail.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.ren.courses.R
import com.ren.courses.databinding.FragmentCoursesDetailBinding
import com.ren.courses.internal.presentation.ui.coursesdetail.adapter.lecturesDelegate
import com.ren.courses.internal.presentation.ui.coursesdetail.adapter.paidLecturesDelegate
import com.ren.courses.internal.presentation.ui.coursesdetail.viewmodel.CoursesDetailViewModel
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoursesDetailFragment :
    BaseFragment<FragmentCoursesDetailBinding, CoursesDetailViewModel>(R.layout.fragment_courses_detail) {

    override val binding by viewBinding(FragmentCoursesDetailBinding::bind)
    override val viewModel by viewModels<CoursesDetailViewModel>()
    private val args by navArgs<CoursesDetailFragmentArgs>()

    private val paidAdapter = ListDelegationAdapter(paidLecturesDelegate() {
        navigateToVideoPlayer(it)
    })

    private val adapter = ListDelegationAdapter(lecturesDelegate())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
    }

    private fun initialize() {
        endPoint = args.id
        subscribeToNews()
        setOnClickListener()
    }

    private fun setupListeners() = with(binding) {
        includePaidItem.root.setOnClickListener {
            rvLectures.isGone = !rvLectures.isGone
            includePaidItem.icSpinnerArrow.scaleY = -(includePaidItem.icSpinnerArrow.scaleY)
        }
        includeItem.root.setOnClickListener {
            rvLectures.isGone = !rvLectures.isGone
            includeItem.icSpinnerArrow.scaleY = -(includeItem.icSpinnerArrow.scaleY)
        }
    }

    private fun navigateToVideoPlayer(video: String) {
        findNavController().navigate(
            CoursesDetailFragmentDirections.actionCoursesDetailFragmentToVideoPlayerFragment(
                video
            )
        )
    }

    private fun subscribeToNews() = with(binding) {
        viewModel.coursesDetailState.collectUIStateFlow(
            onLoading = { loading ->
                Log.d("courses", loading.toString())
            }, onError = { error ->
                error.message?.let { Log.e("courses", it) }
            }, onSuccess = { success ->
                success.data?.let { model ->
                    Glide.with(imIcon.context).load(model.image).into(imIcon)
                    tvClock.text = model.totalDuration.toString()
                    tvNameCourses.text = model.title
                    tvPrice.text = model.price
                    model.rating?.let {
                        tvRating.rating = it
                    }
                    model.reviews?.map { reviews ->
                        Glide.with(imUser.context).load(reviews.user?.avatar).into(imUser)
                        tvUserName.text = reviews.user?.username
                        tvTitleReviews.text = reviews.comment
                        reviews.rating?.let {
                            tvGrade.rating = it.toFloat()
                        }
                        tvCreadAt.text = reviews.createdAt
                    }
                    model.author?.let { author ->
                        Glide.with(imAdmin.context).load(author.avatar).load(imAdmin)
                        tvAuthorsName.text = author.username
                    }
                    model.lectures?.let { lectues ->
                        model.price?.let {
                            if (it.toDouble() == 0.0 || false) {
                                includePaidItem.tvSpinnerLectures.text =
                                    getString(R.string.lectures, model.lectureCount)
                                includePaidItem.tvSpinnerFails.text =
                                    getString(R.string.count_files, 0)
                                includePaidItem.tvSpinnerTest.text = getString(R.string.tests, 0)
                                includePaidItem.root.visibility = View.VISIBLE
                                includeItem.root.visibility = View.GONE
                                paidAdapter.items = lectues
                                rvLectures.adapter = paidAdapter
                            } else {
                                includeItem.tvSpinnerLectures.text =
                                    getString(R.string.lectures, model.lectureCount)
                                includeItem.root.visibility = View.VISIBLE
                                includePaidItem.root.visibility = View.GONE
                                adapter.items = lectues
                                rvLectures.adapter = adapter
                            }
                        }
                    }
                }
            })
    }

    private fun setOnClickListener() {
        binding.tvAll.setOnClickListener {
            findNavController().navigate(R.id.action_coursesDetailFragment_to_reviewsFragment)
        }
    }

    companion object {
        var endPoint = 0
    }
}