package com.example.adnanchaudharydemo.data.repositories

import com.example.adnanchaudharydemo.data.datasource.remote.IPortfolioRemoteDataSource
import com.example.adnanchaudharydemo.data.models.HoldingResponse
import com.example.adnanchaudharydemo.domain.repositories.IPortfolioRepository
import javax.inject.Inject

class PortfolioRepository @Inject constructor(
    private val remoteDataSource: IPortfolioRemoteDataSource
): IPortfolioRepository {

    override suspend fun getHoldings(): HoldingResponse {
        val response = remoteDataSource.getHoldings()

        if(!response.isSuccessful) {
            throw Exception("getHoldings API Failed")
        }

        return response.body() as HoldingResponse
    }
} 