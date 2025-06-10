package com.example.adnanchaudharydemo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun AdnanChaudharyDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = lightColorScheme()
    val view = LocalView.current
    val activity = LocalContext.current as? Activity
    SideEffect {
        activity?.window?.let { window ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) { // API 34
                // New API for Android 14+
                // You can use window.setStatusBarAppearanceLight() and related APIs here
                // But as of now, the Compose/AndroidX ecosystem still uses the compat approach for cross-version support
                // So, you may need to use WindowInsetsControllerCompat for now for icon color
                WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = true
            } else {
                // For older versions, set color and icon style as before
                @Suppress("DEPRECATION")
                window.statusBarColor = Color.White.toArgb()
                WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = true
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}