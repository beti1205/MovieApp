/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account.widget.favoritemovies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.ui.common.widget.listItemHorizontalPadding

@Composable
fun FavoriteMovieList(
    movies: List<Movie>,
    onMovieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.padding(16.dp)
    ) {
        itemsIndexed(
            items = movies
        ) { index, item ->
            Column(
                modifier = Modifier
                    .listItemHorizontalPadding(movies, index)
                    .width(100.dp)
            ) {
                FavoriteItem(
                    poster = item.posterPath,
                    title = item.title,
                    id = item.id,
                    onItemClicked = { onMovieClicked(item.id) }
                )
            }
        }
    }
}
