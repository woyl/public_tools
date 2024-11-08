package com.woyl.lt_woyl.utils.ExtUtils

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.TypedValue
import android.view.WindowManager
import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

/**
 *  @author: luyao
 * @date: 2021/7/4 上午7:16
 */

fun Context.getScreenWidth(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val point = Point()
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
            display?.getRealSize(point)
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 -> {
            wm.defaultDisplay.getRealSize(point)
        }
        else -> {
            wm.defaultDisplay.getSize(point)
        }
    }
    return point.x
}

fun Context.getScreenHeight(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val point = Point()
    when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
            display?.getRealSize(point)
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 -> {
            wm.defaultDisplay.getRealSize(point)
        }
        else -> {
            wm.defaultDisplay.getSize(point)
        }
    }
    return point.y
}

fun Context.getAppScreenWidth(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val point = Point()
    wm.defaultDisplay.getSize(point)
    return point.x
}

fun Context.getAppScreenHeight(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val point = Point()
    wm.defaultDisplay.getSize(point)
    return point.y
}

inline val screenWidth: Int get() = application.resources.displayMetrics.widthPixels

inline val screenHeight: Int get() = application.resources.displayMetrics.heightPixels

//inline var Fragment.isFullScreen: Boolean
//    get() = activity?.isFullScreen == true
//    set(value) {
//        activity?.isFullScreen = value
//    }

//inline var Activity.isFullScreen: Boolean
//    get() = window.decorView.rootWindowInsetsCompat?.isVisible(WindowInsetsCompat.Type.systemBars()) == true
//    set(value) {
//        window.decorView.windowInsetsControllerCompat?.run {
//            val systemBars = WindowInsetsCompat.Type.systemBars()
//            if (value) show(systemBars) else hide(systemBars)
//        }
//    }

inline var Fragment.isLandscape: Boolean
    get() = activity?.isLandscape == true
    set(value) {
        activity?.isLandscape = value
    }

inline var Activity.isLandscape: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    set(value) {
        requestedOrientation = if (value) {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

inline var Fragment.isPortrait: Boolean
    get() = activity?.isPortrait == true
    set(value) {
        activity?.isPortrait = value
    }

inline var Activity.isPortrait: Boolean
    get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    set(value) {
        requestedOrientation = if (value) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }