package com.example.movieplayer.repository

import androidx.lifecycle.*
import com.example.movieplayer.database.DatabaseMovie
import com.example.movieplayer.database.MovieDatabase
import com.example.movieplayer.database.asDomainModel
import com.example.movieplayer.domain.MovieModel
import com.example.movieplayer.domain.Order
import com.example.movieplayer.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val key = "c33ec9fdf85b0eb9fb900af22206b062"

class MovieRepository(private val database: MovieDatabase) {

    val movies: LiveData<List<MovieModel>> = database.movieDao
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
}