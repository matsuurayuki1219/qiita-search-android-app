package jp.matsuura.qiitasearchandroidapp.model

import java.time.OffsetDateTime

data class QiitaArticleModel(
    val id: String,
    val title: String,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime,
    val likesCount: Int,
    val user: UserModel,
    val tags: List<TagModel>,
    val url: String,
)