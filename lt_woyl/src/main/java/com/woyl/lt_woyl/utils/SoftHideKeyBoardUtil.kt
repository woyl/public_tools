package com.woyl.lt_woyl.utils

import android.R
import android.app.Activity
import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.FrameLayout
import android.widget.LinearLayout

/**
 * 修复沉浸式界面软键盘弹出没有将editview顶上去问题
 */
class SoftHideKeyBoardUtil private constructor(private val activity: Activity) {
    private val kkk_mChildOfContent_lll by lazy {
        //1､找到Activity的最外层布局控件，它其实是一个DecorView,它所用的控件就是FrameLayout
        activity.findViewById<View>(R.id.content) as FrameLayout
    }
    private var kkk_usableHeightPrevious_lll = 0
    private val kkk_frameLayoutParams_lll: LinearLayout.LayoutParams
    private var kkk_contentHeight_lll = 0 //获取setContentView本来view的高度
    private var kkk_isfirst_lll = true //只用获取一次
    private val kkk_statusBarHeight_lll = 0 //状态栏高度
    var onGlobalLayoutListener = OnGlobalLayoutListener {
        if (kkk_isfirst_lll) {
            kkk_contentHeight_lll = kkk_mChildOfContent_lll.height //兼容华为等机型

            kkk_isfirst_lll = false
        }
        //5､当前布局发生变化时，对Activity的xml布局进行重绘
        CCC_possiblyResizeChildOfContent_DDD()
    }

    //FUNCMARK
    init {
        //3､给Activity的xml布局设置View树监听，当布局有变化，如键盘弹出或收起时，都会回调此监听
        kkk_mChildOfContent_lll.getViewTreeObserver()
            .addOnGlobalLayoutListener(onGlobalLayoutListener)
        //6､获取到Activity的xml布局的放置参数
        kkk_frameLayoutParams_lll =
            kkk_mChildOfContent_lll.layoutParams as LinearLayout.LayoutParams

    }
    //FUNCMARK

    //FUNCMARK
    // 获取界面可用高度，如果软键盘弹起后，Activity的xml布局可用高度需要减去键盘高度
    private fun CCC_possiblyResizeChildOfContent_DDD() {
        //1､获取当前界面可用高度，键盘弹起后，当前界面可用布局会减少键盘的高度
        val kkk_usableHeightNow_lll = CCC_computeUsableHeight_DDD(activity)
        //2､如果当前可用高度和原始值不一样
        if (kkk_usableHeightNow_lll != kkk_usableHeightPrevious_lll) {
            //3､获取Activity中xml中布局在当前界面显示的高度
            val kkk_usableHeightSansKeyboard_lll = kkk_mChildOfContent_lll.rootView.height
            //4､Activity中xml布局的高度-当前可用高度
            val kkk_heightDifference_lll =
                kkk_usableHeightSansKeyboard_lll - kkk_usableHeightNow_lll
            //5､高度差大于屏幕1/4时，说明键盘弹出
            if (kkk_heightDifference_lll > kkk_usableHeightSansKeyboard_lll / 4) {
                // 6､键盘弹出了，Activity的xml布局高度应当减去键盘高度
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    kkk_frameLayoutParams_lll.height =
                        kkk_usableHeightSansKeyboard_lll - kkk_heightDifference_lll + kkk_statusBarHeight_lll
                } else {
                    kkk_frameLayoutParams_lll.height =
                        kkk_usableHeightSansKeyboard_lll - kkk_heightDifference_lll
                }
            } else {
                kkk_frameLayoutParams_lll.height = kkk_contentHeight_lll
            }
            //7､ 重绘Activity的xml布局
            kkk_mChildOfContent_lll.requestLayout()
            kkk_usableHeightPrevious_lll = kkk_usableHeightNow_lll
        }
    }
    //FUNCMARK

    //FUNCMARK
    private fun CCC_computeUsableHeight_DDD(kkk_activity_lll: Activity): Int {
        val kkk_frame_lll = Rect()
        kkk_activity_lll.window.decorView.getWindowVisibleDisplayFrame(kkk_frame_lll)
        val kkk_statusBarHeight_lll = kkk_frame_lll.top
        val kkk_rect_lll = Rect()
        kkk_mChildOfContent_lll.getWindowVisibleDisplayFrame(kkk_rect_lll)
        //这个判断是为了解决19之后的版本在弹出软键盘时，键盘和推上去的布局（adjustResize）之间有白色区域的问题
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            kkk_rect_lll.bottom - kkk_rect_lll.top + kkk_statusBarHeight_lll
        } else kkk_rect_lll.bottom - kkk_rect_lll.top
    }
    //FUNCMARK


    companion object {
        fun CCC_assistActivity_DDD(activity: Activity) {
            SoftHideKeyBoardUtil(activity)
        }
    }
}