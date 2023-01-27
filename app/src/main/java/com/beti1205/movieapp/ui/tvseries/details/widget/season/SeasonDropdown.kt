package com.beti1205.movieapp.ui.tvseries.details.widget.season

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuBoxScope
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.tvseriesdetails.data.Season
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SeasonDropdown(
    selectedSeason: Season?,
    onSeasonSelected: (Season) -> Unit,
    seasons: List<Season>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        SeasonDropdownTextField(selectedSeason, expanded)
        SeasonExposedDropdownMenu(
            seasons = seasons,
            onSeasonSelected = onSeasonSelected,
            expanded = expanded,
            onSetExpanded = { expanded = it },
            focusManager = focusManager
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExposedDropdownMenuBoxScope.SeasonExposedDropdownMenu(
    seasons: List<Season>,
    onSeasonSelected: (Season) -> Unit,
    expanded: Boolean,
    onSetExpanded: (Boolean) -> Unit,
    focusManager: FocusManager
) {
    ExposedDropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            onSetExpanded(false)
            focusManager.clearFocus()
        }
    ) {
        seasons.forEach { season ->
            DropdownMenuItem(onClick = {
                onSeasonSelected(season)
                onSetExpanded(false)
                focusManager.clearFocus()
            }) {
                Text(text = season.name)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SeasonDropdownTextField(
    selectedSeason: Season?,
    expanded: Boolean
) {
    TextField(
        value = selectedSeason?.name ?: "",
        onValueChange = { },
        label = { Text(text = stringResource(id = R.string.season_hint_label)) },
        readOnly = true,
        trailingIcon = {
            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedLabelColor = MaterialTheme.colors.secondary,
            focusedLabelColor = MaterialTheme.colors.secondaryVariant,
            unfocusedIndicatorColor = MaterialTheme.colors.secondary,
            focusedIndicatorColor = MaterialTheme.colors.secondaryVariant,
            trailingIconColor = MaterialTheme.colors.secondary
        )
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SeasonDropdownPreview(
    @PreviewParameter(SeasonDropdownPreviewProvider::class)
    seasonsList: List<Season>
) {
    MovieAppTheme {
        Surface {
            SeasonDropdown(
                selectedSeason = seasonsList.first(),
                onSeasonSelected = {},
                seasons = seasonsList
            )
        }
    }
}

class SeasonDropdownPreviewProvider : PreviewParameterProvider<List<Season>> {
    override val values = sequenceOf(
        listOf(
            Season(
                airDate = "2021-09-21",
                episodeCount = 10,
                id = 170644,
                name = "Limited Series",
                overview = "In prison, Jeff's newfound fame makes him a target.",
                posterPath = "/h7YlJ1Mhg6jCZiHToUiKqHdzMO9.jpg",
                seasonNumber = 1
            ),
            Season(
                airDate = "2008-01-20",
                episodeCount = 7,
                id = 3572,
                name = "Season 1",
                overview = "When an unassuming high school chemistry teacher discovers he has " +
                    "a rare form of lung cancer, he decides to team up with a former student " +
                    "and create a top of the line crystal meth in a used RV, to provide for his " +
                    "family once he is gone.",
                posterPath = "/1BP4xYv9ZG4ZVHkL7ocOziBbSYH.jpg",
                seasonNumber = 1
            )
        )
    )
}
