package com.example.adnanchaudharydemo.utils

import android.annotation.SuppressLint
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import java.util.Locale

fun formatIndianCurrency(value: Long): String {
    val isNegative = value < 0
    val numStr = if (isNegative) value.toString().substring(1) else value.toString()

    if (numStr.length <= 3) return if (isNegative) "-$numStr" else numStr

    val result = StringBuilder()
    var digitCount = 0

    // Process digits from right to left
    for (i in numStr.length - 1 downTo 0) {
        if (digitCount == 3 || (digitCount > 3 && (digitCount - 3) % 2 == 0)) {
            result.append(',')
        }
        result.append(numStr[i])
        digitCount++
    }

    val formatted = result.reverse().toString()
    return if (isNegative) "-$formatted" else formatted
}

// Overloaded function for Int
fun formatIndianCurrency(value: Int): String = formatIndianCurrency(value.toLong())

// Overloaded function for Double (for decimal values with 2 decimal precision)
@SuppressLint("DefaultLocale")
fun formatIndianCurrency(value: Double): String {
    val isNegative = value < 0
    val absoluteValue = if (isNegative) -value else value
    val roundedValue = String.format("%.2f", absoluteValue)
    val parts = roundedValue.split('.')
    val integerPart = parts[0].toLong()
    val decimalPart = parts[1]

    val formattedInteger = formatIndianCurrency(integerPart)
    val result = "$formattedInteger.$decimalPart"
    return if (isNegative) "-$result" else result
}

// Extension function for any Number
fun Number.toIndianCurrencyFormat(): String {
    return when (this) {
        is Int -> formatIndianCurrency(this)
        is Long -> formatIndianCurrency(this)
        is Double -> formatIndianCurrency(this)
        is Float -> formatIndianCurrency(this.toDouble())
        else -> formatIndianCurrency(this.toLong())
    }
}

fun String.capitalizeString(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            val width = size.width
            val height = size.height - strokeWidthPx/2 + 12

            drawLine(
                color = color,
                start = Offset(x = 0f, y = height),
                end = Offset(x = width , y = height),
                strokeWidth = strokeWidthPx
            )
        }
    }
)