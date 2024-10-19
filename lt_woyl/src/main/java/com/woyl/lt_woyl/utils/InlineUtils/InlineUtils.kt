package com.woyl.lt_woyl.utils.InlineUtils

import android.app.Activity
import android.content.Intent

class InlineUtils {
}

/**
 * activity跳转 startActivity<activity>(
 * */

inline fun <reified T> Activity.startActivity() {
    startActivity(Intent(this,T::class.java))
}