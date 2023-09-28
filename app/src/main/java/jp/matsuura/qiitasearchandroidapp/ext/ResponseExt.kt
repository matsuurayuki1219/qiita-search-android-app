package jp.matsuura.qiitasearchandroidapp.ext

import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.requireBody(): T {
    val body = this.body()
    if (body != null) {
        return body
    } else {
        throw HttpException(this)
    }
}