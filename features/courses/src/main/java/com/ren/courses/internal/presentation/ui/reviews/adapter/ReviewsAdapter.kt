package com.ren.courses.internal.presentation.ui.reviews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ren.courses.databinding.ItemReviewsBinding
import com.ren.courses.internal.domain.entities.reviews.Reviews

class ReviewsAdapter () :
    ListAdapter<Reviews, ReviewsAdapter.ReviewsViewHolder>(diffUtil) {

    inner class ReviewsViewHolder(private val binding: ItemReviewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Reviews) {
            binding.tvComment.text = item.comment
            binding.tvUserName.text = item.user.username
            binding.tvCreatAt.text = item.createdAt
            binding.tvRating.rating = item.rating
            Glide.with(binding.imUser.context).load(item.user.avatar).into(binding.imUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        return ReviewsViewHolder(
            ItemReviewsBinding.inflate(
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
        val diffUtil = object : DiffUtil.ItemCallback<Reviews>() {
            override fun areItemsTheSame(
                oldItem: Reviews,
                newItem: Reviews
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Reviews, newItem: Reviews
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}