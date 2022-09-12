package com.example.movieplayer.ui.tvseries

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.view.MenuProvider
import com.example.movieplayer.R

class TVSeriesMenuProvider(private val onSearchClicked: () -> Unit) : MenuProvider {
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.appbar_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.search_item -> onSearchClicked()
        }
        return true
    }
}
