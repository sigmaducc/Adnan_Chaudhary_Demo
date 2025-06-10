package com.example.adnanchaudharydemo.presentation.portfolio.uievents

data class MockPortfolioUiEvents(
    override val togglePnL: () -> Unit = {},
    override val fetchHoldings: () -> Unit = {}
): IPortfolioUiEvents