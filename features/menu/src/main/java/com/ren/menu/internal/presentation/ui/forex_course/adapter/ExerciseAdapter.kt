package com.ren.menu.internal.presentation.ui.forex_course.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ren.menu.databinding.ItemLessonsBinding
import com.ren.menu.internal.domain.entities.lessons.HomeworksItem
import com.ren.theme.R

class ExerciseAdapter(private val context: Context) :
    ListAdapter<HomeworksItem, ExerciseAdapter.ExerciseViewHolder>(DiffUtilCallback()) {
    inner class ExerciseViewHolder(private val binding: ItemLessonsBinding) :
        ViewHolder(binding.root) {
        fun onBind(homeworksItem: HomeworksItem) {
            binding.apply {
                tvName.text = homeworksItem.name
                tvLesson.text = homeworksItem.id.toString()
                tvStartTime.text = context.getString(R.string.deadline)
                tvBetween.text = ""
                tvEndTime.text = homeworksItem.deadline
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            ItemLessonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    companion object {

        class DiffUtilCallback : DiffUtil.ItemCallback<HomeworksItem>() {

            override fun areItemsTheSame(oldItem: HomeworksItem, newItem: HomeworksItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: HomeworksItem,
                newItem: HomeworksItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}