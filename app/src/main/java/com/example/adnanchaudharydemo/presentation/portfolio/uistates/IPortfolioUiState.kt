package com.example.adnanchaudharydemo.presentation.portfolio.uistates

import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.UiUserHolding
import com.example.adnanchaudharydemo.utils.Resource

interface IPortfolioUiState {
    val userHolding: Resource<UiUserHolding>
    val isPnLCollapsed: Boolean
}