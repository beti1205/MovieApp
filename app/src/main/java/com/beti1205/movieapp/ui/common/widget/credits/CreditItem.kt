/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget.credits

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.credits.data.Cast
import com.beti1205.movieapp.ui.common.widget.HorizontalListItemPoster
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun CreditItem(
    path: String?,
    name: String,
    id: Int,
    description: String,
    onPersonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.clickable { onPersonClicked(id) }) {
        HorizontalListItemPoster(posterPath = path)
        Text(
            text = name,
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.body1
        )
        Text(
            text = description,
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun CreditItemPreview(@PreviewParameter(CastItemPreviewProvider::class) cast: Cast) {
    MovieAppTheme {
        Surface {
            CreditItem(
                path = cast.path,
                name = cast.name,
                id = cast.id,
                description = cast.character,
                onPersonClicked = {}
            )
        }
    }
}

class CastItemPreviewProvider : PreviewParameterProvider<Cast> {
    override val values = sequenceOf(
        Cast(
            id = 1,
            name = "Grace Caroline Currey",
            popularity = 8.9,
            character = "Becky",
            path = "/6chZcnjWEiFfpmB6D5BR9YUeIs9.jpg"
        ),
        Cast(
            id = 2,
            name = "Grace Caroline Currey",
            popularity = 8.9,
            character = "Becky",
            path = null
        )
    )
}
