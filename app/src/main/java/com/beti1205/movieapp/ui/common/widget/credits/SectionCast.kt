package com.beti1205.movieapp.ui.common.widget.credits

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.credits.data.Cast
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
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SectionCastPreview(@PreviewParameter(CastListPreviewProvider::class) castList: List<Cast>) {
    MovieAppTheme {
        Surface {
            SectionCast(
                cast = castList,
                onPersonClicked = {}
            )
        }
    }
}
