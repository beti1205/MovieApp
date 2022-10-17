package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.fetchmovies.data.MovieApiService
import com.beti1205.movieapp.feature.fetchmovies.domain.FetchMoviesUseCase
import com.beti1205.movieapp.feature.fetchmovies.domain.FetchMoviesUseCaseImpl
import com.beti1205.movieapp.feature.fetchmovies.domain.SearchMoviesUseCase
import com.beti1205.movieapp.feature.fetchmovies.domain.SearchMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface MovieModule {

    companion object {
        @Provides
        fun provideMovieService(retrofit: Retrofit): MovieApiService = retrofit.create()
    }

    @Binds
    fun bindMoviesUseCase(
        moviesUseCaseImpl: FetchMoviesUseCaseImpl
    ): FetchMoviesUseCase

    @Binds
    fun bindSearchMoviesUseCase(
        searchMoviesUseCaseImpl: SearchMoviesUseCaseImpl
    ): SearchMoviesUseCase
}
