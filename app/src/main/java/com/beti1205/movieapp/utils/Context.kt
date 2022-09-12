package com.beti1205.movieapp.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment

@ColorInt
fun Context.themeColor(
    @AttrRes themeAttrId: Int
): Int {
    val typedArray = obtainStyledAttributes(intArrayOf(themeAttrId))
    val themeColor = typedArray.getColor(0, Color.WHITE)
    typedArray.recycle()

    return themeColor
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Fragment.showKeyboard(view: View) {
    requireContext().showKeyboard(view)
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService<InputMethodManager>()
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService<InputMethodManager>()
    inputMethodManager?.showSoftInput(view, 0)
}
