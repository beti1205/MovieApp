package com.beti1205.movieapp.ui.tvseries.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season

class SeasonArrayAdapter(
    context: Context,
    items: List<Season>
) : ArrayAdapter<Season>(context, R.layout.season_list_item, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            inflater.inflate(R.layout.season_list_item, parent, false)
        } else {
            convertView
        }

        val item = getItem(position)
        (view as TextView).text = item?.name

        return view
    }
}
