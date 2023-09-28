package jp.matsuura.qiitasearchandroidapp.model

data class UserModel(
    val id: String,
    // TODO: 必要
    val name: String,
    // TODO: 必要
    val profileImageUrl: String,
    val followeesCount: Int,
    val followersCount: Int,
    val itemsCount: Int,
)
