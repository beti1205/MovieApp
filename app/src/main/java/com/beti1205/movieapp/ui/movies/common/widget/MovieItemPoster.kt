package com.beti1205.movieapp.ui.movies.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.movies.common.MoviePreviewDataProvider

@Composable
fun MovieItemPoster(
    posterPath: String?,
    modifier: Modifier = Modifier
) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    val imageUri = "$baseUrl$posterPath"
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUri)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder_image),
        error = painterResource(id = R.drawable.error_image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(300.dp)
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun MovieItemPosterPreview() {
    MovieItemPoster(posterPath = MoviePreviewDataProvider.movie.posterPath)
}
