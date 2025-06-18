package com.woyl.lt_woyl.dialog

import android.annotation.SuppressLint
import android.app.Dialog

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding

/**
 * 对话框基类，提供常用的对话框功能和属性配置
 * 在 Kotlin 的基类 DialogFragment 中重写 show() 方法时，需要确保在 Activity 有效生命周期内执行显示 / 隐藏操作。以下是针对 Activity 创建前 和 Activity 销毁后 调用 show()/dismiss() 的解决方案：
 * 1. 问题分析
 * Activity 生命周期风险：
 * 创建前：Activity 尚未完成初始化（如 onCreate() 未执行），此时调用 show() 可能导致 IllegalStateException。
 * 销毁后：Activity 已进入 onDestroy() 状态，此时调用 show()/dismiss() 会引发崩溃。
 * 关键生命周期状态：
 * Activity 有效区间：从 onStart() 到 onStop() 之间。
 * 安全判断条件：isAdded && activity?.isFinishing == false && activity?.isDestroyed == false。
 */
abstract class BaseDialog(
    context: Context,
    @StyleRes themeResId: Int = 0
) : Dialog(context, themeResId) {

    // 对话框宽度，默认是WRAP_CONTENT
    protected open var dialogWidth: Int = ViewGroup.LayoutParams.WRAP_CONTENT

    // 对话框高度，默认是WRAP_CONTENT
    protected open var dialogHeight: Int = ViewGroup.LayoutParams.WRAP_CONTENT

    // 是否支持沉浸式状态栏
    protected open var isImmersive: Boolean = false

    // 是否点击外部区域关闭对话框
    protected open var cancelOnTouchOutside: Boolean = true

    // 对话框动画资源ID
    @StyleRes
    protected open var dialogAnimation: Int = 0

    // 布局资源ID
    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    // 初始化视图
    protected abstract fun initView()

    // 初始化数据
    protected abstract fun initData()

    // 初始化监听
    protected abstract fun initListener()

    // 对话框显示回调
    protected open fun onDialogShow() {}

    // 对话框隐藏回调
    protected open fun onDialogHide() {}

    // 对话框销毁回调
    protected open fun onDialogDestroy() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initWindow()
        initView()
        initData()
        initListener()
    }

    private fun initWindow() {
        val window = window ?: return

        // 设置窗口属性
        window.apply {
            // 设置窗口位置和大小
            setGravity(Gravity.CENTER)
            setLayout(dialogWidth, dialogHeight)

            // 设置背景透明
            setBackgroundDrawableResource(android.R.color.transparent)

            // 设置对话框动画
            if (dialogAnimation != 0) {
                setWindowAnimations(dialogAnimation)
            }

            // 设置是否可取消
            setCancelable(cancelOnTouchOutside)
            setCanceledOnTouchOutside(cancelOnTouchOutside)

            // 沉浸式状态栏支持
            if (isImmersive) {
                setImmersiveMode(this)
            }
        }
    }

    @SuppressLint("NewApi")
    private fun setImmersiveMode(window: Window) {
        // 启用窗口的扩展布局
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 获取窗口的InsetsController
        val controller = WindowCompat.getInsetsController(window, window.decorView)

        // 隐藏状态栏和导航栏
        controller.hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())

        // 设置沉浸式粘性模式
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        // 调整内容的内边距，避免被系统UI遮挡
        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            val statusBarInsets = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            val navigationBarInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())

            // 根据需要调整内容的内边距
            view.updatePadding(
                top = statusBarInsets.top,
                bottom = navigationBarInsets.bottom
            )

            insets
        }
    }

    override fun show() {
        // 确保在Activity有效状态下显示对话框 这里有问题 activity没有赋值 为null show方法时候才开始创建
//        val activity = context as? AppCompatActivity
//        if (activity != null && !activity.isFinishing && !activity.isDestroyed) {
//            super.show()
//            onDialogShow()
//        }
        try {
            super.show()
            onDialogShow()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun dismiss() {
        // 确保在Activity有效状态下关闭对话框
//        val activity = context as? AppCompatActivity
//        if (activity != null && !activity.isFinishing && !activity.isDestroyed) {
//            super.dismiss()
//            onDialogHide()
//        }
        try {
            super.dismiss()
            onDialogHide()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        onDialogDestroy()
    }

    /**
     * 设置对话框宽度（像素）
     */
    fun setDialogWidth(width: Int): BaseDialog {
        this.dialogWidth = width
        return this
    }

    /**
     * 设置对话框高度（像素）
     */
    fun setDialogHeight(height: Int): BaseDialog {
        this.dialogHeight = height
        return this
    }

    /**
     * 设置对话框宽度为屏幕宽度的百分比
     */
    fun setDialogWidthPercent(percent: Float): BaseDialog {
        if (percent in 0.1f..1.0f) {
//            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//            val displayMetrics = windowManager.defaultDisplay?.metrics ?: return this
            val displayMetrics = getWindowSize(context)
            this.dialogWidth = (displayMetrics.first * percent).toInt()
        }
        return this
    }

    /**
     * 设置对话框高度为屏幕高度的百分比
     */
    fun setDialogHeightPercent(percent: Float): BaseDialog {
        if (percent in 0.1f..1.0f) {
//            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//            val displayMetrics = windowManager.defaultDisplay?.metrics ?: return this
            val displayMetrics = getWindowSize(context)
            this.dialogHeight = (displayMetrics.second * percent).toInt()
        }
        return this
    }

    // 获取窗口尺寸（替代 windowManager.defaultDisplay?.metrics）
    fun getWindowSize(context: Context): Pair<Int, Int> {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11+ 使用 WindowMetrics
            val windowMetrics = windowManager.currentWindowMetrics
            val insets = windowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())

            val width = windowMetrics.bounds.width() - insets.left - insets.right
            val height = windowMetrics.bounds.height() - insets.top - insets.bottom
            width to height
        } else {
            // Android 10及以下使用 DisplayMetrics
            @Suppress("DEPRECATION")
            val displayMetrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            windowManager.defaultDisplay?.getMetrics(displayMetrics)
            displayMetrics.widthPixels to displayMetrics.heightPixels
        }
    }

    // 获取屏幕真实尺寸（包括系统装饰区域）
    fun getRealScreenSize(context: Context): Pair<Int, Int> {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11+ 使用 WindowManager.currentWindowMetrics
            val windowMetrics = windowManager.currentWindowMetrics
            val width = windowMetrics.bounds.width()
            val height = windowMetrics.bounds.height()
            width to height
        } else {
            // Android 10及以下使用 Display.getRealSize
            val display = windowManager.defaultDisplay
            val point = Point()
            @Suppress("DEPRECATION")
            display.getRealSize(point)
            point.x to point.y
        }
    }

    /**
     * 设置是否支持沉浸式状态栏
     */
    fun setImmersive(immersive: Boolean): BaseDialog {
        isImmersive = immersive
        return this
    }

    /**
     * 设置点击外部区域是否关闭对话框
     */
    fun setCancelOnTouchOutside(cancel: Boolean): BaseDialog {
        cancelOnTouchOutside = cancel
        setCancelable(cancel)
        setCanceledOnTouchOutside(cancel)
        return this
    }

    /**
     * 设置对话框动画
     */
    fun setDialogAnimation(@StyleRes animationResId: Int): BaseDialog {
        dialogAnimation = animationResId
        return this
    }
}