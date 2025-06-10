package com.example.adnanchaudharydemo.data.datasource.remote

import com.example.adnanchaudharydemo.data.api.IPortfolioApi
import com.example.adnanchaudharydemo.data.models.HoldingResponse
import retrofit2.Response
import javax.inject.Inject

class PortfolioRemoteDataSource @Inject constructor(
    private val api: IPortfolioApi
) : IPortfolioRemoteDataSource {

    override suspend fun getHoldings(): Response<HoldingResponse> {
        return api.getHoldings()
    }
} 