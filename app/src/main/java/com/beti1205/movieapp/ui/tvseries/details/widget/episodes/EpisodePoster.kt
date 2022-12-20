package com.beti1205.movieapp.ui.tvseries.details.widget.episodes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beti1205.movieapp.R

@Composable
fun EpisodePoster(
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
        placeholder = painterResource(R.drawable.placeholder_image_horizontal),
        error = painterResource(id = R.drawable.error_image),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = modifier.fillMaxWidth()
    )
}
