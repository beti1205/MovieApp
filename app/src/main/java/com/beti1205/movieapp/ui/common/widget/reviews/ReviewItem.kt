/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget.reviews

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beti1205.movieapp.feature.reviews.data.Review
import com.beti1205.movieapp.ui.common.widget.AccountAvatar
import com.beti1205.movieapp.ui.common.widget.Avatar
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun ReviewItem(
    item: Review,
    reviews: List<Review>,
    index: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        val avatar = item.authorDetails.avatar

        Row {
            when {
                avatar != null ->
                    Column(modifier = Modifier.weight(1f)) {
                        ReviewerAvatar(avatar = avatar)
                    }
                else -> DefaultReviewerAvatar(item = item, modifier = modifier.weight(1f))
            }
            ReviewContent(
                text = item.content,
                modifier = Modifier
                    .weight(3f)
                    .padding(start = 8.dp)
            )
        }
        ReviewsInfo(item = item)
        if (index != reviews.size - 1) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
private fun ReviewerAvatar(avatar: String) {
    if (avatar.startsWith("/https")) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(avatar.removePrefix("/"))
                .crossfade(true)
                .build(),
            contentDescription = null
        )
    } else {
        AccountAvatar(
            avatar = avatar
        )
    }
}

@Composable
private fun ReviewsInfo(item: Review) {
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        ReviewCreatedDate(item.createdDate, modifier = Modifier.weight(1f))
        ReviewAuthor(item.author)
    }
}

@Composable
private fun DefaultReviewerAvatar(item: Review, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Avatar(
            text = item.author,
            size = 70.dp
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    heightDp = 2000
)
@Composable
fun ReviewItemPreview(
    @PreviewParameter(ReviewsPreviewProvider::class) reviews: List<Review>
) {
    MovieAppTheme {
        Surface {
            ReviewItem(item = reviews.first(), reviews = reviews, index = 0)
        }
    }
}
