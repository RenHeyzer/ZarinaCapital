package com.ren.courses.internal.presentation.ui.coursesdetail.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.ren.courses.R
import com.ren.courses.databinding.FragmentCoursesDetailBinding
import com.ren.courses.internal.presentation.ui.coursesdetail.adapter.CoursesSpinnerAdapter
import com.ren.courses.internal.presentation.ui.coursesdetail.viewmodel.CoursesDetailViewModel
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoursesDetailFragment :
    BaseFragment<FragmentCoursesDetailBinding, CoursesDetailViewModel>(R.layout.fragment_courses_detail) {

    override val binding by viewBinding(FragmentCoursesDetailBinding::bind)
    override val viewModel by viewModels<CoursesDetailViewModel>()
    private val args by navArgs<CoursesDetailFragmentArgs>()

    private val spinnerAdapter = CoursesSpinnerAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        endPoint = args.id
        subscribeToNews()
        setOnClickListener()
        setOnClickListeners()
        checkVisibleDown()
        checkVisibleUp()
        binding.recCourses.adapter = spinnerAdapter
    }

    private fun checkVisibleDown() {
        binding.recCourses.visibility = View.VISIBLE
        binding.imScrollDown.visibility = View.GONE
        binding.imScrollUp.visibility = View.VISIBLE
    }

    private fun checkVisibleUp() {
        binding.recCourses.visibility = View.GONE
        binding.imScrollDown.visibility = View.VISIBLE
        binding.imScrollUp.visibility = View.GONE

    }

    private fun setOnClickListeners() {
        binding.imScrollDown.setOnClickListener {
            checkVisibleDown()
        }
        binding.imScrollUp.setOnClickListener {
            checkVisibleUp()
        }
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
                    model.author?.let { author ->
                        Glide.with(imAdmin.context).load(author.avatar).load(imAdmin)
                        tvAuthorsName.text = author.username
                    }
                    model.lectures?.let {
                        spinnerAdapter.submitList(it)
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