package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.fetchtvepisodes.data.EpisodeService
import com.beti1205.movieapp.feature.fetchtvepisodes.domain.FetchEpisodesUseCase
import com.beti1205.movieapp.feature.fetchtvepisodes.domain.FetchEpisodesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface EpisodeModule {

    companion object {
        @Provides
        fun provideEpisodeApiService(retrofit: Retrofit): EpisodeService = retrofit.create()
    }

    @Binds
    fun bindEpisodeUseCase(
        fetchEpisodesUseCaseImpl: FetchEpisodesUseCaseImpl
    ): FetchEpisodesUseCase
}
