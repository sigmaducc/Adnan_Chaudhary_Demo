package com.example.adnanchaudharydemo.domain.usecases

import com.example.adnanchaudharydemo.domain.mappers.TextValueObjectMapper
import com.example.adnanchaudharydemo.domain.repositories.IPortfolioRepository
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.UiHolding
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.UiUserHolding
import javax.inject.Inject

class GetHoldingsUseCase @Inject constructor(
    private val repository: IPortfolioRepository,
    private val textValueObjectMapper: TextValueObjectMapper
) {
    suspend fun execute(): UiUserHolding {
        val allHoldings = repository.getHoldings()

        if(allHoldings.data?.userHolding == null) return UiUserHolding()

        val uiHoldings = allHoldings.data.userHolding.map {
            if(it.symbol !== null
                && it.quantity != null
                && it.ltp != null
                && it.close != null
                && it.avgPrice != null
            ) {
                val pnl = (it.ltp - it.avgPrice) * it.quantity
                UiHolding(
                    symbol = it.symbol,
                    quantity = it.quantity,
                    ltp = textValueObjectMapper.map(it.ltp),
                    avgPrice = textValueObjectMapper.map(it.avgPrice),
                    close = textValueObjectMapper.map(it.close),
                    pnl = textValueObjectMapper.map(pnl),
                )
            } else {
                throw Exception("values empty")
            }
        }

        val currentValue = uiHoldings.sumOf { it.ltp.value * it.quantity }
        val totalInvestment = uiHoldings.sumOf { it.avgPrice.value * it.quantity }
        val totalPnL = currentValue - totalInvestment
        val todaysPnL = uiHoldings.sumOf { (it.close.value - it.ltp.value) * it.quantity }

        return UiUserHolding(
            holdings = uiHoldings,
            currentValue = textValueObjectMapper.map(currentValue),
            totalInvestment = textValueObjectMapper.map(totalInvestment),
            totalPnL = textValueObjectMapper.map(totalPnL),
            todaysPnL = textValueObjectMapper.map(todaysPnL)
        )
    }
} 