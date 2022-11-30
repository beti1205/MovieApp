package com.beti1205.movieapp.ui.persondetails.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R

@Composable
fun SectionButton(
    onExpandedChanged: (Boolean) -> Unit,
    expanded: Boolean
) {
    Button(
        onClick = { onExpandedChanged(!expanded) },
        modifier = Modifier.padding(start = 16.dp)
    ) {
        if (!expanded) {
            Text(text = stringResource(R.string.section_button_show_more))
        } else {
            Text(text = stringResource(R.string.section_button_show_less))
        }
    }
}
