package com.ren.courses.internal.presentation.ui.coursesdetail.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.ren.courses.databinding.PaidSpinnerItemBinding
import com.ren.courses.databinding.SpinnerCourseItemBinding
import com.ren.courses.internal.domain.entities.courses.detail.CoursesLecturesItem

fun paidLecturesDelegate(navigateToVideoPlayer: (video: String) -> Unit) =
    adapterDelegateViewBinding<CoursesLecturesItem, Any, PaidSpinnerItemBinding>(
        viewBinding = { inflater, parent ->
            PaidSpinnerItemBinding.inflate(inflater, parent, false)
        },
        block = {
            bind {
                binding.root.setOnClickListener {
                    item.videoFile?.let {
                        navigateToVideoPlayer(it)
                    }
                }
                binding.tvCourseTitle.text = item.title
                binding.tvCourseTime.text = item.duration.toString()
            }
        }
    )

fun lecturesDelegate() =
    adapterDelegateViewBinding<CoursesLecturesItem, Any, SpinnerCourseItemBinding>(
        viewBinding = { inflater, parent ->
            SpinnerCourseItemBinding.inflate(inflater, parent, false)
        },
        block = {
            bind {
                binding.tvCourseTitle.text = item.title
                binding.tvCourseTime.text = item.duration.toString()
            }
        }
    )