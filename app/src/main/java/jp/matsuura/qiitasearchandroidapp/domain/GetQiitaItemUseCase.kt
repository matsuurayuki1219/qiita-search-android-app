package jp.matsuura.qiitasearchandroidapp.domain

import androidx.paging.PagingData
import jp.matsuura.qiitasearchandroidapp.data.repository.QiitaRepository
import jp.matsuura.qiitasearchandroidapp.model.QiitaItemModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetQiitaItemUseCase @Inject constructor(
    private val repository: QiitaRepository,
) {
    operator fun invoke(query: String?): Flow<PagingData<QiitaItemModel>> {
        return repository.getItems(query = query)
    }
}