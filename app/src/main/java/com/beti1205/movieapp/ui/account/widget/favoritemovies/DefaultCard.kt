package com.beti1205.movieapp.ui.account.widget.favoritemovies

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DefaultCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    contentColor: Color = MaterialTheme.colors.onPrimary,
    elevation: Dp = 4.dp,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = shape,
        contentColor = contentColor,
        elevation = elevation,
        content = content
    )
}
