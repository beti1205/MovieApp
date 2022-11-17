package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetailsService
import com.beti1205.movieapp.feature.fetchmoviedetails.domain.FetchMovieDetailsUseCase
import com.beti1205.movieapp.feature.fetchmoviedetails.domain.FetchMovieDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface MovieDetailsModule {

    companion object {
        @Provides
        fun provideMovieDetailsService(
            retrofit: Retrofit
        ): MovieDetailsService =
            retrofit.create()
    }

    @Binds
    fun bindFetchMovieDetailsUseCase(
        fetchMovieDetailsUseCaseImpl: FetchMovieDetailsUseCaseImpl
    ): FetchMovieDetailsUseCase
}
