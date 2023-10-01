package jp.matsuura.qiitasearchandroidapp.domain

import androidx.paging.PagingData
import jp.matsuura.qiitasearchandroidapp.data.repository.QiitaRepository
import jp.matsuura.qiitasearchandroidapp.model.QiitaArticleModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetQiitaArticlesUseCase @Inject constructor(
    private val repository: QiitaRepository,
) {
    operator fun invoke(query: String?): Flow<PagingData<QiitaArticleModel>> {
        return repository.getArticles(query = query)
    }
}