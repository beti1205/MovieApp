package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.reviews.data.MovieReviewsService
import com.beti1205.movieapp.feature.reviews.data.TVSeriesReviewsService
import com.beti1205.movieapp.feature.reviews.domain.FetchMovieReviewsUseCase
import com.beti1205.movieapp.feature.reviews.domain.FetchMovieReviewsUseCaseImpl
import com.beti1205.movieapp.feature.reviews.domain.FetchTVSeriesReviewsUseCase
import com.beti1205.movieapp.feature.reviews.domain.FetchTVSeriesReviewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface ReviewsModule {

    companion object {
        @Provides
        fun provideMovieReviewsService(retrofit: Retrofit): MovieReviewsService = retrofit.create()

        @Provides
        fun provideTVSeriesReviewsService(
            retrofit: Retrofit
        ): TVSeriesReviewsService = retrofit.create()
    }

    @Binds
    fun bindFetchMovieReviewsUseCase(
        fetchMovieReviewsUseCaseImpl: FetchMovieReviewsUseCaseImpl
    ): FetchMovieReviewsUseCase

    @Binds
    fun bindFetchTVSeriesReviewsUseCase(
        fetchTVSeriesReviewsUseCaseImpl: FetchTVSeriesReviewsUseCaseImpl
    ): FetchTVSeriesReviewsUseCase
}
