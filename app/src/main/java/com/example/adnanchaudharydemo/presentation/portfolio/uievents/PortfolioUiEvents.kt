package com.example.adnanchaudharydemo.presentation.portfolio.uievents

data class PortfolioUiEvents(
    override val togglePnL: () -> Unit,
    override val fetchHoldings: () -> Unit,
): IPortfolioUiEvents