package com.example.movieplayer.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MovieApiService {
    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") key: String): MovieResponse

    @GET("top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") key: String): MovieResponse

    @GET("upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") key: String): MovieResponse

    @GET("now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") key: String): MovieResponse
}

object MovieApi {
    val retrofitService: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}