package com.crystal2033.qrextractor.nav_graphs

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.crystal2033.qrextractor.R
import com.crystal2033.qrextractor.core.User
import com.crystal2033.qrextractor.scanner_feature.scanned_info_list.presentation.ScannedListView
import com.crystal2033.qrextractor.scanner_feature.scanned_info_list.presentation.viewmodel.ScannedDataGroupsViewModel
import com.crystal2033.qrextractor.scanner_feature.scanner.presentation.QRCodeView
import com.crystal2033.qrextractor.scanner_feature.scanner.presentation.viewmodel.QRCodeScannerViewModel
import com.crystal2033.qrextractor.sharedViewModel

fun NavGraphBuilder.scannerGraph(
    navController: NavController,
    context: Context,
    snackbarHostState: SnackbarHostState,
    userViewModel: User
) {
    navigation(
        startDestination = context.resources.getString(R.string.scanner_route),
        route = context.resources.getString(R.string.scanner_head_graph_route)
    ) {
        composable(context.resources.getString(R.string.scanner_route)) {
            val viewModel = it.sharedViewModel<QRCodeScannerViewModel>(navController)
            QRCodeView(
                viewModel = viewModel,
                onNavigate = { navigationEvent ->
                    navController.navigate(navigationEvent.route)
                },
                snackbarHostState = snackbarHostState
            )
        }

        composable(context.resources.getString(R.string.list_of_scanned_objects_route)) {
            //val viewModel = it.sharedViewModel<QRCodeScannerViewModel>(navController)
            val viewModel = it.sharedViewModel<ScannedDataGroupsViewModel>(navController)
            viewModel.user = userViewModel
            viewModel.refresh()
            ScannedListView(
                viewModel = viewModel,
                onNavigate = { navigationEvent ->
                    navController.navigate(navigationEvent.route)
                },
                onPopBackStack = {
                    navController.popBackStack()
                },
                snackbarHostState = snackbarHostState
            )
        }
    }
}