package jp.matsuura.qiitasearchandroidapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.qiitasearchandroidapp.domain.GetQiitaArticlesUseCase
import jp.matsuura.qiitasearchandroidapp.exception.LimitedAccessException
import jp.matsuura.qiitasearchandroidapp.model.QiitaArticleModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getQiitaArticles: GetQiitaArticlesUseCase,
): ViewModel() {

    private val _uiEvent = Channel<UiEvent>(Channel.CONFLATED)
    val uiEvent = _uiEvent.receiveAsFlow()

    private val queryChanged = Channel<String?>(Channel.CONFLATED)

    @OptIn(ExperimentalCoroutinesApi::class)
    val qiitaArticles: Flow<PagingData<QiitaArticleModel>> =
        queryChanged.receiveAsFlow().flatMapLatest {
            getQiitaArticles(query = it)
        }.cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            queryChanged.send(null)
        }
    }

    fun onSearchButtonClicked(s: String) {
        if (s.isEmpty()) {
            return
        }
        viewModelScope.launch {
            queryChanged.send(s)
        }
    }

    fun onError(error: Throwable) {
        viewModelScope.launch {
            when (error) {
                is LimitedAccessException -> {
                    _uiEvent.send(UiEvent.LimitedAccessError)
                }
                is IOException -> {
                    _uiEvent.send(UiEvent.NetworkError)
                }
                else -> {
                    _uiEvent.send(UiEvent.Error(error = error))
                }
            }
        }
    }

    sealed interface UiEvent {
        object NetworkError : UiEvent
        data class Error(val error: Throwable) : UiEvent
        object LimitedAccessError : UiEvent
    }
}