package com.example.adnanchaudharydemo.presentation.portfolio.uistates

import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.UiUserHolding
import com.example.adnanchaudharydemo.utils.Resource

data class MockPortfolioUiState(
    override val userHolding: Resource<UiUserHolding> = Resource.Loading,
    override val isPnLCollapsed: Boolean = true
): IPortfolioUiState