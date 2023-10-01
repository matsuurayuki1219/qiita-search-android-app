package jp.matsuura.qiitasearchandroidapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import jp.matsuura.qiitasearchandroidapp.R
import jp.matsuura.qiitasearchandroidapp.ext.observeWithLifecycle
import jp.matsuura.qiitasearchandroidapp.model.QiitaArticleModel
import jp.matsuura.qiitasearchandroidapp.ui.common.AppTopSearchBar
import jp.matsuura.qiitasearchandroidapp.ui.common.LoadingView
import jp.matsuura.qiitasearchandroidapp.ui.home.components.QiitaArticleItem
import jp.matsuura.qiitasearchandroidapp.ui.theme.QiitaSearchAndroidAppTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (url: String) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    viewModel.uiEvent.observeWithLifecycle {
        when (it) {
            is HomeViewModel.UiEvent.NetworkError -> {
                snackbarHostState.showSnackbar(context.getString(R.string.common_network_error_message))
            }

            is HomeViewModel.UiEvent.Error -> {
                snackbarHostState.showSnackbar(context.getString(R.string.common_unknown_error_message))
            }
        }
    }

    val qiitaItems = viewModel.qiitaArticles.collectAsLazyPagingItems()
    HomeScreen(
        qiitaItems = qiitaItems,
        snackbarHostState = snackbarHostState,
        onItemClick = onItemClick,
        onEditDone = viewModel::onSearchButtonClicked,
        onError = viewModel::onError,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    qiitaItems: LazyPagingItems<QiitaArticleModel>,
    snackbarHostState: SnackbarHostState,
    onItemClick: (url: String) -> Unit,
    onEditDone: (String) -> Unit,
    onError: (Throwable) -> Unit,
) {
    QiitaSearchAndroidAppTheme {
        Scaffold(
            topBar = {
                AppTopSearchBar(
                    onDone = onEditDone,
                )
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
        ) {

            ErrorHandler(
                loadState = qiitaItems.loadState,
                onError = onError,
            )

            LoadingItem(loadState = qiitaItems.loadState)

            Column(modifier = Modifier.padding(it)) {
                LazyColumn {
                    items(qiitaItems) { qiitaItem ->
                        qiitaItem?.let { item ->
                            QiitaArticleItem(
                                modifier = Modifier.fillMaxWidth(),
                                qiitaItem = item,
                                onClick = onItemClick,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingItem(loadState: CombinedLoadStates) {
    val refreshLoadState = loadState.refresh
    if (refreshLoadState is LoadState.Loading) {
        LoadingView()
    }
}

@Composable
private fun ErrorHandler(
    loadState: CombinedLoadStates,
    onError: (Throwable) -> Unit,
) {
    val refreshLoadState = loadState.refresh
    val prependLoadState = loadState.prepend
    val appendLoadState = loadState.append
    if (refreshLoadState is LoadState.Error) {
        onError(refreshLoadState.error)
    } else if (prependLoadState is LoadState.Error) {
        onError(prependLoadState.error)
    } else if (appendLoadState is LoadState.Error) {
        onError(appendLoadState.error)
    }
}
