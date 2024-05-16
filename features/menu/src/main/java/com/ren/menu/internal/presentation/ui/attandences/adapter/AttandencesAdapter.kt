package com.ren.menu.internal.presentation.ui.attandences.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ren.menu.databinding.ItemAttendancesBinding
import com.ren.menu.databinding.ItemNewsBinding
import com.ren.menu.internal.domain.entities.attandences.Attandences
import com.ren.menu.internal.domain.entities.news.News

class AttandencesAdapter() :
    ListAdapter<Attandences, AttandencesAdapter.AttandencesViewHolder>(diffUtil) {

    inner class AttandencesViewHolder(private val binding: ItemAttendancesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: Attandences) {
            binding.tvLesson.text = item.lesson.toString()
            binding.tvAttandences.text = item.status.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttandencesViewHolder {
        return AttandencesViewHolder(
            ItemAttendancesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AttandencesViewHolder, position: Int) {
        getItem(position).let {
            holder.onBind(it)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Attandences>() {
            override fun areItemsTheSame(
                oldItem: Attandences,
                newItem: Attandences
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Attandences, newItem: Attandences
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}