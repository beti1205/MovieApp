/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget.search

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchTopAppBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onBackPressed: () -> Unit,
    enabled: Boolean = true
) {
    val focusRequester = remember { FocusRequester() }

    TopAppBar(
        title = {
            val interactionSource = remember { MutableInteractionSource() }
            val localStyle = LocalTextStyle.current
            val mergedStyle = localStyle.merge(TextStyle(color = LocalContentColor.current))

            SearchAppBarTextField(
                query = query,
                onQueryChange = onQueryChange,
                interactionSource = interactionSource,
                mergedStyle = mergedStyle,
                focusRequester = focusRequester,
                enabled = enabled
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        },
        elevation = 0.dp
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun SearchAppBarTextField(
    query: String,
    onQueryChange: (String) -> Unit,
    interactionSource: MutableInteractionSource,
    mergedStyle: TextStyle,
    focusRequester: FocusRequester,
    enabled: Boolean
) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        interactionSource = interactionSource,
        textStyle = mergedStyle,
        cursorBrush = SolidColor(MaterialTheme.colors.onPrimary),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        decorationBox = { innerTextField ->
            TextFieldDefaults.TextFieldDecorationBox(
                value = query,
                innerTextField = innerTextField,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search)
                    )
                },
                enabled = enabled,
                singleLine = true,
                interactionSource = interactionSource,
                visualTransformation = VisualTransformation.None,
                contentPadding = PaddingValues(
                    start = 0.dp,
                    top = 16.dp,
                    bottom = 16.dp,
                    end = 16.dp
                )
            )
        }
    )
}
