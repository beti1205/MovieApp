package com.beti1205.movieapp.ui.movies.list.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beti1205.movieapp.ui.theme.Mercury
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun OrderChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        colors = ChipDefaults.filterChipColors(
            selectedBackgroundColor = MaterialTheme.colors.secondaryVariant,
            selectedContentColor = MaterialTheme.colors.onSecondary,
            backgroundColor = Mercury,
            contentColor = Color.Black
        ),
        modifier = modifier.padding(4.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun OrderGroupPreview() {
    MovieAppTheme {
        Surface {
            OrderChip(text = "popular", selected = false, onClick = { })
        }
    }
}
