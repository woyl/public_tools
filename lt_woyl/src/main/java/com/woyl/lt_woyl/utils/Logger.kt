package com.woyl.lt_woyl.utils

import android.os.Build
import android.util.Log
import androidx.media3.common.BuildConfig
import androidx.media3.common.util.Util

object Logger {

    /**打印日志开关*/
    private var IS_DEBUG = BuildConfig.DEBUG
    /**内部判断是否打开*/
    private var mIsInnerShowLog = IS_DEBUG

    fun getFunctionName() : String {
        val sts = Thread.currentThread().stackTrace
        for (st in sts) {
            if (st.isNativeMethod) {
                continue
            }
            if (st.className == Thread::class.java.name) {
                continue
            }
            if (st.className == "com.woyl.lt_woyl") {
                continue
            }
            return "[line:]${st.lineNumber}"
        }
        return ""
    }

    fun i (tag:String,message: Any) {
        if (mIsInnerShowLog) {
            Log.i(tag, "[Out:${message}]")
        }
    }

    fun d (tag:String,message: Any) {
        if (mIsInnerShowLog) {
            Log.d(tag, "[Out:${message}]")
        }
    }

    fun w (tag:String,message: Any) {
        if (mIsInnerShowLog) {
            Log.w(tag, "[Out:${message}]")
        }
    }

    fun v (tag:String,message: Any) {
        if (mIsInnerShowLog) {
            Log.v(tag, "[Out:${message}]")
        }
    }

    fun e (tag:String,message: Any) {
        if (mIsInnerShowLog) {
            Log.e(tag, "[Out:${message}]")
        }
    }

    fun e (tag:String,message: Exception) {
        if (mIsInnerShowLog) {
            Log.e(tag, "[Out:${message}]")
        }
    }

}