package com.beti1205.movieapp.ui.tvseries.search.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.ui.movies.common.widget.PagingError
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.theme.SonicSilver
import com.beti1205.movieapp.ui.tvseries.common.PagingTVSeriesPreviewDataProvider
import kotlinx.coroutines.flow.flowOf

@Composable
fun SearchTVSeriesPagingError(
    items: LazyPagingItems<TVSeries>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        PagingError(
            onRetryClick = { items.retry() },
            textColor = SonicSilver
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SearchTVSeriesPagingErrorPreview() {
    val items = flowOf(PagingTVSeriesPreviewDataProvider.pagingData).collectAsLazyPagingItems()

    MovieAppTheme {
        Surface {
            SearchTVSeriesPagingError(items)
        }
    }
}
