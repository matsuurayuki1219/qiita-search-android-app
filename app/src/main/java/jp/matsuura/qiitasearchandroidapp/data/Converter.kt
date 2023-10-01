package jp.matsuura.qiitasearchandroidapp.data

import jp.matsuura.qiitasearchandroidapp.data.entity.QiitaArticleEntity
import jp.matsuura.qiitasearchandroidapp.data.entity.TagEntity
import jp.matsuura.qiitasearchandroidapp.data.entity.UserEntity
import jp.matsuura.qiitasearchandroidapp.ext.toOffsetDateTime
import jp.matsuura.qiitasearchandroidapp.model.QiitaArticleModel
import jp.matsuura.qiitasearchandroidapp.model.TagModel
import jp.matsuura.qiitasearchandroidapp.model.UserModel

fun QiitaArticleEntity.toModel() = QiitaArticleModel(
    createdAt = createdAt.toOffsetDateTime(),
    id = id,
    likesCount = likesCount,
    tags = tags.map { it.toModel() },
    title = title,
    updatedAt = updatedAt.toOffsetDateTime(),
    url = url,
    user = user.toModel()
)

fun TagEntity.toModel() = TagModel(
    name = name,
)

fun UserEntity.toModel() = UserModel(
    id = id,
    name = name,
    profileImageUrl = profileImageUrl,
)