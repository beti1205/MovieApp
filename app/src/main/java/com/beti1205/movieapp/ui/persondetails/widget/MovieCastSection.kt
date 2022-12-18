package com.beti1205.movieapp.ui.persondetails.widget

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.ui.persondetails.Section
import com.beti1205.movieapp.ui.persondetails.SectionType

fun LazyListScope.movieCastSection(
    movieCastSection: Section<PersonMovieCast>,
    onMovieClicked: (Int) -> Unit,
    onExpandedChanged: (
        SectionType,
        Boolean
    ) -> Unit
) {
    if (movieCastSection.isNotEmpty()) {
        item {
            SectionSubtitle(text = stringResource(id = R.string.movies_label))
        }
    }

    items(movieCastSection.items) { item ->
        MovieCastItem(item, onMovieClicked)
    }

    if (movieCastSection.expandable) {
        item {
            MovieCastSectionButton(movieCastSection, onExpandedChanged)
        }
    }
}

@Composable
fun MovieCastSectionButton(
    movieCastSection: Section<PersonMovieCast>,
    onExpandedChanged: (SectionType, Boolean) -> Unit
) {
    SectionButton(expanded = movieCastSection.expanded, onExpandedChanged = { expanded ->
        onExpandedChanged(
            SectionType.MOVIE_CAST,
            expanded
        )
    })
}

@Composable
fun MovieCastItem(
    item: PersonMovieCast,
    onMovieClicked: (Int) -> Unit
) {
    FilmographyItem(
        date = item.releaseDate,
        name = item.title,
        description = item.character ?: stringResource(id = R.string.filmography_unknown),
        id = item.id,
        rating = item.votes,
        onItemRowClicked = onMovieClicked
    )
}
