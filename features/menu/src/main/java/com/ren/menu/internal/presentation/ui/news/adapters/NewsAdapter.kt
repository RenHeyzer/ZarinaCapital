package com.ren.menu.internal.presentation.ui.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ren.menu.databinding.ItemNewsBinding
import com.ren.menu.internal.domain.entities.news.News
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewsAdapter(val setItemClickListener: (id: Int) -> Unit) :
    ListAdapter<News, NewsAdapter.NewsViewHolder>(diffUtil) {

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: News) {
            // Исходный формат даты, предполагается, что он выглядит как "21.06.2024 22:06:33"
            val originalFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
            // Желаемый формат даты "7 августа в 15:30"
            val targetFormat = SimpleDateFormat("d MMMM HH:mm", Locale("ru"))

            val date: Date = originalFormat.parse(item.startDatetime)!!
            val formattedDate: String = targetFormat.format(date)

            binding.tvData.text = formattedDate
            binding.tvName.text = item.title
            Glide.with(binding.imView.context).load(item.image).into(binding.imView)

            itemView.setOnClickListener {
                getItem(adapterPosition)?.apply { setItemClickListener(item.id) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        getItem(position).let {
            holder.onBind(it)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(
                oldItem: News,
                newItem: News
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: News, newItem: News
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}