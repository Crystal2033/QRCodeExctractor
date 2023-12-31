package com.crystal2033.qrextractor

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.crystal2033.qrextractor.core.model.User
import com.crystal2033.qrextractor.nav_graphs.add_qr_data.addQRCodeGraph
import com.crystal2033.qrextractor.nav_graphs.documents.documentsGraph
import com.crystal2033.qrextractor.nav_graphs.history.historyGraph
import com.crystal2033.qrextractor.nav_graphs.home.homeGraph
import com.crystal2033.qrextractor.nav_graphs.scanner.scannerGraph
import com.crystal2033.qrextractor.ui.NavBottomBar
import com.crystal2033.qrextractor.ui.theme.QRExtractorTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this,
                CAMERAX_PERMISSIONS,
                0
            )
        }

        setContent {
            QRExtractorTheme(darkTheme = true) {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    bottomBar = {
                        NavBottomBar(navController, applicationContext)
                    }
                ) {
                    MyNavGraph(
                        navController = navController,
                        context = applicationContext,
                        snackbarHostState = snackbarHostState
                    )
                }
            }
        }


    }

    private fun hasRequiredPermissions(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA
        )
    }
}


@Composable
fun MyNavGraph(
    navController: NavHostController,
    context: Context,
    snackbarHostState: SnackbarHostState

) {

    val userViewModel: User by remember {
        mutableStateOf(User("123", 1))

    }
    NavHost(
        navController = navController,
        startDestination = context.resources.getString(R.string.home_head_graph_route)
    ) {
        //val userViewModel = User("some user", 1)

        homeGraph(navController, context, snackbarHostState)

        addQRCodeGraph(navController, context, snackbarHostState, userViewModel)

        scannerGraph(navController, context, snackbarHostState, userViewModel)

        historyGraph(navController, context, snackbarHostState)

        documentsGraph(navController, context, snackbarHostState)
    }
}

@Composable
fun TextWindow(string: String) {
//    val result = remember { mutableStateOf<Uri?>(null) }
//    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.CreateDocument("application/pdf")) {
//        result.value = it
//    }
//
//    Button(onClick = { launcher.launch(result.value.toString()) }) {
//        Text(text = "Open Document")
//    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = string,
            fontSize = 35.sp
        )
    }
}


//@Composable
//inline fun <reified T : ViewModel> NavBackStackEntry.sharedScannedDataGroupsViewModel(
//    navController: NavController,
//    user: User
//): T {
//    val navGraphRoute = destination.parent?.route ?: return viewModel()
//    val parentEntry = remember(this) {
//        navController.getBackStackEntry(navGraphRoute)
//    }
//
//    val factory = EntryPointAccessors.fromActivity(
//        LocalContext.current as Activity,
//        ScannedDataViewModelFactoryProvider::class.java
//    ).scannedDataGroupsViewModelFactory()
//
//    return viewModel(
//        viewModelStoreOwner = parentEntry,
//        factory = ScannedDataGroupsViewModel.provideFactory(factory, user)
//    )
//}

//@Composable
//fun scannedDataGroupsViewModel(user: User?): ScannedDataGroupsViewModel {
//    val factory = EntryPointAccessors.fromActivity(
//        LocalContext.current as Activity,
//        ScannedDataViewModelFactoryProvider::class.java
//    ).scannedDataGroupsViewModelFactory()
//
//    return viewModel(factory = ScannedDataGroupsViewModel.provideFactory(factory, user))
//}


