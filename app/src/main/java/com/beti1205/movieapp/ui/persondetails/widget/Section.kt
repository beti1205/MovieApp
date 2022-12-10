package com.beti1205.movieapp.ui.persondetails.widget

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.res.stringResource
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.persondetails.Section

fun LazyListScope.section(
    section: Section,
    onExpandedChanged: (Boolean) -> Unit,
    onMovieClicked: (Int) -> Unit
) {
    when (section) {
        is Section.MovieCast -> movieCastSection(section, onMovieClicked, onExpandedChanged)
        is Section.TVCast -> tvCastSection(section, onExpandedChanged)
        is Section.MovieCrew -> movieCrewSection(section, onMovieClicked, onExpandedChanged)
        is Section.TVCrew -> tvCrewSection(section, onExpandedChanged)
    }
}

private fun LazyListScope.movieCastSection(
    section: Section.MovieCast,
    onMovieClicked: (Int) -> Unit,
    onExpandedChanged: (Boolean) -> Unit
) {
    item {
        SectionHeader(text = stringResource(id = R.string.section_header_movie_cast))
        SectionSubtitle(text = stringResource(id = R.string.movies_label))
    }
    items(section.cast) { item ->
        FilmographyItem(
            date = item.releaseDate,
            name = item.title,
            description = item.character ?: stringResource(id = R.string.filmography_unknown),
            movieId = item.id,
            onMovieClicked = onMovieClicked
        )
    }
    item {
        SectionButton(expanded = section.expanded, onExpandedChanged = onExpandedChanged)
    }
}

private fun LazyListScope.tvCastSection(
    section: Section.TVCast,
    onExpandedChanged: (Boolean) -> Unit
) {
    item { SectionSubtitle(text = stringResource(id = R.string.tv_series_label)) }
    items(section.cast) { item ->
        FilmographyItem(
            date = item.firstAirDate,
            name = item.name,
            description = item.character,
            movieId = item.id
        )
    }
    item {
        SectionButton(expanded = section.expanded, onExpandedChanged = onExpandedChanged)
        StandardDivider()
    }
}

private fun LazyListScope.tvCrewSection(
    section: Section.TVCrew,
    onExpandedChanged: (Boolean) -> Unit
) {
    item { SectionSubtitle(text = stringResource(id = R.string.tv_series_label)) }
    items(section.crew) { item ->
        FilmographyItem(
            date = item.firstAirDate,
            name = item.name,
            description = item.job,
            movieId = item.id
        )
    }
    item {
        SectionButton(expanded = section.expanded, onExpandedChanged = onExpandedChanged)
        StandardDivider()
    }
}

private fun LazyListScope.movieCrewSection(
    section: Section.MovieCrew,
    onMovieClicked: (Int) -> Unit,
    onExpandedChanged: (Boolean) -> Unit
) {
    item {
        SectionHeader(text = stringResource(id = R.string.section_header_movie_crew))
        SectionSubtitle(text = stringResource(id = R.string.movies_label))
    }
    items(section.crew) { item ->
        FilmographyItem(
            date = item.releaseDate,
            name = item.title,
            description = item.job,
            movieId = item.id,
            onMovieClicked = onMovieClicked
        )
    }
    item { SectionButton(expanded = section.expanded, onExpandedChanged = onExpandedChanged) }
}
