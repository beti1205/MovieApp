package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.markfavorite.data.MarkFavoriteService
import com.beti1205.movieapp.feature.markfavorite.domain.MarkFavoriteUseCase
import com.beti1205.movieapp.feature.markfavorite.domain.MarkFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface FavoriteModule {

    companion object {
        @Provides
        fun provideMarkFavoriteService(retrofit: Retrofit): MarkFavoriteService = retrofit.create()
    }

    @Binds
    fun bindMarkFavoriteUseCase(
        markFavoriteUseCaseImpl: MarkFavoriteUseCaseImpl
    ): MarkFavoriteUseCase
}
