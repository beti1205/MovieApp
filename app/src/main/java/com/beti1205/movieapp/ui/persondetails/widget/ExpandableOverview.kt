package com.beti1205.movieapp.ui.persondetails.widget

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.ui.common.widget.Overview
import com.beti1205.movieapp.ui.persondetails.widget.person.PersonPreviewProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun ExpandableOverview(
    text: String,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val newText by remember {
        derivedStateOf { if (expanded) text else text.split("\n\n").first() }
    }

    Column(modifier = modifier) {
        Overview(
            overview = newText,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.animateContentSize()
        )
        ShowMoreButton(
            expanded = expanded,
            onClick = { expanded = !expanded },
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun ExpandableOverviewPreview(
    @PreviewParameter(PersonPreviewProvider::class)
    personDetails: PersonDetails
) {
    MovieAppTheme {
        Surface {
            ExpandableOverview(
                text = personDetails.biography
            )
        }
    }
}
