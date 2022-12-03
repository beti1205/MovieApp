package com.beti1205.movieapp.utils

import java.math.RoundingMode
import java.text.DecimalFormat

val decimalFormat = DecimalFormat("#.#")
    .apply { roundingMode = RoundingMode.FLOOR }

val Double.formattedRating: String
    get() = decimalFormat.format(this)
