package com.beti1205.movieapp.ui.common.widget.reviews

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.reviews.data.Review
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun ReviewItem(
    item: Review,
    reviews: List<Review>,
    index: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        ReviewContent(item.content)
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            ReviewCreatedDate(item.createdDate, modifier = Modifier.weight(1f))
            ReviewAuthor(item.author)
        }
        if (index != reviews.size - 1) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
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
