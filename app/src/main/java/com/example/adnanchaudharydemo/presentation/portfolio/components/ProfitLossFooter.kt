package com.example.adnanchaudharydemo.presentation.portfolio.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adnanchaudharydemo.R
import com.example.adnanchaudharydemo.constants.Constants.Companion.RUPEE_SYMBOL
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.TextValueObject
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.UiUserHolding
import com.example.adnanchaudharydemo.ui.theme.LossRed
import com.example.adnanchaudharydemo.ui.theme.ProfitGreen
import com.example.adnanchaudharydemo.ui.theme.TextPrimary

@Composable
@Preview(showBackground = true)
private fun ProfitLossFooterPreview() {
    ProfitLossFooter(
        isPnLCollapsed = false,
        userHolding = UiUserHolding(
            currentValue = TextValueObject(
                text = RUPEE_SYMBOL + "38.05",
                value = 38.05
            ),
            totalInvestment = TextValueObject(
                text = RUPEE_SYMBOL + "38.05",
                value = 38.05
            ),
            todaysPnL = TextValueObject(
                text = RUPEE_SYMBOL + "-38.05",
                value = -38.05
            ),
            totalPnL = TextValueObject(
                text = RUPEE_SYMBOL + "38.05",
                value = 38.05
            ),
        ),
        togglePnL = {}
    )
}

@Composable
fun ProfitLossFooter(
    isPnLCollapsed: Boolean,
    userHolding: UiUserHolding,
    togglePnL: () -> Unit
) {
    println("togglePnl $togglePnL")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .padding(vertical = 12.dp, horizontal = 16.dp),
    ) {
        AnimatedVisibility(
            visible = !isPnLCollapsed,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Current value*",
                        color = TextPrimary,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )

                    Row(
                        modifier = Modifier
                            .wrapContentSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = userHolding.currentValue.text,
                            color = TextPrimary,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total investment*",
                        color = TextPrimary,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )

                    Row(
                        modifier = Modifier
                            .wrapContentSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = userHolding.totalInvestment.text,
                            color = TextPrimary,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Today's Profit & Loss*",
                        color = TextPrimary,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )

                    Row(
                        modifier = Modifier
                            .wrapContentSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = userHolding.todaysPnL.text,
                            color = if(userHolding.todaysPnL.value > 0) ProfitGreen else LossRed,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(thickness = 1.dp)
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .clickable {
                    togglePnL()
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Profit & Loss*",
                    color = TextPrimary,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Image(
                    modifier = Modifier
                        .size(15.dp)
                        .rotate(if (isPnLCollapsed) 0f else 180f),
                    painter = painterResource(id = R.drawable.chevron_up),
                    contentDescription = "Chevron"
                )
            }

            Row(
                modifier = Modifier
                    .wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = userHolding.totalPnL.text,
                    color = if(userHolding.totalPnL.value > 0) ProfitGreen else LossRed,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}