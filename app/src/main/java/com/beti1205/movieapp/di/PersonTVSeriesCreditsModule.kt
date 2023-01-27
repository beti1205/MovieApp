package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCreditsService
import com.beti1205.movieapp.feature.persontvseriescredits.domain.FetchPersonTVSeriesCreditsUseCase
import com.beti1205.movieapp.feature.persontvseriescredits.domain.FetchPersonTVSeriesCreditsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface PersonTVSeriesCreditsModule {

    companion object {
        @Provides
        fun providePersonTVSeriesCreditsService(
            retrofit: Retrofit
        ): PersonTVSeriesCreditsService = retrofit.create()
    }

    @Binds
    fun bindFetchPersonTVSeriesCreditsUseCase(
        fetchPersonTVSeriesCreditsUseCaseImpl: FetchPersonTVSeriesCreditsUseCaseImpl
    ): FetchPersonTVSeriesCreditsUseCase
}
