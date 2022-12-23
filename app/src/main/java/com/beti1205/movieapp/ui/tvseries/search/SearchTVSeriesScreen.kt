package com.beti1205.movieapp.ui.tvseries.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.PagingTVSeriesPreviewDataProvider
import com.beti1205.movieapp.ui.tvseries.search.widget.SearchTVSeriesList
import kotlinx.coroutines.flow.flowOf

@Composable
fun SearchTVSeriesScreen(
    viewModel: SearchTVSeriesViewModel,
    onTVSeriesClicked: (TVSeries) -> Unit
) {
    val searchTVSeriesItems = viewModel.querySearchResults.collectAsLazyPagingItems()

    SearchTVSeriesScreen(
        searchTVSeriesItems = searchTVSeriesItems,
        onTVSeriesClicked = onTVSeriesClicked
    )
}

@Composable
fun SearchTVSeriesScreen(
    searchTVSeriesItems: LazyPagingItems<TVSeries>,
    onTVSeriesClicked: (TVSeries) -> Unit
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SearchTVSeriesList(
                searchTVSeriesItems = searchTVSeriesItems,
                onTVSeriesClicked = onTVSeriesClicked
            )
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SearchTVSeriesScreenPreview() {
    val items = flowOf(PagingTVSeriesPreviewDataProvider.pagingData).collectAsLazyPagingItems()
    MovieAppTheme {
        Surface {
            SearchTVSeriesScreen(searchTVSeriesItems = items, onTVSeriesClicked = {})
        }
    }
}
