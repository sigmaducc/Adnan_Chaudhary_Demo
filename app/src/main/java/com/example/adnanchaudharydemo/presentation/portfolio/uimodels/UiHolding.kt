package com.example.adnanchaudharydemo.presentation.portfolio.uimodels

data class UiHolding(
    val symbol: String,
    val quantity: Int,
    val ltp: ITextValueObject,
    val avgPrice: ITextValueObject,
    val close: ITextValueObject,
    val pnl: ITextValueObject,
)