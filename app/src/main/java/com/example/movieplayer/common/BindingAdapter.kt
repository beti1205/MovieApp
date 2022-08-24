package com.example.movieplayer.common

import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieplayer.R
import com.example.movieplayer.feature.fetchcredits.data.Cast
import com.example.movieplayer.feature.fetchcredits.data.Crew
import com.example.movieplayer.feature.fetchtvseriesdetails.data.Season
import com.example.movieplayer.ui.details.movie.CastAdapter
import com.example.movieplayer.ui.details.movie.CrewAdapter
import com.example.movieplayer.ui.details.tvseries.SeasonArrayAdapter

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    val imageUri = "$baseUrl$url"
    imageView.load(imageUri) {
        crossfade(true)
        placeholder(R.drawable.placeholder_image)
        error(R.drawable.error_image)
    }
}

@BindingAdapter("listCrewData")
fun bindCrewRecyclerView(recyclerView: RecyclerView, data: List<Crew>?) {
    val adapter = recyclerView.adapter as CrewAdapter
    adapter.submitList(data)
}

@BindingAdapter("listCastData")
fun bindCastRecyclerView(recyclerView: RecyclerView, data: List<Cast>?) {
    val adapter = recyclerView.adapter as CastAdapter
    adapter.submitList(data)
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
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
