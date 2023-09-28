package jp.matsuura.qiitasearchandroidapp.data.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TagEntity(
    val name: String,
    val versions: List<String>
)