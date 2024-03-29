package com.houlis.haris.picfind.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.testing.TestNavHostController
import com.houlis.haris.picfind.navigation.TopLevelDestination
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.single

internal class PicFindAppStateTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val sutWithNavController
        get() = @Composable { navController: TestNavHostController ->
            rememberPicFindAppState(navController)
        }

    private val sut: PicFindAppState
        @Composable get() = sutWithNavController(rememberTestNavController())

    @Test
    fun detects_current_destination_changed() = runTest {
        var curDestination: String? = null
        composeTestRule.setContent {
            val navController = rememberTestNavController()
            val sut = sutWithNavController(navController)

            curDestination = sut.currentDestination?.route

            LaunchedEffect(Unit) {
                navController.setCurrentDestination("b")
            }
        }
        expectThat(curDestination).isEqualTo("b")
    }

    @Test
    fun confirms_top_level_destinations() {
        runComposeTest {
            expectThat(sut.topLevelDestinations)
                .single()
                .isEqualTo(TopLevelDestination.PICTURES)
        }
    }

    private fun runComposeTest(composable: @Composable () -> Unit) {
        runTest {
            composeTestRule.setContent(composable)
        }
    }

    @Composable
    private fun rememberTestNavController(): TestNavHostController {
        val context = LocalContext.current
        return remember {
            TestNavHostController(context).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
                graph = createGraph(startDestination = "a") {
                    composable("a") { }
                    composable("b") { }
                    composable("c") { }
                }
            }
        }
    }
}
