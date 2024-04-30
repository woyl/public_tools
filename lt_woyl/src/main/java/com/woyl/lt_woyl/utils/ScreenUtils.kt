package com.woyl.lt_woyl.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager

object ScreenUtils {

    fun getScreenWith (context: Context) :Int{
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        val p = Point()
        display.getRealSize(p)
        return p.x
    }

    fun getScreenHeight (context: Context) :Int{
        val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        val p = Point()
        display.getRealSize(p)
        return p.y
    }

    fun getScreenWith (activity: Activity) :Int{
        val metrics = DisplayMetrics()
        val display = activity.windowManager.defaultDisplay
        display.getRealMetrics(metrics)
        return metrics.widthPixels
    }

    fun getScreenHeight (activity: Activity) :Int{
        val metrics = DisplayMetrics()
        val display = activity.windowManager.defaultDisplay
        display.getRealMetrics(metrics)
        return metrics.heightPixels
    }

}