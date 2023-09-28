package jp.matsuura.qiitasearchandroidapp.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QiitaItemEntity(
    val body: String,
    @Json(name = "comments_count")
    val commentsCount: Int,
    @Json(name = "created_at")
    val createdAt: String,
    val id: String,
    @Json(name = "likes_count")
    val likesCount: Int,
    @Json(name = "rendered_body")
    val renderedBody: String,
    @Json(name = "stocks_count")
    val stocksCount: Int,
    val tags: List<TagEntity>,
    val title: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    val url: String,
    val user: UserEntity
)