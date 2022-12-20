package com.beti1205.movieapp.ui.persondetails.widget.person

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beti1205.movieapp.R

@Composable
fun PersonPoster(
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
        modifier = modifier
            .width(150.dp)
    )
}
