package com.beti1205.movieapp.ui.tvseries.details.widget.episodes

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.ui.common.widget.listItemHorizontalPadding
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun EpisodeList(
    episodes: List<Episode>?,
    modifier: Modifier = Modifier
) {
    if (episodes != null) {
        LazyRow(
            modifier = modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .animateContentSize()
        ) {
            itemsIndexed(
                items = episodes,
                key = { _, item -> item.id }
            ) { index, item ->
                Column(
                    modifier = Modifier
                        .listItemHorizontalPadding(episodes, index)
                        .width(300.dp)
                ) {
                    EpisodeItem(item)
                }
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
fun EpisodeListPreview(
    @PreviewParameter(EpisodeListPreviewProvider::class)
    episodesList: List<Episode>
) {
    MovieAppTheme {
        Surface {
            EpisodeList(episodes = episodesList)
        }
    }
}

class EpisodeListPreviewProvider : PreviewParameterProvider<List<Episode>> {
    override val values = sequenceOf(
        listOf(
            Episode(
                id = 1019694,
                name = "Mijo",
                overview = "As his troubles escalate to a boiling point, Jimmy finds himself in dire straits.",
                posterPath = "/8XFhyx4xFCY2nZPThgOUF42G4fI.jpg",
                episodeAirDate = "2015-02-09",
                episodeNumber = 1
            ),
            Episode(
                id = 62085,
                name = "Pilot",
                overview = "When an unassuming high school chemistry teacher discovers he has " +
                    "a rare form of lung cancer, he decides to team up with a former student" +
                    " and create a top of the line crystal meth in a used RV, to provide for" +
                    " his family once he is gone.",
                posterPath = "/ydlY3iPfeOAvu8gVqrxPoMvzNCn.jpg",
                episodeAirDate = "2008-01-20",
                episodeNumber = 1
            )
        )
    )
}
