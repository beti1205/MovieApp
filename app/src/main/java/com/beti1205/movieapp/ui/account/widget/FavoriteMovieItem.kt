package com.beti1205.movieapp.ui.account.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.beti1205.movieapp.ui.common.widget.HorizontalListItemPoster
import com.beti1205.movieapp.ui.common.widget.list.ItemTitle

@Composable
fun FavoriteMovieItem(
    poster: String?,
    title: String,
    id: Int,
    onMovieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.clickable { onMovieClicked(id) }) {
        HorizontalListItemPoster(posterPath = poster)
        ItemTitle(title = title)
    }
}
