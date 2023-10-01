package jp.matsuura.qiitasearchandroidapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import jp.matsuura.qiitasearchandroidapp.ui.home.HomeScreen
import jp.matsuura.qiitasearchandroidapp.ui.theme.QiitaSearchAndroidAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            QiitaSearchAndroidAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val externalNavController = rememberExternalNavController()
                    HomeScreen(
                        onItemClick = externalNavController::navigate,
                    )
                }
            }
        }
    }
}

private class ExternalNavController(
    private val context: Context,
) {

    fun navigate(url: String) {
        val uri: Uri = url.toUri()
        navigateToCustomTab(context = context, uri = uri)
    }

    private fun navigateToCustomTab(context: Context, uri: Uri) {
        CustomTabsIntent.Builder()
            .setShowTitle(true)
            .build()
            .launchUrl(context, uri)
    }
}

@Composable
private fun rememberExternalNavController(): ExternalNavController {
    val context = LocalContext.current
    return remember(context) {
        ExternalNavController(context = context)
    }
}