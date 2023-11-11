package com.crystal2033.qrextractor.nav_graphs

import android.content.Context
import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.crystal2033.qrextractor.R
import com.crystal2033.qrextractor.core.LOG_TAG_NAMES
import com.crystal2033.qrextractor.core.User
import com.crystal2033.qrextractor.scanner_feature.scanned_info_list.presentation.ScannedListView
import com.crystal2033.qrextractor.scanner_feature.scanned_info_list.presentation.viewmodel.ScannedDataGroupsViewModel
import com.crystal2033.qrextractor.scanner_feature.scanner.presentation.QRCodeView
import com.crystal2033.qrextractor.scanner_feature.scanner.presentation.viewmodel.QRCodeScannerViewModel
import com.crystal2033.qrextractor.sharedNotHiltViewModel
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
            val viewModel2 = it.sharedNotHiltViewModel<ScannedDataGroupsViewModel>(
                navController = navController,
                user = userViewModel
            )
            viewModel2.printUserAddress()
            Log.i(
                LOG_TAG_NAMES.INFO_TAG,
                "GOT VIEWMODEL=${System.identityHashCode(viewModel2)}"
            )
            QRCodeView(
                viewModel = viewModel,
                onNavigate = { navigationEvent ->
                    navController.navigate(navigationEvent.route)
                },
                snackbarHostState = snackbarHostState
            )
        }

        composable(context.resources.getString(R.string.list_of_scanned_objects_route)) {
            //val viewModel = scannedDataGroupsViewModel(user = userViewModel)
            val viewModel = it.sharedNotHiltViewModel<ScannedDataGroupsViewModel>(
                navController = navController,
                user = userViewModel
            )
            viewModel.printUserAddress()
            Log.i(
                LOG_TAG_NAMES.INFO_TAG,
                "GOT VIEWMODEL=${System.identityHashCode(viewModel)}"
            )
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