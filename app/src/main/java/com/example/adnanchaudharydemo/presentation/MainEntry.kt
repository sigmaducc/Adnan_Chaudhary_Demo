package com.example.adnanchaudharydemo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.adnanchaudharydemo.presentation.portfolio.PortfolioScreen

@Composable
fun MainEntry(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "/"
        ) {
            composable("/") {
                PortfolioScreen()
            }
        }
    }
}