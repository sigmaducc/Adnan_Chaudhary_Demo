package com.example.adnanchaudharydemo.presentation.portfolio.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adnanchaudharydemo.constants.Constants.Tabs.HOLDINGS
import com.example.adnanchaudharydemo.presentation.portfolio.uievents.IPortfolioUiEvents
import com.example.adnanchaudharydemo.presentation.portfolio.uievents.MockPortfolioUiEvents
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.UiUserHolding
import com.example.adnanchaudharydemo.presentation.portfolio.uistates.IPortfolioUiState
import com.example.adnanchaudharydemo.presentation.portfolio.uistates.MockPortfolioUiState
import com.example.adnanchaudharydemo.utils.Resource

@Composable
@Preview(showBackground = true)
private fun PortfolioPreview() {
    Portfolio(
        uiState = MockPortfolioUiState(),
        uiEvents = MockPortfolioUiEvents()
    )
}

@Composable
fun Portfolio(
    uiState: IPortfolioUiState,
    uiEvents: IPortfolioUiEvents
) {
    when (uiState.userHolding) {
        is Resource.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Fetching holdings..."
                    )
                }
            }
        }
        is Resource.Success -> {
            val userHolding = (uiState.userHolding as Resource.Success<UiUserHolding>).data

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    PortfolioTopBar()
                    PortfolioTabBar(selectedTab = HOLDINGS)
                    HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.surfaceVariant)
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(userHolding.holdings) { holding ->
                            HoldingItem(holding)
                            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.surfaceVariant)
                        }
                    }
                    Spacer(Modifier.height(55.dp))
                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    ProfitLossFooter(
                        userHolding = userHolding,
                        togglePnL = uiEvents.togglePnL,
                        isPnLCollapsed = uiState.isPnLCollapsed
                    )
                }
            }
        }
        is Resource.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Unable to fetch holdings",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            uiEvents.fetchHoldings()
                        }
                    ) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}