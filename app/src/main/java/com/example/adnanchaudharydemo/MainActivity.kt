package com.example.adnanchaudharydemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.adnanchaudharydemo.presentation.MainEntry
import com.example.adnanchaudharydemo.ui.theme.AdnanChaudharyDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdnanChaudharyDemoTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    MainEntry(innerPadding)
                }
            }
        }
    }
}