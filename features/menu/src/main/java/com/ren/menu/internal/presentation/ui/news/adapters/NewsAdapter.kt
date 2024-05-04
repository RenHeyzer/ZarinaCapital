package com.ren.menu.internal.presentation.ui.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ren.menu.databinding.ItemNewsBinding
import com.ren.menu.internal.domain.entities.news.News

class NewsAdapter() :
    ListAdapter<News, NewsAdapter.NewsViewHolder>(diffUtil) {

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: News) {
            binding.tvData.text = item.startDatetime
            binding.tvName.text = item.title
            Glide.with(binding.imView.context).load(item.image).into(binding.imView)
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