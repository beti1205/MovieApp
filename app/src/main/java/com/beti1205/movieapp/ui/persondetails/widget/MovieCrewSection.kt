package com.beti1205.movieapp.ui.persondetails.widget

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.ui.persondetails.Section
import com.beti1205.movieapp.ui.persondetails.SectionType

fun LazyListScope.movieCrewSection(
    movieCrewSection: Section<PersonMovieCrew>,
    onMovieClicked: (Int) -> Unit,
    onExpandedChanged: (
        SectionType,
        Boolean
    ) -> Unit
) {
    if (movieCrewSection.isNotEmpty()) {
        item {
            SectionSubtitle(text = stringResource(id = R.string.movies_label))
        }
    }

    items(movieCrewSection.items) { item ->
        MovieCrewItem(item, onMovieClicked)
    }

    if (movieCrewSection.expandable) {
        item {
            MovieCrewSectionButton(movieCrewSection, onExpandedChanged)
        }
    }
}

@Composable
fun MovieCrewSectionButton(
    movieCrewSection: Section<PersonMovieCrew>,
    onExpandedChanged: (
        SectionType,
        Boolean
    ) -> Unit
) {
    SectionButton(expanded = movieCrewSection.expanded, onExpandedChanged = { expanded ->
        onExpandedChanged(
            SectionType.MOVIE_CAST,
            expanded
        )
    })
}

@Composable
fun MovieCrewItem(
    item: PersonMovieCrew,
    onMovieClicked: (Int) -> Unit
) {
    FilmographyItem(
        date = item.releaseDate,
        name = item.title,
        description = item.job,
        id = item.id,
        rating = item.votes,
        onItemRowClicked = onMovieClicked
    )
}
