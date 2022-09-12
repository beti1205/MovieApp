package com.beti1205.movieapp.ui.tvseries.details

import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Genre
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("listEpisodesData")
fun bindEpisodeRecyclerView(recyclerView: RecyclerView, data: List<Episode>) {
    val adapter = recyclerView.adapter as EpisodeAdapter
    adapter.submitList(data)
}

@BindingAdapter("seasons")
fun bindSeasonAutoCompleteTextView(
    autoCompleteTextView: AutoCompleteTextView,
    data: List<Season>?
) {
    val context = autoCompleteTextView.context
    val adapter = SeasonArrayAdapter(context, data ?: emptyList())
    autoCompleteTextView.setAdapter(adapter)
}

@BindingAdapter("android:text", "filter")
fun AutoCompleteTextView.updateFilter(text: String?, filter: Boolean) {
    this.setText(text, filter)
}

@BindingAdapter("onItemClicked")
fun setOnItemClickListener(view: AutoCompleteTextView, listener: AdapterView.OnItemClickListener) {
    view.onItemClickListener = listener
}

@BindingAdapter("genres")
fun bindGenres(chipGroup: ChipGroup, data: List<Genre>?) {
    val context = chipGroup.context
    data?.forEach { genre ->
        chipGroup.addView(
            Chip(context).apply {
                text = genre.name
            }
        )
    }
}
