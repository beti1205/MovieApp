/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account.widget

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.beti1205.movieapp.R
import com.beti1205.movieapp.common.data.ListOrder
import com.beti1205.movieapp.ui.account.AccountScreenOrderType
import com.beti1205.movieapp.ui.theme.SonicSilver

@Composable
fun ListOrderButton(
    order: ListOrder,
    orderType: AccountScreenOrderType,
    onOrderChanged: (AccountScreenOrderType, ListOrder) -> Unit
) {
    IconButton(
        onClick = {
            ListOrder.availableValues()
                .first { it != order }
                .let { onOrderChanged(orderType, it) }
        }
    ) {
        if (order == ListOrder.LATEST) {
            Icon(
                painter = painterResource(id = R.drawable.sort_descending),
                contentDescription = null,
                tint = SonicSilver
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.sort_ascending),
                contentDescription = null,
                tint = SonicSilver
            )
        }
    }
}
