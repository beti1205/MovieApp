package com.beti1205.movieapp.ui.persondetails.widget

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.ui.persondetails.Section
import com.beti1205.movieapp.ui.persondetails.SectionType

fun LazyListScope.tvCastSection(
    tvCastSection: Section<PersonTVSeriesCast>,
    onTVSeriesClicked: (Int) -> Unit,
    onExpandedChanged: (
        SectionType,
        Boolean
    ) -> Unit
) {
    if (tvCastSection.isNotEmpty()) {
        item {
            SectionSubtitle(text = stringResource(id = R.string.tv_series_label))
        }
    }

    items(tvCastSection.items) { item ->
        TVCastItem(item, onTVSeriesClicked)
    }

    if (tvCastSection.expandable) {
        item {
            TVCastSectionButton(tvCastSection, onExpandedChanged)
        }
    }
}

@Composable
fun TVCastSectionButton(
    tvCastSection: Section<PersonTVSeriesCast>,
    onExpandedChanged: (SectionType, Boolean) -> Unit
) {
    SectionButton(expanded = tvCastSection.expanded, onExpandedChanged = { expanded ->
        onExpandedChanged(
            SectionType.TV_CAST,
            expanded
        )
    })
}

@Composable
fun TVCastItem(
    item: PersonTVSeriesCast,
    onTVSeriesClicked: (Int) -> Unit
) {
    FilmographyItem(
        date = item.firstAirDate,
        name = item.name,
        description = item.character,
        id = item.id,
        rating = item.votes,
        onItemRowClicked = onTVSeriesClicked
    )
}
