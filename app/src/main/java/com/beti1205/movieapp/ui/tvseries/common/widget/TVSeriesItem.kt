package com.beti1205.movieapp.ui.tvseries.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.ui.common.widget.ItemPoster
import com.beti1205.movieapp.ui.common.widget.ItemTitle
import com.beti1205.movieapp.ui.common.widget.Rating
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.TVSeriesPreviewDataProvider

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TVSeriesItem(
    tvSeries: TVSeries,
    onTVSeriesClicked: (TVSeries) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onTVSeriesClicked(tvSeries) },
        shape = RoundedCornerShape(16.dp),
        contentColor = MaterialTheme.colors.onPrimary,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        modifier = modifier.padding(8.dp),
        elevation = 16.dp
    ) {
        Column {
            ItemPoster(posterPath = tvSeries.posterPath)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                ItemTitle(
                    title = tvSeries.name,
                    modifier = Modifier.weight(1f)
                )
                Rating(votes = tvSeries.votes, modifier = Modifier.padding(top = 8.dp, end = 8.dp))
            }
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun TVSeriesItemPreview() {
    MovieAppTheme {
        Surface {
            TVSeriesItem(
                tvSeries = TVSeriesPreviewDataProvider.tvSeries,
                onTVSeriesClicked = {}
            )
        }
    }
}
