package io.github.kitswas.virtualgamepadmobile.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.kitswas.VGP_Data_Exchange.GamepadReading

@Composable
fun DrawGamepad(
    screenWidth: Int,
    screenHeight: Int,
    gamepadState: GamepadReading
) {
    // TEMP PLACEHOLDER
    // This makes the app compile and lets us build the overlay logic first
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    )
}
