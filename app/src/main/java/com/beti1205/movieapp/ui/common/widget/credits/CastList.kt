/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget.credits

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.credits.data.Cast
import com.beti1205.movieapp.ui.common.widget.listItemHorizontalPadding
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun CastList(
    cast: List<Cast>,
    onPersonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .padding(16.dp)
            .animateContentSize()
    ) {
        itemsIndexed(
            items = cast,
            key = { _, item -> item.id }
        ) { index, item ->
            Column(
                modifier = Modifier
                    .listItemHorizontalPadding(cast, index)
                    .width(100.dp)
            ) {
                CreditItem(
                    path = item.path,
                    name = item.name,
                    id = item.id,
                    description = item.character,
                    onPersonClicked = onPersonClicked
                )
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
fun CastListPreview(@PreviewParameter(CastListPreviewProvider::class) castList: List<Cast>) {
    MovieAppTheme {
        Surface {
            CastList(
                cast = castList,
                onPersonClicked = {}
            )
        }
    }
}
