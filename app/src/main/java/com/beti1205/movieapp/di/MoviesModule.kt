/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.movies.data.FavoriteMoviesService
import com.beti1205.movieapp.feature.movies.data.MovieApiService
import com.beti1205.movieapp.feature.movies.data.MovieWatchlistService
import com.beti1205.movieapp.feature.movies.domain.FetchFavoriteMoviesUseCase
import com.beti1205.movieapp.feature.movies.domain.FetchFavoriteMoviesUseCaseImpl
import com.beti1205.movieapp.feature.movies.domain.FetchMovieWatchlistUseCase
import com.beti1205.movieapp.feature.movies.domain.FetchMovieWatchlistUseCaseImpl
import com.beti1205.movieapp.feature.movies.domain.FetchMoviesUseCase
import com.beti1205.movieapp.feature.movies.domain.FetchMoviesUseCaseImpl
import com.beti1205.movieapp.feature.movies.domain.SearchMoviesUseCase
import com.beti1205.movieapp.feature.movies.domain.SearchMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface MoviesModule {

    companion object {
        @Provides
        fun provideMovieService(retrofit: Retrofit): MovieApiService = retrofit.create()

        @Provides
        fun provideFavoriteMoviesService(
            retrofit: Retrofit
        ): FavoriteMoviesService = retrofit.create()

        @Provides
        fun providerMovieWatchlistService(
            retrofit: Retrofit
        ): MovieWatchlistService = retrofit.create()
    }

    @Binds
    fun bindMoviesUseCase(
        moviesUseCaseImpl: FetchMoviesUseCaseImpl
    ): FetchMoviesUseCase

    @Binds
    fun bindSearchMoviesUseCase(
        searchMoviesUseCaseImpl: SearchMoviesUseCaseImpl
    ): SearchMoviesUseCase

    @Binds
    fun bindFetchFavoriteMoviesUseCase(
        fetchFavoriteMoviesUseCaseImpl: FetchFavoriteMoviesUseCaseImpl
    ): FetchFavoriteMoviesUseCase

    @Binds
    fun bindFetchMovieWatchlistUseCase(
        fetchMovieWatchlistUseCaseImpl: FetchMovieWatchlistUseCaseImpl
    ): FetchMovieWatchlistUseCase
}
