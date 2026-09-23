package com.joaopedro.wearmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import com.joaopedro.wearmanager.ui.WearManagerApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Color(0xFF38BDF8),
                    secondary = Color(0xFF22D3EE),
                    background = Color(0xFF07111F),
                    surface = Color(0xFF111827),
                    onPrimary = Color(0xFF001018),
                    onBackground = Color(0xFFE5E7EB),
                    onSurface = Color(0xFFE5E7EB)
                )
            ) {
                WearManagerApp()
            }
        }
    }
}