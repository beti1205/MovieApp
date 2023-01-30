package com.beti1205.movieapp.ui.account.widget.favoritemovies

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.ui.common.widget.HorizontalListItemPoster
import com.beti1205.movieapp.ui.common.widget.list.ItemTitle
import com.beti1205.movieapp.ui.common.widget.list.ListItemPreviewProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun FavoriteMovieItem(
    poster: String?,
    title: String,
    id: Int,
    onMovieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.clickable { onMovieClicked(id) }) {
        HorizontalListItemPoster(posterPath = poster)
        ItemTitle(title = title)
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun FavoriteMovieItemPreview(@PreviewParameter(ListItemPreviewProvider::class) movie: Movie) {
    MovieAppTheme {
        Surface {
            FavoriteMovieItem(
                poster = movie.posterPath,
                title = movie.title,
                id = movie.id,
                onMovieClicked = {}
            )
        }
    }
}
