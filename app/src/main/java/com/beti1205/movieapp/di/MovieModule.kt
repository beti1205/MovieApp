package com.beti1205.movieapp.di

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.feature.fetchmovies.data.MovieApiService
import com.beti1205.movieapp.feature.fetchmovies.domain.FetchMoviesUseCase
import com.beti1205.movieapp.feature.fetchmovies.domain.FetchMoviesUseCaseImpl
import com.beti1205.movieapp.feature.fetchmovies.domain.SearchMoviesUseCase
import com.beti1205.movieapp.feature.fetchmovies.domain.SearchMoviesUseCaseImpl
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieModule {

    companion object {
        @Provides
        fun provideMovieService(
            client: OkHttpClient,
            moshi: Moshi,
            appConfig: AppConfig
        ): MovieApiService = Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(appConfig.baseUrl)
            .build()
            .create()
    }

    @Binds
    abstract fun bindMoviesUseCase(
        moviesUseCaseImpl: FetchMoviesUseCaseImpl
    ): FetchMoviesUseCase

    @Binds
    abstract fun bindSearchMoviesUseCase(
        searchMoviesUseCaseImpl: SearchMoviesUseCaseImpl
    ): SearchMoviesUseCase
}
