package com.ren.courses.internal.presentation.ui.coursesdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SpinnerAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ren.courses.databinding.ItemReviewsBinding
import com.ren.courses.databinding.ItemSpinnerBinding
import com.ren.courses.internal.domain.entities.courses.detail.CoursesLecturesItem
import com.ren.courses.internal.domain.entities.reviews.Reviews

class CoursesSpinnerAdapter () :
    ListAdapter<CoursesLecturesItem, CoursesSpinnerAdapter.ReviewsViewHolder>(diffUtil) {

    inner class ReviewsViewHolder(private val binding: ItemSpinnerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: CoursesLecturesItem) {
            binding.tvNumber.text = item.partNumber.toString()
            binding.tvName.text = item.title
            binding.tvTime.text = item.duration.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        return ReviewsViewHolder(
            ItemSpinnerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        getItem(position).let {
            holder.onBind(it)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CoursesLecturesItem>() {
            override fun areItemsTheSame(
                oldItem: CoursesLecturesItem,
                newItem: CoursesLecturesItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CoursesLecturesItem, newItem: CoursesLecturesItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}