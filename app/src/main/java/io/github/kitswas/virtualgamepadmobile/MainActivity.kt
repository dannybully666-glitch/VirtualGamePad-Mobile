package io.github.kitswas.virtualgamepadmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.kitswas.virtualgamepadmobile.data.SettingsRepository
import io.github.kitswas.virtualgamepadmobile.data.defaultBaseColor
import io.github.kitswas.virtualgamepadmobile.data.defaultColorScheme
import io.github.kitswas.virtualgamepadmobile.data.defaultHapticFeedbackEnabled
import io.github.kitswas.virtualgamepadmobile.ui.screens.AboutScreen
import io.github.kitswas.virtualgamepadmobile.ui.screens.GamePad
import io.github.kitswas.virtualgamepadmobile.ui.screens.MainMenu
import io.github.kitswas.virtualgamepadmobile.ui.screens.SettingsScreen
import io.github.kitswas.virtualgamepadmobile.ui.theme.VirtualGamePadMobileTheme
import io.github.kitswas.virtualgamepadmobile.ui.utils.HapticUtils
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settingsRepository = SettingsRepository(this)

        setContent {
            AppUI(settingsRepository = settingsRepository)
        }
    }

    @Composable
    private fun AppUI(
        settingsRepository: SettingsRepository
    ) {
        val hapticEnabled = settingsRepository.hapticFeedbackEnabled.collectAsState(
            initial = defaultHapticFeedbackEnabled
        )

        LaunchedEffect(hapticEnabled.value) {
            HapticUtils.isEnabled = hapticEnabled.value
        }

        VirtualGamePadMobileTheme(
            darkMode = settingsRepository.colorScheme.collectAsState(
                initial = defaultColorScheme
            ).value,
            baseColor = settingsRepository.baseColor.collectAsState(
                initial = defaultBaseColor
            ).value
        ) {
            NavTree(settingsRepository = settingsRepository)
        }
    }

    @Composable
    private fun NavTree(
        settingsRepository: SettingsRepository,
        navController: NavHostController = rememberNavController()
    ) {
        NavHost(navController = navController, startDestination = "main_menu") {

            composable("main_menu") {
                MainMenu(
                    onNavigateToConnectScreen = {
                        navController.navigate("gamepad")
                    },
                    onNavigateToSettingsScreen = {
                        navController.navigate("settings_screen")
                    },
                    onNavigateToAboutScreen = {
                        navController.navigate("about_screen")
                    },
                    onExit = { exitProcess(0) }
                )
            }

            composable("gamepad") {
                GamePad(
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable("settings_screen") {
                SettingsScreen(
                    onNavigateBack = { navController.popBackStack() },
                    settingsRepository = settingsRepository
                )
            }

            composable("about_screen") {
                AboutScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}
