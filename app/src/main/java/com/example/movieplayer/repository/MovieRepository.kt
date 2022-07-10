package com.example.movieplayer.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.movieplayer.database.MoviesDao
import com.example.movieplayer.database.asDomainModel
import com.example.movieplayer.domain.Movie
import com.example.movieplayer.domain.MovieOrder
import com.example.movieplayer.network.MovieApiService
import com.example.movieplayer.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val key = "c33ec9fdf85b0eb9fb900af22206b062"

interface MovieRepository {
    val movies: LiveData<List<Movie>>
    suspend fun refreshMovies(order: MovieOrder)
    suspend fun searchMovies(keyword: String): List<com.example.movieplayer.network.Movie>

}

class MovieRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao,
    private val movieApiService: MovieApiService
) : MovieRepository {

    override val movies: LiveData<List<Movie>> = moviesDao
        .getMovies().map { it.asDomainModel() }

    override suspend fun refreshMovies(order: MovieOrder) {
        withContext(Dispatchers.IO) {
            val movies = when (order) {
                MovieOrder.POPULAR -> movieApiService.getPopularMovies(key).movies
                MovieOrder.UPCOMING -> movieApiService.getUpcomingMovies(key).movies
                MovieOrder.TOP_RATED -> movieApiService.getTopRatedMovies(key).movies
                MovieOrder.NOW_PLAYING -> movieApiService.getNowPlayingMovies(key).movies
            }

            moviesDao.deleteAll()
            moviesDao.insertAll(movies.asDatabaseModel())
        }
    }

    override suspend fun searchMovies(keyword: String): List<com.example.movieplayer.network.Movie> {
        return withContext(Dispatchers.IO) {
            movieApiService.getSearchedMovies(key, keyword).movies
        }
    }
}