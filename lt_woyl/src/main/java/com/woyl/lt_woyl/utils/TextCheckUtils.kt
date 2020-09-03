package com.woyl.lt_woyl.utils

import android.text.TextUtils


object TextCheckUtils {

    private const val REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$"

    fun isPhoneNum(s: String): Boolean {
        return s.matches(Regex(REGEX_MOBILE_SIMPLE))
    }

    fun isPassword(s: String): Boolean {
        return !TextUtils.isEmpty(s) && s.length >= 6 && s.length <= 20
    }

    fun isCode(s: String): Boolean {
        return !TextUtils.isEmpty(s) && s.length == 6
    }

    fun convertPhoneNum(phone: String?): String? {
        return if (phone != null && phone.length == 11) phone.substring(
            0,
            3
        ) + "****" + phone.substring(phone.length - 4, phone.length) else phone
    }

    fun convertNickName(name: String?): String? {
        val length = name?.length ?: 0
        val start = 1
        val endNum = length - start
        return if (endNum <= 0) {
            "*"
        } else if (endNum < 2) {
            name!!.substring(0, start) + "*"
        } else {
            val sb = StringBuilder(length)
            sb.append(name!!.substring(0, start))
            for (i in start until endNum) {
                sb.append('*')
            }
            sb.append(name.substring(length - 1, length)).toString()
        }
    }
}