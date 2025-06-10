package com.example.adnanchaudharydemo.domain.repositories

import com.example.adnanchaudharydemo.data.models.HoldingResponse

interface IPortfolioRepository {
    suspend fun getHoldings(): HoldingResponse
}