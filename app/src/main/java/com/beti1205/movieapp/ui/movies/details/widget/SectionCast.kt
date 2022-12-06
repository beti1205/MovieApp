package com.beti1205.movieapp.ui.movies.details.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.movies.details.MovieDetailsPreviewDataProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun SectionCast(
    cast: List<Cast>?,
    onPersonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SectionTitle(text = stringResource(id = R.string.cast))
        CastList(
            cast = cast,
            onPersonClicked = onPersonClicked
        )
        StandardDivider()
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SectionCastPreview() {
    MovieAppTheme {
        Surface {
            SectionCast(
                cast = MovieDetailsPreviewDataProvider.credits.cast,
                onPersonClicked = {}
            )
        }
    }
}
