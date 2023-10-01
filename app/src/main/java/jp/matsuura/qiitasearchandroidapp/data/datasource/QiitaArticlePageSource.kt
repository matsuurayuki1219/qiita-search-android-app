package jp.matsuura.qiitasearchandroidapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jp.matsuura.qiitasearchandroidapp.data.api.QiitaApi
import jp.matsuura.qiitasearchandroidapp.data.entity.QiitaArticleEntity
import jp.matsuura.qiitasearchandroidapp.exception.LimitedAccessException
import retrofit2.HttpException

class QiitaArticlePageSource(
    private val api: QiitaApi,
    private val query: String?,
) : PagingSource<Int, QiitaArticleEntity>() {

    override fun getRefreshKey(state: PagingState<Int, QiitaArticleEntity>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QiitaArticleEntity> {
        val page = params.key ?: 1
        val next = page + 1
        val prev = if (page == 1) null else page - 1
        return try {
            val resp = api.getArticles(page = page, perPage = 10, query = query)
            val body = resp.body()
            if (body != null) {
                LoadResult.Page(
                    data = body,
                    prevKey = prev,
                    nextKey = next,
                )
            } else {
                if (resp.code() == 403) {
                    throw LimitedAccessException()
                } else {
                    throw HttpException(resp)
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}