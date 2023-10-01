package jp.matsuura.qiitasearchandroidapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import jp.matsuura.qiitasearchandroidapp.data.api.QiitaApi
import jp.matsuura.qiitasearchandroidapp.data.datasource.QiitaArticlePageSource
import jp.matsuura.qiitasearchandroidapp.data.toModel
import jp.matsuura.qiitasearchandroidapp.model.QiitaArticleModel
import jp.matsuura.qiitasearchandroidapp.utility.Const
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QiitaRepository @Inject constructor(
    private val api: QiitaApi,
) {
    fun getArticles(query: String?): Flow<PagingData<QiitaArticleModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = Const.PAGING3_PAGE_SIZE,
                enablePlaceholders = false,
                maxSize = Const.PAGING3_MAX_SIZE,
            ),
            pagingSourceFactory = {
                QiitaArticlePageSource(api = api, query = query)
            },
        ).flow.map { pagingData ->
            pagingData.map { it.toModel() }
        }
    }
}