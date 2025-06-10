package com.example.adnanchaudharydemo.presentation.portfolio.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adnanchaudharydemo.constants.Constants.Companion.RUPEE_SYMBOL
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.TextValueObject
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.UiHolding
import com.example.adnanchaudharydemo.ui.theme.LossRed
import com.example.adnanchaudharydemo.ui.theme.ProfitGreen
import com.example.adnanchaudharydemo.ui.theme.TextPrimary
import com.example.adnanchaudharydemo.ui.theme.TextSecondary

@Preview(showBackground = true)
@Composable
private fun HoldingItemPreview() {
    HoldingItem(
        holding = UiHolding(
            symbol = "MAHABANK",
            quantity = 10,
            ltp = TextValueObject(
                text = RUPEE_SYMBOL + "38.05",
                value = 38.05
            ),
            avgPrice = TextValueObject(
                text = RUPEE_SYMBOL + "38.05",
                value = 38.05
            ),
            close = TextValueObject(
                text = RUPEE_SYMBOL + "38.05",
                value = 38.05
            ),
            pnl = TextValueObject(
                text = RUPEE_SYMBOL + "38.05",
                value = 38.05
            ),
        )
    )
}

@Composable
fun HoldingItem(holding: UiHolding) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = holding.symbol,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "LTP: ",
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = TextSecondary
                )
                Text(
                    text = holding.ltp.text,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextPrimary
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "NET QTY: ",
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = TextSecondary
                )
                Text(
                    text = holding.quantity.toString(),
                    fontSize = 14.sp,
                    color = TextPrimary,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "P&L: ",
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    color = TextSecondary
                )
                Text(
                    text = holding.pnl.text,
                    fontSize = 14.sp,
                    color = if(holding.pnl.value > 0) ProfitGreen else LossRed,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}