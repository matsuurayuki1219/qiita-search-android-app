package jp.matsuura.qiitasearchandroidapp.data.api

import jp.matsuura.qiitasearchandroidapp.data.entity.QiitaItemEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaApi {

    @GET("api/v2/items/")
    suspend fun getItems(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int? = null,
        @Query("query") query: String? = null,
    ): Response<List<QiitaItemEntity>>

}