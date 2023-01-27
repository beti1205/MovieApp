package com.beti1205.movieapp.ui.tvseries.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.feature.tvseries.domain.TVOrder
import com.beti1205.movieapp.ui.common.widget.list.ListTopAppBar
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.PagingTVSeriesPreviewDataProvider
import com.beti1205.movieapp.ui.tvseries.common.widget.TVSeriesList
import com.beti1205.movieapp.ui.tvseries.list.widget.TVOrderChipGroup
import com.beti1205.movieapp.ui.tvseries.list.widget.TVSeriesListError
import kotlinx.coroutines.flow.flowOf

@Composable
fun TVSeriesScreen(
    viewModel: TVSeriesViewModel,
    onTVSeriesClicked: (TVSeries) -> Unit,
    onSearchClicked: () -> Unit
) {
    val tvSeriesListItems = viewModel.tvSeries.collectAsLazyPagingItems()
    val selectedTVSeriesOrder by viewModel.order.collectAsState()

    TVSeriesScreen(
        tvSeriesListItems = tvSeriesListItems,
        selectedTVSeriesOrder = selectedTVSeriesOrder,
        onSelectedTVSeriesOrderChanged = viewModel::onOrderChanged,
        onTVSeriesClicked = onTVSeriesClicked,
        onSearchClicked = onSearchClicked
    )
}

@Composable
fun TVSeriesScreen(
    tvSeriesListItems: LazyPagingItems<TVSeries>,
    selectedTVSeriesOrder: TVOrder,
    onSelectedTVSeriesOrderChanged: (TVOrder) -> Unit,
    onTVSeriesClicked: (TVSeries) -> Unit,
    onSearchClicked: () -> Unit
) {
    MovieAppTheme {
        Scaffold(
            topBar = {
                ListTopAppBar(
                    title = stringResource(id = R.string.tv_series_label),
                    onSearchClicked = onSearchClicked
                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                TVOrderChipGroup(
                    selectedTVSeriesOrder = selectedTVSeriesOrder,
                    onSelectedTVSeriesOrderChanged = onSelectedTVSeriesOrderChanged
                )
                TVSeriesList(
                    tvSeriesListItems = tvSeriesListItems,
                    onTVSeriesClicked = onTVSeriesClicked,
                    modifier = Modifier.weight(1f)
                )
                TVSeriesListError(tvSeriesListItems)
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
fun TVSeriesScreenPreview() {
    val items = flowOf(PagingTVSeriesPreviewDataProvider.pagingData).collectAsLazyPagingItems()
    TVSeriesScreen(
        tvSeriesListItems = items,
        selectedTVSeriesOrder = TVOrder.POPULAR,
        onSelectedTVSeriesOrderChanged = {},
        onTVSeriesClicked = {},
        onSearchClicked = {}
    )
}
