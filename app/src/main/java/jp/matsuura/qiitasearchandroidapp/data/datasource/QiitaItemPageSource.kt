package jp.matsuura.qiitasearchandroidapp.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import jp.matsuura.qiitasearchandroidapp.data.api.QiitaApi
import jp.matsuura.qiitasearchandroidapp.data.entity.QiitaItemEntity
import jp.matsuura.qiitasearchandroidapp.ext.requireBody

class QiitaItemPageSource(
    private val api: QiitaApi,
    private val query: String?,
) : PagingSource<Int, QiitaItemEntity>() {

    override fun getRefreshKey(state: PagingState<Int, QiitaItemEntity>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QiitaItemEntity> {
        val page = params.key ?: 1
        val next = page + 1
        val prev = if (page == 1) null else page - 1
        return try {
            val resp = api.getItems(page = page, perPage = 10, query = query).requireBody()
            LoadResult.Page(
                data = resp,
                prevKey = prev,
                nextKey = next,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}