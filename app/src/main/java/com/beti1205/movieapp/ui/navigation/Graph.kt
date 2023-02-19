/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

sealed class Graph(val route: String) {
    object MoviesGraph : Graph("movies_graph")
    object TVGraph : Graph("tv_graph")
}
