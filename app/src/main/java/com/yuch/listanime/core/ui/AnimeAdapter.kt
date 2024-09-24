package com.yuch.listanime.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yuch.listanime.core.domain.model.Anime
import com.yuch.listanime.databinding.ItemAnimeBinding

class AnimeAdapter : ListAdapter<Anime, AnimeAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Anime) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemAnimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Anime) {
            val type = data.type
            val eps = data.episodes

            with(binding) {
                Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                if (eps != null) {
                    val animeInfo = "$type ($eps Episode)"
                    tvItemEpisode.text = animeInfo
                } else {
                    val animeInfo = "$type (? Episode)"
                    tvItemEpisode.text = animeInfo
                }
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Anime>() {
            override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem.malId == newItem.malId
            }

            override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
                return oldItem == newItem
            }
        }
    }
}