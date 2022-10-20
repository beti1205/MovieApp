package com.beti1205.movieapp.ui.movies.details.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchcredits.data.Crew

@Composable
fun CrewList(
    crew: List<Crew>?,
    modifier: Modifier = Modifier
) {
    if (crew != null) {
        LazyRow(modifier = modifier.padding(16.dp)) {
            itemsIndexed(
                items = crew,
                key = { _, item -> item.id }
            ) { index, item ->
                Column(
                    modifier = Modifier
                        .listItemHorizontalPadding(crew, index)
                        .width(100.dp)
                ) {
                    CreditItem(
                        path = item.path,
                        name = item.name,
                        description = item.job
                    )
                }
            }
        }
    }
}
