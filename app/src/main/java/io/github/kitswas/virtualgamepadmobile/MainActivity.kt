package io.github.kitswas.virtualgamepadmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.kitswas.virtualgamepadmobile.data.SettingsRepository
import io.github.kitswas.virtualgamepadmobile.data.defaultBaseColor
import io.github.kitswas.virtualgamepadmobile.data.defaultColorScheme
import io.github.kitswas.virtualgamepadmobile.ui.screens.AboutScreen
import io.github.kitswas.virtualgamepadmobile.ui.screens.MainMenu
import io.github.kitswas.virtualgamepadmobile.ui.screens.SettingsScreen
import io.github.kitswas.virtualgamepadmobile.ui.theme.VirtualGamePadMobileTheme

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
        VirtualGamePadMobileTheme(
            darkMode = settingsRepository.colorScheme.value ?: defaultColorScheme,
            baseColor = settingsRepository.baseColor.value ?: defaultBaseColor
        ) {
            NavTree(settingsRepository = settingsRepository)
        }
    }

    @Composable
    private fun NavTree(
        settingsRepository: SettingsRepository,
        navController: NavHostController = rememberNavController()
    ) {
        NavHost(
            navController = navController,
            startDestination = "main_menu"
        ) {
            composable("main_menu") {
                MainMenu(
                    onNavigateToConnectScreen = {},
                    onNavigateToSettingsScreen = {
                        navController.navigate("settings_screen")
                    },
                    onNavigateToAboutScreen = {
                        navController.navigate("about_screen")
                    },
                    onExit = {}
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
