package com.example.adnanchaudharydemo.presentation.portfolio.uimodels

data class MockTextValueObject(
    override val text: String = "0.0",
    override val value: Double = 0.0
): ITextValueObject