package com.example.adnanchaudharydemo.presentation.portfolio

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.adnanchaudharydemo.presentation.portfolio.components.Portfolio
import com.example.adnanchaudharydemo.presentation.portfolio.uievents.PortfolioUiEvents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioScreen(
    portfolioScreenViewModel: PortfolioScreenViewModel = hiltViewModel<PortfolioScreenViewModel>()
) {
    val uiState by portfolioScreenViewModel.uiState.collectAsState()

    Portfolio(
        uiState = uiState,
        uiEvents = PortfolioUiEvents(
            togglePnL = portfolioScreenViewModel::togglePnL,
            fetchHoldings = portfolioScreenViewModel::fetchHoldings
        ),
    )
}
