package com.beti1205.movieapp.ui.account.widget.favoritemovies

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun FavoriteMoviesSection(
    movies: List<Movie>,
    onMovieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultCard {
        Column(modifier = modifier) {
            AccountSectionHeader(
                text = "Favorite movies",
                modifier = Modifier.padding(top = 8.dp)
            )
            AnimatedVisibility(
                visible = movies.isNotEmpty()
            ) {
                FavoriteMovieList(movies = movies, onMovieClicked = onMovieClicked)
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
fun FavoriteMoviesSectionPreview(
    @PreviewParameter(
        FavoriteMoviesSectionPreviewProvider::class
    ) movies: List<Movie>
) {
    MovieAppTheme {
        Surface(Modifier.fillMaxSize()) {
            FavoriteMoviesSection(
                movies = movies,
                onMovieClicked = {}
            )
        }
    }
}

class FavoriteMoviesSectionPreviewProvider : PreviewParameterProvider<List<Movie>> {
    override val values = sequenceOf(
        listOf(
            Movie(
                id = 985939,
                title = "The Godfather II",
                overview = "In the continuing saga of the Corleone crime family, a young " +
                    "Vito Corleone grows up in Sicily and in 1910s New York.",
                popularity = 63.191,
                adult = false,
                voteCount = 10364,
                voteAverage = 8.602,
                language = "en",
                posterPath = "/spCAxD99U1A6jsiePFoqdEcY0dG.jpg",
                originalTitle = "The Godfather Part II",
                releaseDate = "1974-12-20"
            ),
            Movie(
                id = 497,
                title = "The Green Mile",
                overview = "A supernatural tale set on death row in a Southern prison," +
                    " where gentle giant John Coffey possesses the mysterious power to " +
                    "heal people's ailments.When the cell block's head guard, Paul Edgecomb," +
                    " recognizes Coffey's miraculous gift, he tries desperately to help stave off " +
                    "the condemned man's execution.",
                popularity = 95.84,
                adult = false,
                voteCount = 14744,
                voteAverage = 8.509,
                language = "en",
                posterPath = null,
                originalTitle = "The Green Mile",
                releaseDate = null
            )
        ),
        emptyList()
    )
}
