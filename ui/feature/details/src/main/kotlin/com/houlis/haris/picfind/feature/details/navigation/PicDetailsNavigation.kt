package com.houlis.haris.picfind.feature.details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.houlis.haris.picfind.feature.details.ui.DetailsRoute
import com.houlis.haris.picfind.ui.common.savedstate.ReadState

internal const val PIC_ID_ARG = "pic_id"
private const val PIC_DETAILS_BASE = "pic_details_route/"
private const val PIC_DETAILS_ROUTE = "pic_details_route/{$PIC_ID_ARG}"

@JvmInline
internal value class PicDetailsArg(val picId: String) {
    constructor(readState: ReadState<String>) : this(readState(PIC_ID_ARG))
}

fun NavController.navigateToDetails(picId: String, navOptions: NavOptions? = null) {
    navigate(route = PIC_DETAILS_BASE + picId, navOptions = navOptions)
}

fun NavGraphBuilder.picDetailsScreen(onBackClicked: () -> Unit) {
    composable(
        route = PIC_DETAILS_ROUTE,
        arguments = listOf(
            navArgument(PIC_ID_ARG) {
                type = NavType.StringType
            }
        )
    ) {
        DetailsRoute(
            onBackClicked = onBackClicked
        )
    }
}
