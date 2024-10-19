package com.woyl.lt_woyl.utils.ExtUtils

import android.util.Base64
import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.isNumeric(): Boolean {
    return this.all { it.isDigit() }
}

fun String.isWebUrl(): Boolean {
    val regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
    val pattern: Pattern = Pattern.compile(regex)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.base64() = Base64.encodeToString(toByteArray(), Base64.DEFAULT)
fun String.deCodeBase64() = Base64.decode(this, Base64.DEFAULT)