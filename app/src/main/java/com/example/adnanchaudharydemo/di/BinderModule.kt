package com.example.adnanchaudharydemo.di

import com.example.adnanchaudharydemo.data.datasource.remote.PortfolioRemoteDataSource
import com.example.adnanchaudharydemo.data.datasource.remote.IPortfolioRemoteDataSource
import com.example.adnanchaudharydemo.data.repositories.PortfolioRepository
import com.example.adnanchaudharydemo.domain.repositories.IPortfolioRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {
    @Binds
    @Singleton
    abstract fun bindPortfolioRepository(
        repo: PortfolioRepository
    ): IPortfolioRepository

    @Binds
    @Singleton
    abstract fun bindPortfolioRemoteDataSource(
        dataSource: PortfolioRemoteDataSource
    ): IPortfolioRemoteDataSource
}

