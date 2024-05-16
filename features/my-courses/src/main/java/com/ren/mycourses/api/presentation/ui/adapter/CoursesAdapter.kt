package com.ren.mycourses.api.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ren.mycourses.databinding.ItemCoursesBinding
import com.ren.mycourses.internal.domain.entities.courses.Courses

class CoursesAdapter() :
    ListAdapter<Courses, CoursesAdapter.CoursesViewHolder>(diffUtil) {

    inner class CoursesViewHolder(private val binding: ItemCoursesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Courses) {
            binding.tvName.text = item.title
            binding.tvPrice.text = item.price
            binding.tvRating.rating = item.rating
            Glide.with(binding.imIcon.context).load(item.image).into(binding.imIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        return CoursesViewHolder(
            ItemCoursesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        getItem(position).let {
            holder.onBind(it)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Courses>() {
            override fun areItemsTheSame(
                oldItem: Courses,
                newItem: Courses
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Courses, newItem: Courses
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}