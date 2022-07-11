package com.example.movieplayer.ui.tvseries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplayer.databinding.TvseriesItemBinding
import com.example.movieplayer.feature.fetchtvseries.data.TVSeries

class TVSeriesAdapter : PagingDataAdapter<TVSeries, TVSeriesAdapter.ViewHolder>(
        DiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TvseriesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class ViewHolder(
            private val binding: TvseriesItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TVSeries) {
            binding.tvseries = item
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TVSeries>() {
        override fun areItemsTheSame(oldItem: TVSeries, newItem: TVSeries): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TVSeries, newItem: TVSeries): Boolean {
            return oldItem == newItem
        }


    }
}