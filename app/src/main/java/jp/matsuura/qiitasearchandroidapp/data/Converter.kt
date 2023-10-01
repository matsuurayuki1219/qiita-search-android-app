package jp.matsuura.qiitasearchandroidapp.data

import jp.matsuura.qiitasearchandroidapp.data.entity.QiitaArticleEntity
import jp.matsuura.qiitasearchandroidapp.data.entity.TagEntity
import jp.matsuura.qiitasearchandroidapp.data.entity.UserEntity
import jp.matsuura.qiitasearchandroidapp.model.QiitaArticleModel
import jp.matsuura.qiitasearchandroidapp.model.TagModel
import jp.matsuura.qiitasearchandroidapp.model.UserModel

fun QiitaArticleEntity.toModel() = QiitaArticleModel(
    body = body,
    commentsCount = commentsCount,
    createdAt = createdAt,
    id = id,
    likesCount = likesCount,
    renderedBody = renderedBody,
    tags = tags.map { it.toModel() },
    title = title,
    updatedAt = updatedAt,
    url = url,
    user = user.toModel()
)

fun TagEntity.toModel() = TagModel(
    name = name,
    versions = versions,
)

fun UserEntity.toModel() = UserModel(
    followersCount = followersCount,
    followeesCount = followeesCount,
    id = id,
    itemsCount = itemsCount,
    name = name,
    profileImageUrl = profileImageUrl,
)