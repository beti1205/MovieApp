/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.utils

import java.math.RoundingMode
import java.text.DecimalFormat

val decimalFormat = DecimalFormat("0.0")
    .apply { roundingMode = RoundingMode.HALF_EVEN }

val Double.formattedRating: String
    get() = decimalFormat.format(this)
