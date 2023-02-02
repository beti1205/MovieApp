/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.persondetails.widget

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCrew
import com.beti1205.movieapp.ui.persondetails.Section
import com.beti1205.movieapp.ui.persondetails.SectionType

fun LazyListScope.tvCrewSection(
    tvCrewSection: Section<PersonTVSeriesCrew>,
    onTVSeriesClicked: (Int) -> Unit,
    onExpandedChanged: (
        SectionType,
        Boolean
    ) -> Unit
) {
    if (tvCrewSection.isNotEmpty()) {
        item {
            SectionSubtitle(text = stringResource(id = R.string.tv_series_label))
        }
    }

    items(tvCrewSection.items) { item ->
        TVCrewItem(item, onTVSeriesClicked)
    }

    if (tvCrewSection.expandable) {
        item {
            TVCrewSectionButton(tvCrewSection, onExpandedChanged)
        }
    }
}

@Composable
fun TVCrewSectionButton(
    tvCastSection: Section<PersonTVSeriesCrew>,
    onExpandedChanged: (
        SectionType,
        Boolean
    ) -> Unit
) {
    SectionButton(expanded = tvCastSection.expanded, onExpandedChanged = { expanded ->
        onExpandedChanged(
            SectionType.TV_CREW,
            expanded
        )
    })
}

@Composable
fun TVCrewItem(
    item: PersonTVSeriesCrew,
    onTVSeriesClicked: (Int) -> Unit
) {
    FilmographyItem(
        date = item.firstAirDate,
        name = item.name,
        description = item.job,
        id = item.id,
        rating = item.votes,
        onItemRowClicked = onTVSeriesClicked
    )
}
