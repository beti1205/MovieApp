package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.fetchaccountdetails.data.AccountDetailsService
import com.beti1205.movieapp.feature.fetchaccountdetails.domain.FetchAccountDetailsUseCase
import com.beti1205.movieapp.feature.fetchaccountdetails.domain.FetchAccountDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface AccountModule {

    companion object {
        @Provides
        fun provideAccountDetailsService(retrofit: Retrofit): AccountDetailsService =
            retrofit.create()
    }

    @Binds
    fun bindAccountDetailsUseCase(
        fetchAccountDetailsUseCaseImpl: FetchAccountDetailsUseCaseImpl
    ): FetchAccountDetailsUseCase
}
