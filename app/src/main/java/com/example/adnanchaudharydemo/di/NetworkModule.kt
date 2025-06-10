package com.example.adnanchaudharydemo.di

import com.example.adnanchaudharydemo.constants.Constants.Companion.BASE_URL
import com.example.adnanchaudharydemo.data.api.IPortfolioApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePortfolioApi(retrofit: Retrofit): IPortfolioApi =
        retrofit.create(IPortfolioApi::class.java)
}