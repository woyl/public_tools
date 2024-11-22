package com.woyl.lt_woyl.utils

import android.content.Context
import android.util.TypedValue




object PixelUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dp2px (context: Context,dpValue :Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f) .toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dp(context: Context,pxValue:Float):Int {
        val scale = context.resources.displayMetrics.density
        return  (pxValue / scale + 0.5f).toInt()
    }

    /**
     * sp转px
     */
    fun sp2px(context: Context, spVal: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spVal, context.resources.displayMetrics
        ).toInt()
    }

    /**
     * px转sp
     */
    fun px2sp(context: Context, pxVal: Float): Float {
        return pxVal / context.resources.displayMetrics.scaledDensity
    }
}
