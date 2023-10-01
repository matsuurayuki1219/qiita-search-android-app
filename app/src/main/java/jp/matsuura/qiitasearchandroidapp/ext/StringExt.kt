package jp.matsuura.qiitasearchandroidapp.ext

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun String.toOffsetDateTime(): OffsetDateTime {
    val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    return OffsetDateTime.parse(this, formatter)
}