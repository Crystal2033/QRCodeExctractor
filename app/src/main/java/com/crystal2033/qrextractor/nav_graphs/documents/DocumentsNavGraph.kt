package com.crystal2033.qrextractor.nav_graphs.documents

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.crystal2033.qrextractor.R
import com.crystal2033.qrextractor.TextWindow

fun NavGraphBuilder.documentsGraph(
    navController: NavController,
    context: Context,
    snackbarHostState: SnackbarHostState
) {
    navigation(
        startDestination = context.resources.getString(R.string.documents_route),
        route = context.resources.getString(R.string.documents_head_graph_route)
    ) {
        composable(context.resources.getString(R.string.documents_route)) {
            TextWindow("Documents")
        }
    }
}