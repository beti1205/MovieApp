package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.persondetails.data.PersonDetailsService
import com.beti1205.movieapp.feature.persondetails.domain.FetchPersonDetailsUseCase
import com.beti1205.movieapp.feature.persondetails.domain.FetchPersonDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface PersonDetailsModule {

    companion object {
        @Provides
        fun providePersonDetailsService(
            retrofit: Retrofit
        ): PersonDetailsService = retrofit.create()
    }

    @Binds
    fun bindPersonDetailsUseCase(
        fetchPersonDetailsUseCaseImpl: FetchPersonDetailsUseCaseImpl
    ): FetchPersonDetailsUseCase
}
