package io.github.kitswas.virtualgamepadmobile.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import io.github.kitswas.virtualgamepadmobile.ui.composables.DrawGamepad

private const val TAG = "OfflineGamePad"

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun GamePad(
    onNavigateBack: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    // Local gamepad state (offline)
    val gamepadState = remember {
        mutableStateOf(
            OfflineGamepadState()
        )
    }

    // Draw the gamepad UI
    DrawGamepad(
        screenWidth = screenWidth,
        screenHeight = screenHeight,
        gamepadState = gamepadState.value,
        onButtonEvent = { button, pressed ->
            Log.d(TAG, "Button: $button pressed=$pressed")
        },
        onStickMove = { stick, x, y ->
            Log.d(TAG, "Stick: $stick x=$x y=$y")
        }
    )
}
