package com.beti1205.movieapp.ui.persondetails.widget

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.res.stringResource
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.persondetails.Section

fun LazyListScope.section(
    section: Section,
    onExpandedChanged: (Boolean) -> Unit
) {
    when (section) {
        is Section.MovieCast -> {
            item {
                SectionHeader(text = stringResource(id = R.string.section_header_movie_cast))
            }
            items(section.cast) { item ->
                FilmographyItem(
                    date = item.releaseDate,
                    name = item.title,
                    description = item.character
                )
            }
        }
        is Section.MovieCrew -> {
            item {
                SectionHeader(text = stringResource(id = R.string.section_header_movie_crew))
            }
            items(section.crew) { item ->
                FilmographyItem(
                    date = item.releaseDate,
                    name = item.title,
                    description = item.job
                )
            }
        }
    }
    item {
        SectionButton(onExpandedChanged, section.expanded)
        StandardDivider()
    }
}
