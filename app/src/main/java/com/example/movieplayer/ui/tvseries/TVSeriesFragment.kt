package com.example.movieplayer.ui.tvseries

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.movieplayer.R
import com.example.movieplayer.databinding.TvseriesListBinding

class TVSeriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: TvseriesListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.tvseries_list,
            container,
            false
        )

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.appbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.search_item -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}