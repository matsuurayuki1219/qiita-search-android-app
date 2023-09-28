package jp.matsuura.qiitasearchandroidapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.matsuura.qiitasearchandroidapp.domain.GetQiitaItemUseCase
import jp.matsuura.qiitasearchandroidapp.model.QiitaItemModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getQiitaItem: GetQiitaItemUseCase,
): ViewModel() {

    private val _uiEvent = Channel<UiEvent>(Channel.CONFLATED)
    val uiEvent = _uiEvent.receiveAsFlow()

    private val queryChanged = Channel<String?>(Channel.CONFLATED)

    @OptIn(ExperimentalCoroutinesApi::class)
    val qiitaItems: Flow<PagingData<QiitaItemModel>> =
        queryChanged.receiveAsFlow().flatMapLatest {
            getQiitaItem(query = it)
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
            if (error is IOException) {
                _uiEvent.send(UiEvent.NetworkError)
            } else {
                _uiEvent.send(UiEvent.Error(error = error))
            }
        }
    }

    sealed interface UiEvent {
        object NetworkError : UiEvent
        data class Error(val error: Throwable) : UiEvent
    }
}