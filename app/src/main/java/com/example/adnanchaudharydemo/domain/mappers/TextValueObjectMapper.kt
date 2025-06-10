package com.example.adnanchaudharydemo.domain.mappers

import com.example.adnanchaudharydemo.constants.Constants.Companion.RUPEE_SYMBOL
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.ITextValueObject
import com.example.adnanchaudharydemo.presentation.portfolio.uimodels.TextValueObject
import com.example.adnanchaudharydemo.utils.toIndianCurrencyFormat
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class TextValueObjectMapper @Inject constructor(
) {
    fun map(value: Double): ITextValueObject {
        return TextValueObject(
            text = RUPEE_SYMBOL + value.toIndianCurrencyFormat(),
            value = BigDecimal(value).setScale(2, RoundingMode.HALF_UP).toDouble()
        )
    }
}