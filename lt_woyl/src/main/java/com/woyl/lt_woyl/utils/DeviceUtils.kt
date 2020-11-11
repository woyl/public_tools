package com.woyl.lt_woyl.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.util.Log

object DeviceUtils {

    /**
     * 是否有刘海屏
     *
     * @return
     */
    fun hasNotchInScreen(activity: Activity): Boolean {
        // android  P 以上有标准 API 来判断是否有刘海屏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val decorView = activity.window.decorView
            val windowInsets = decorView.rootWindowInsets
            if (windowInsets != null) {
                val displayCutout = windowInsets.displayCutout
                if (displayCutout != null) {
                    val rects =
                        displayCutout.boundingRects
                    //通过判断是否存在rects来确定是否刘海屏手机
                    if (rects.size > 0) {
                        return true
                    }
                }
            }
        }
        // 通过其他方式判断是否有刘海屏  目前官方提供有开发文档的就 小米，vivo，华为（荣耀），oppo
        val manufacturer = Build.MANUFACTURER
        return when {
            TextUtils.isEmpty(manufacturer) -> {
                false
            }
            manufacturer.equals("HUAWEI", ignoreCase = true) -> {
                hasNotchHw(activity)
            }
            manufacturer.equals("xiaomi", ignoreCase = true) -> {
                hasNotchXiaoMi(activity)
            }
            manufacturer.equals("oppo", ignoreCase = true) -> {
                hasNotchOPPO(activity)
            }
            manufacturer.equals("vivo", ignoreCase = true) -> {
                hasNotchVIVO(activity)
            }
            else -> {
                false
            }
        }
    }


    /**
     * 判断vivo是否有刘海屏
     * https://swsdl.vivo.com.cn/appstore/developer/uploadfile/20180328/20180328152252602.pdf
     *
     * @param activity
     * @return
     */
    private fun hasNotchVIVO(activity: Activity): Boolean {
        return try {
            @SuppressLint("PrivateApi") val c =
                Class.forName("android.util.FtFeature")
            val get =
                c.getMethod("isFeatureSupport", Int::class.javaPrimitiveType)
            get.invoke(c, 0x20) as Boolean
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 判断oppo是否有刘海屏
     * https://open.oppomobile.com/wiki/doc#id=10159
     *
     * @param activity
     * @return
     */
    private fun hasNotchOPPO(activity: Activity): Boolean {
        return activity.packageManager
            .hasSystemFeature("com.oppo.feature.screen.heteromorphism")
    }

    /**
     * 判断xiaomi是否有刘海屏
     * https://dev.mi.com/console/doc/detail?pId=1293
     *
     * @param activity
     * @return
     */
    private fun hasNotchXiaoMi(activity: Activity): Boolean {
        return try {
            @SuppressLint("PrivateApi") val c =
                Class.forName("android.os.SystemProperties")
            val get =
                c.getMethod("getInt", String::class.java, Int::class.javaPrimitiveType)
            get.invoke(c, "ro.miui.notch", 0) as Int == 1
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 判断华为是否有刘海屏
     * https://devcenter-test.huawei.com/consumer/cn/devservice/doc/50114
     *
     * @param activity
     * @return
     */
    private fun hasNotchHw(activity: Activity): Boolean {
        return try {
            val cl = activity.classLoader
            val HwNotchSizeUtil =
                cl.loadClass("com.huawei.android.util.HwNotchSizeUtil")
            val get = HwNotchSizeUtil.getMethod("hasNotchInScreen")
            get.invoke(HwNotchSizeUtil) as Boolean
        } catch (e: Exception) {
            false
        }
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    fun getSystemModel(): String? {
        return Build.MODEL
    }

    /**
     * 获取华为刘海的高
     * 比如华为p40挖孔屏的状态栏高度在挖孔上面只有一点26 的高度，并没有计算挖孔的高度，实际结果为108，这个要专门针对华为挖孔屏计算在内
     * @param context
     * @return
     */
    fun getNotchSizeAtHuawei(context: Context): Int {
        var ret = intArrayOf(0, 0)
        try {
            val cl = context.classLoader
            val HwNotchSizeUtil =
                cl.loadClass("com.huawei.android.util.HwNotchSizeUtil")
            val get = HwNotchSizeUtil.getMethod("getNotchSize")
            ret = get.invoke(HwNotchSizeUtil) as IntArray
        } catch (e: ClassNotFoundException) {
            Log.e("NotchScreenUtil", "getNotchSize ClassNotFoundException")
        } catch (e: NoSuchMethodException) {
            Log.e("NotchScreenUtil", "getNotchSize NoSuchMethodException")
        } catch (e: java.lang.Exception) {
            Log.e("NotchScreenUtil", "getNotchSize Exception")
        }
        return ret[1]
    }
}