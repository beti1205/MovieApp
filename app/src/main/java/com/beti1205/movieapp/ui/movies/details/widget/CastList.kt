package com.beti1205.movieapp.ui.movies.details.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchcredits.data.Cast

@Composable
fun CastList(
    cast: List<Cast>?,
    modifier: Modifier = Modifier
) {
    if (cast != null) {
        LazyRow(modifier = modifier.padding(16.dp)) {
            itemsIndexed(
                items = cast,
                key = { _, item -> item.id }
            ) { index, item ->
                Column(
                    modifier = Modifier
                        .listItemHorizontalPadding(cast, index)
                        .width(100.dp)
                ) {
                    CreditItem(
                        path = item.path,
                        name = item.name,
                        description = item.character
                    )
                }
            }
        }
    }
}
