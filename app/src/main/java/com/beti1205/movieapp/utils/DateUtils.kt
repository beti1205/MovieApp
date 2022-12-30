package com.beti1205.movieapp.utils

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

fun String.toZoneDateTime(): ZonedDateTime = ZonedDateTime.parse(this)
    .withZoneSameInstant(ZoneId.systemDefault())

val ZonedDateTime.formattedDate: String
    get() = format(dateFormatter)
