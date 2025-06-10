package com.example.adnanchaudharydemo.presentation.portfolio.uistates

import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.UiUserHolding
import com.example.adnanchaudharydemo.utils.Resource

data class PortfolioUiState(
    override val userHolding: Resource<UiUserHolding>,
    override val isPnLCollapsed: Boolean
): IPortfolioUiState