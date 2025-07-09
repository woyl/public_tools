package com.woyl.my_

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowInsetsAnimation
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.Type.ime
import androidx.core.view.doOnLayout
import androidx.fragment.app.DialogFragment

class SoftDialogFragment : DialogFragment() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // 获取 WindowInsetsController 1. 使用 WindowInsetsController (推荐方法)
        val windowInsetsController = requireActivity().window.decorView.windowInsetsController ?: return

        // 设置系统窗口Insets行为
        windowInsetsController.systemBarsBehavior =
            WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        // 设置布局在软键盘显示时调整
        view.doOnLayout {
            val rootView = requireActivity().findViewById<View>(android.R.id.content)
            rootView.fitsSystemWindows = true

            // 监听软键盘可见性变化
            val keyboardCallback = object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_CONTINUE_ON_SUBTREE) {

                override fun onProgress(
                    insets: WindowInsetsCompat,
                    runningAnimations: List<WindowInsetsAnimationCompat?>
                ): WindowInsetsCompat {
                    val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
                    // 处理软键盘高度变化，例如调整布局
                    return insets
                }

                override fun onEnd(animation: WindowInsetsAnimationCompat) {
                    super.onEnd(animation)
                    // 动画结束时的处理
                }
            }

            // 注册回调
            windowInsetsController.let { controller ->
                controller.hide(WindowInsetsCompat.Type.ime())
                controller.show(WindowInsetsCompat.Type.ime())
//                controller.addWindowInsetsAnimationCallback(keyboardCallback)
            }
        }



        // 使用 WindowCompat 处理软键盘行为 2在布局中使用 androidx.core:core-ktx
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)

        // 设置视图的窗口Insets动画回调
        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())

            // 调整视图位置或边距
            (v.layoutParams as? ViewGroup.MarginLayoutParams)?.let { params ->
                params.bottomMargin = imeInsets.bottom + systemBarsInsets.bottom
                v.layoutParams = params
            }

            insets
        }


        // 设置回调监听软键盘动画
        ViewCompat.setWindowInsetsAnimationCallback(
            view,
            object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_CONTINUE_ON_SUBTREE) {
                override fun onProgress(
                    insets: WindowInsetsCompat,
                    runningAnimations: List<WindowInsetsAnimationCompat?>
                ): WindowInsetsCompat {
                    val imeHeight = insets.getInsets(ime()).bottom

                    // 调整布局位置
                    view.translationY = -imeHeight.toFloat()

                    return insets
                }
            }
        )

        // 请求应用窗口Insets（使用兼容库版本）
        ViewCompat.requestApplyInsets(view)
    }
}