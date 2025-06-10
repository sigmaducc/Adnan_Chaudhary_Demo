package com.example.adnanchaudharydemo.data.api

import com.example.adnanchaudharydemo.data.models.HoldingResponse
import retrofit2.Response
import retrofit2.http.GET

interface IPortfolioApi {
    @GET("/")
    suspend fun getHoldings(): Response<HoldingResponse>
}