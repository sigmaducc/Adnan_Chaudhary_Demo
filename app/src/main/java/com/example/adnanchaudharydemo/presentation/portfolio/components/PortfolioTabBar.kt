package com.example.adnanchaudharydemo.presentation.portfolio.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adnanchaudharydemo.constants.Constants.Tabs.HOLDINGS
import com.example.adnanchaudharydemo.constants.Constants.Tabs.POSITIONS
import com.example.adnanchaudharydemo.ui.theme.TextPrimary
import com.example.adnanchaudharydemo.ui.theme.TextSecondary
import com.example.adnanchaudharydemo.utils.bottomBorder
import com.example.adnanchaudharydemo.utils.capitalizeString

@Preview(showBackground = true)
@Composable
private fun PortfolioTabBarPreview() {
    PortfolioTabBar(selectedTab = HOLDINGS)
}

@Composable
fun PortfolioTabBar(selectedTab: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(if (selectedTab == POSITIONS) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier,
                text = POSITIONS.capitalizeString(),
                color = if (selectedTab == POSITIONS) TextPrimary else TextSecondary,
                fontWeight = if (selectedTab == POSITIONS) FontWeight.Bold else FontWeight.Normal,
                fontSize = 16.sp
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(if (selectedTab == HOLDINGS) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .bottomBorder(1.dp, MaterialTheme.colorScheme.outline),
                text = HOLDINGS.capitalizeString(),
                color = if (selectedTab == HOLDINGS) TextPrimary else TextSecondary,
                fontWeight = if (selectedTab == HOLDINGS) FontWeight.Bold else FontWeight.Normal,
                fontSize = 16.sp
            )
        }
    }
}