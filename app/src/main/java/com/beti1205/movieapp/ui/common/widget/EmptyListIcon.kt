package com.beti1205.movieapp.ui.common.widget

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.theme.SonicSilver

@Composable
fun EmptyListIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_baseline_list_24),
        contentDescription = null,
        modifier = Modifier
            .height(140.dp)
            .width(140.dp),
        tint = SonicSilver
    )
}
