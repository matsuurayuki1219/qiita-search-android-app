package jp.matsuura.qiitasearchandroidapp.ext

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun OffsetDateTime.toAppString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分")
    return this.format(formatter)
}