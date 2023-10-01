package jp.matsuura.qiitasearchandroidapp.model

data class QiitaArticleModel(
    val id: String,
    val title: String,
    val createdAt: String,
    val updatedAt: String,
    val likesCount: Int,
    val user: UserModel,
    val tags: List<TagModel>,
    val url: String,
)