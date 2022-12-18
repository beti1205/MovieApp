package com.beti1205.movieapp.ui.persondetails

data class Section<T>(
    val items: List<T> = emptyList(),
    val expanded: Boolean = false,
    val expandable: Boolean = false
) {
    fun isEmpty(): Boolean = items.isEmpty()
    fun isNotEmpty(): Boolean = !isEmpty()
}
