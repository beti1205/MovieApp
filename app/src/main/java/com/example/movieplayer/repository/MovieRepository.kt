package com.example.movieplayer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.example.movieplayer.database.DatabaseMovie
import com.example.movieplayer.database.MovieDatabase
import com.example.movieplayer.database.asDomainModel
import com.example.movieplayer.domain.MovieModel
import com.example.movieplayer.network.MovieApi
import com.example.movieplayer.network.MovieResponse
import com.example.movieplayer.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val key = "c33ec9fdf85b0eb9fb900af22206b062"

class MovieRepository(private val database: MovieDatabase) {

//    val movies2: LiveData<List<MovieModel>> =
//        Transformations.map(database.movieDao.getMovies()) { it.asDomainModel() }

    val movies: LiveData<List<MovieModel>> = database.movieDao.getMovies().map { it.asDomainModel() }


    suspend fun refreshMovies() {
        withContext(Dispatchers.IO) {
            val movies = MovieApi.retrofitService.getPopularMovies(key).movies
            database.movieDao.insertAll(movies.asDatabaseModel())
        }
    }
}