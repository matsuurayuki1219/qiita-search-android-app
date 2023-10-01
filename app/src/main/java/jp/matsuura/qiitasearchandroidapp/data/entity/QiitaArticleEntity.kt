package jp.matsuura.qiitasearchandroidapp.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QiitaArticleEntity(
    val id: String,
    val title: String,
    val url: String,
    val user: UserEntity,
    @Json(name = "likes_count")
    val likesCount: Int,
    val tags: List<TagEntity>,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String,
)