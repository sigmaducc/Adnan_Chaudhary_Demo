package com.example.adnanchaudharydemo.presentation.portfolio.uimodels

data class UiUserHolding(
    val holdings: List<UiHolding> = emptyList(),
    val currentValue: ITextValueObject = MockTextValueObject(),
    val totalInvestment: ITextValueObject = MockTextValueObject(),
    val todaysPnL: ITextValueObject = MockTextValueObject(),
    val totalPnL: ITextValueObject = MockTextValueObject()
)