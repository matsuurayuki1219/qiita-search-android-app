package jp.matsuura.qiitasearchandroidapp.model

data class QiitaItemModel(
    val id: String,
    // TODO: 必要
    val title: String,
    // TODO: 必要
    val createdAt: String,
    // TODO: 必要
    val updatedAt: String,
    // TODO: 必要
    val likesCount: Int,
    // TODO: 必要
    val user: UserModel,
    val body: String,
    val commentsCount: Int,
    val renderedBody: String,
    val tags: List<TagModel>,
    val url: String,
)