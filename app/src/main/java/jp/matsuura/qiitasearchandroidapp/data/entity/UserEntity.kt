package jp.matsuura.qiitasearchandroidapp.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserEntity(
    val id: String,
    val name: String,
    @Json(name = "followees_count")
    val followeesCount: Int,
    @Json(name = "followers_count")
    val followersCount: Int,
    @Json(name = "items_count")
    val itemsCount: Int,
    @Json(name = "profile_image_url")
    val profileImageUrl: String,
)