package com.example.adnanchaudharydemo.data.datasource.remote

import com.example.adnanchaudharydemo.data.models.HoldingResponse
import retrofit2.Response

interface IPortfolioRemoteDataSource {
    suspend fun getHoldings(): Response<HoldingResponse>
}