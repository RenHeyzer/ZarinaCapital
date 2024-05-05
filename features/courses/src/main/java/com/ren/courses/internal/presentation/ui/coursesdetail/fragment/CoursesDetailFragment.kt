package com.ren.courses.internal.presentation.ui.coursesdetail.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.ren.courses.R
import com.ren.courses.api.presentation.ui.adapter.CoursesAdapter
import com.ren.courses.api.presentation.ui.viewmodels.CoursesViewModel
import com.ren.courses.databinding.FragmentCoursesBinding
import com.ren.courses.databinding.FragmentCoursesDetailBinding
import com.ren.courses.internal.domain.entities.courses.detail.CoursesDetail
import com.ren.courses.internal.domain.entities.courses.detail.CoursesLecturesItem
import com.ren.courses.internal.presentation.ui.coursesdetail.viewmodel.CoursesDetailViewModel
import com.ren.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoursesDetailFragment :
    BaseFragment<FragmentCoursesDetailBinding, CoursesDetailViewModel>(R.layout.fragment_courses_detail) {

    override val binding by viewBinding(FragmentCoursesDetailBinding::bind)
    override val viewModel by viewModels<CoursesDetailViewModel>()
    private val args by navArgs<CoursesDetailFragmentArgs>()
    private val listCourses = mutableListOf<CoursesLecturesItem>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        endPoint = args.id
        subscribeToNews()
        setOnClickListener()
//        spinner()
    }

//    private fun spinner() {
//
//        val adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, listCourses)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.spCourses.adapter = adapter
//
//        binding.spCourses.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
//                val selectedItem = parent.getItemAtPosition(position).toString()
//                Toast.makeText(requireContext(), "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//                // Do nothing
//            }
//        }
//    }

    private fun subscribeToNews() = with(binding) {
        viewModel.coursesDetailState.collectUIStateFlow(onLoading = { loading ->
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
//                        model.lectures?.map {
//                            listCourses.add(it)
//                        }
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