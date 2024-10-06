package com.yuch.listanime.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yuch.listanime.core.databinding.ItemAnimeBinding
import com.yuch.listanime.core.domain.model.Anime

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
            loadImage(data.imageUrl)
            with(binding) {
                tvItemTitle.text = data.title
                tvItemEpisode.text = formatAnimeInfo(data.type, data.episodes)
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }

        private fun loadImage(imageUrl: String?) {
            Glide.with(itemView.context)
                .load(imageUrl)
                .into(binding.ivItemImage)
        }

        private fun formatAnimeInfo(type: String?, episodes: Int?): String {
            val epsInfo = if (episodes != null) "$episodes Episode" else "? Episode"
            return "$type ($epsInfo)"
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
