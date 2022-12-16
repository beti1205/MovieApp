package com.beti1205.movieapp.ui.persondetails.widget

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.persondetails.Section

fun LazyListScope.section(
    section: Section,
    onExpandedChanged: (Boolean) -> Unit,
    onMovieClicked: (Int) -> Unit,
    onTVSeriesClicked: (Int) -> Unit
) {
    when (section) {
        is Section.MovieCast -> movieCastSection(section, onMovieClicked, onExpandedChanged)
        is Section.TVCast -> tvCastSection(section, onTVSeriesClicked, onExpandedChanged)
        is Section.MovieCrew -> movieCrewSection(section, onMovieClicked, onExpandedChanged)
        is Section.TVCrew -> tvCrewSection(section, onTVSeriesClicked, onExpandedChanged)
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
            id = item.id,
            rating = item.votes,
            onItemRowClicked = onMovieClicked
        )
    }
    item {
        SectionButton(section, onExpandedChanged)
    }
}

private fun LazyListScope.tvCastSection(
    section: Section.TVCast,
    onTVSeriesClicked: (Int) -> Unit,
    onExpandedChanged: (Boolean) -> Unit
) {
    item { SectionSubtitle(text = stringResource(id = R.string.tv_series_label)) }
    items(section.cast) { item ->
        FilmographyItem(
            date = item.firstAirDate,
            name = item.name,
            description = item.character,
            id = item.id,
            rating = item.votes,
            onItemRowClicked = onTVSeriesClicked
        )
    }
    item {
        SectionButton(section, onExpandedChanged)
        StandardDivider()
    }
}

private fun LazyListScope.tvCrewSection(
    section: Section.TVCrew,
    onTVSeriesClicked: (Int) -> Unit,
    onExpandedChanged: (Boolean) -> Unit
) {
    item { SectionSubtitle(text = stringResource(id = R.string.tv_series_label)) }
    items(section.crew) { item ->
        FilmographyItem(
            date = item.firstAirDate,
            name = item.name,
            description = item.job,
            id = item.id,
            rating = item.votes,
            onItemRowClicked = onTVSeriesClicked
        )
    }
    item {
        SectionButton(section, onExpandedChanged)
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
            id = item.id,
            rating = item.votes,
            onItemRowClicked = onMovieClicked
        )
    }
    item {
        SectionButton(section, onExpandedChanged)
    }
}

@Composable
private fun SectionButton(
    section: Section,
    onExpandedChanged: (Boolean) -> Unit
) {
    if (section.expandable) {
        SectionButton(
            expanded = section.expanded,
            onExpandedChanged = onExpandedChanged
        )
    }
}
