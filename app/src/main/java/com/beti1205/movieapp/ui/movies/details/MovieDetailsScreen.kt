package com.beti1205.movieapp.ui.movies.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import com.beti1205.movieapp.ui.movies.details.widget.CastList
import com.beti1205.movieapp.ui.movies.details.widget.CrewList
import com.beti1205.movieapp.ui.movies.details.widget.MovieDetails
import com.beti1205.movieapp.ui.movies.details.widget.SectionTitle
import com.beti1205.movieapp.ui.movies.details.widget.StandardDivider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel) {
    val movie by viewModel.selectedMovie.observeAsState()
    val cast by viewModel.cast.observeAsState()
    val crew by viewModel.crew.observeAsState()

    MovieDetailsScreen(
        movie = movie,
        cast = cast,
        crew = crew
    )
}

@Composable
fun MovieDetailsScreen(movie: Movie?, cast: List<Cast>?, crew: List<Crew>?) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                MovieDetails(movie)
                StandardDivider()
                SectionTitle(text = stringResource(id = R.string.cast))
                CastList(cast)
                StandardDivider()
                SectionTitle(text = stringResource(id = R.string.crew))
                CrewList(crew)
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
fun MovieDetailsScreenPreview() {
    MovieDetailsScreen(
        movie = Movie(
            id = 985939,
            title = "The Godfather II",
            overview = "For best friends Becky and Hunter, life is all about conquering fears and pushing limits.",
            popularity = 5456.118,
            adult = false,
            voteCount = 1300,
            voteAverage = 7.4,
            language = "en",
            posterPath = "/spCAxD99U1A6jsiePFoqdEcY0dG.jpg",
            originalTitle = "Fall",
            releaseDate = "2022-08-11"
        ),
        cast = listOf(
            Cast(
                id = 1,
                name = "Grace Caroline Currey",
                popularity = 8.9,
                character = "Becky",
                path = "/6chZcnjWEiFfpmB6D5BR9YUeIs9.jpg"
            )
        ),
        crew = listOf(
            Crew(
                id = 90812,
                name = "Scott Mann",
                popularity = 7.356,
                job = "Director",
                department = "Directing",
                path = "/8WygpUzfdfztZQqxGE5zn3rCedJ.jpg"
            )
        )
    )
}
