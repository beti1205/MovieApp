package com.example.movieplayer.repository

import androidx.lifecycle.*
import com.example.movieplayer.database.MovieDatabase
import com.example.movieplayer.database.asDomainModel
import com.example.movieplayer.domain.Movie
import com.example.movieplayer.domain.Order
import com.example.movieplayer.network.*
import com.example.movieplayer.ui.search.movies.SearchMoviesFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val key = "c33ec9fdf85b0eb9fb900af22206b062"

class MovieRepository(private val database: MovieDatabase) {

    val movies: LiveData<List<Movie>> = database.movieDao
        .getMovies().map { it.asDomainModel() }

    suspend fun refreshMovies(order: Order) {
        withContext(Dispatchers.IO) {
            val movies = when (order) {
                Order.POPULAR -> MovieApi.retrofitService.getPopularMovies(key).movies
                Order.UPCOMING -> MovieApi.retrofitService.getUpcomingMovies(key).movies
                Order.TOP_RATED -> MovieApi.retrofitService.getTopRatedMovies(key).movies
                Order.NOW_PLAYING -> MovieApi.retrofitService.getNowPlayingMovies(key).movies
            }

            database.movieDao.deleteAll()
            database.movieDao.insertAll(movies.asDatabaseModel())
        }
    }

    suspend fun searchMovies(keyword: String): List<com.example.movieplayer.network.Movie> {
        return withContext(Dispatchers.IO) {
            MovieApi.retrofitService.getSearchedMovies(key, keyword).movies
        }
    }
}