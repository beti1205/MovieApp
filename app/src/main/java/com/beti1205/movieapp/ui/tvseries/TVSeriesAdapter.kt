package com.beti1205.movieapp.ui.tvseries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.beti1205.movieapp.databinding.TvseriesItemBinding
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries

class TVSeriesAdapter(
    private val onClick: (view: View, tvSeries: TVSeries) -> Unit
) : PagingDataAdapter<TVSeries, TVSeriesAdapter.ViewHolder>(DiffCallback) {

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
            holder.bind(onClick, item)
        }
    }

    inner class ViewHolder(
        val binding: TvseriesItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(onClick: (View, TVSeries) -> Unit, item: TVSeries) {
            binding.onClick = onClick
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
