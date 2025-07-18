package com.woyl.lt_woyl.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.woyl.lt_woyl.R

/**
 * 安全的 DialogFragment 基类，处理了 show() 和 dismiss() 的安全调用问题
 * 在 Kotlin 的基类 DialogFragment 中重写 show() 方法时，需要确保在 Activity 有效生命周期内执行显示 / 隐藏操作。以下是针对 Activity 创建前 和 Activity 销毁后 调用 show()/dismiss() 的解决方案：
 * 1. 问题分析
 * Activity 生命周期风险：
 * 创建前：Activity 尚未完成初始化（如 onCreate() 未执行），此时调用 show() 可能导致 IllegalStateException。
 * 销毁后：Activity 已进入 onDestroy() 状态，此时调用 show()/dismiss() 会引发崩溃。
 * 关键生命周期状态：
 * Activity 有效区间：从 onStart() 到 onStop() 之间。
 * 安全判断条件：isAdded && activity?.isFinishing == false && activity?.isDestroyed == false。
 *
 */
abstract class BaseSafeDialogFragment : DialogFragment() {

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

    private var pendingShowRequest = false
    private var isDialogDismissed = false
    var onShowListener: (() -> Unit)? = null
    var onDismissListener: (() -> Unit)? = null

    // 布局资源ID
    @LayoutRes
    protected abstract fun getLayoutResId(): Int

    // 初始化视图
    protected abstract fun initView(view: View)

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
        setStyle(STYLE_NORMAL, getThemeResId())
    }

    @StyleRes
    protected open fun getThemeResId(): Int = R.style.BaseDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCanceledOnTouchOutside(cancelOnTouchOutside)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWindow()
        initView(view)
        initData()
        initListener()
    }

    private fun initWindow() {
        val window = dialog?.window ?: return

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

            // 沉浸式状态栏支持
            if (isImmersive) {
                setImmersiveMode(this)
            }
        }
    }

    private fun setImmersiveMode(window: Window) {
        // 启用窗口的扩展布局
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 获取窗口的InsetsController
        val controller = WindowCompat.getInsetsController(window, window.decorView)

        // 隐藏状态栏和导航栏
        controller.hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())

        // 设置沉浸式粘性模式
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    override fun onStart() {
        super.onStart()
        // 重新应用窗口属性，确保在某些设备上生效
        dialog?.window?.let { window ->
            window.setLayout(dialogWidth, dialogHeight)
        }
    }

    override fun onResume() {
        super.onResume()
        onDialogShow()
    }

    override fun onPause() {
        super.onPause()
        onDialogHide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onDialogDestroy()
    }

//    /**
//     * 安全显示对话框的方法，检查 FragmentManager 和 Activity 状态
//     */
//    fun showSafely(manager: FragmentManager, tag: String? = null) {
//        if (isAdded || isVisible || isRemoving || isDetached) return
//
//        val activity = activity ?: context as? AppCompatActivity
//        if (activity != null && !activity.isFinishing && !activity.isDestroyed) {
//            try {
//                // 使用 commitNowAllowingStateLoss 避免状态丢失异常
//                manager.beginTransaction().add(this, tag).commitNowAllowingStateLoss()
//            } catch (e: IllegalStateException) {
//                e.printStackTrace()
//            }
//        }
//    }

//    /**
//     * 安全关闭对话框的方法，检查 Activity 和 Fragment 状态
//     */
//    fun dismissSafely() {
//        val activity = activity ?: context as? AppCompatActivity
//        if (activity != null && !activity.isFinishing && !activity.isDestroyed) {
//            try {
//                // 使用 dismissAllowingStateLoss 避免状态丢失异常
//                dismissAllowingStateLoss()
//            } catch (e: IllegalStateException) {
//                e.printStackTrace()
//            }
//        }
//    }


    /**
     * 关键逻辑说明
     * 安全判断：canShowDialog() 确保 Activity 处于有效状态。
     * 延迟显示：若 Activity 未就绪，保存显示请求，在 onFragmentStarted() 回调中执行。
     * 状态管理：通过 pendingShowRequest 和 isDialogDismissed 避免冲突操作。
     * 安全显示对话框的方法，检查 FragmentManager 和 Activity 状态
     */
    override fun show(manager: FragmentManager, tag: String?) {
        if (canShowDialog()) {
            super.show(manager, tag)
            pendingShowRequest = false
            isDialogDismissed = false
            onShowListener?.invoke()
        } else {
            // 保存请求，在 Activity 就绪后执行
            pendingShowRequest = true
            manager.registerFragmentLifecycleCallbacks(lifecycleCallbacks, false)
        }
    }

    /**
     * 安全关闭对话框的方法，检查 Activity 和 Fragment 状态
     */
    override fun dismiss() {
        if (canShowDialog()) {
            super.dismiss()
            isDialogDismissed = true
            onDismissListener?.invoke()
        } else {
            // 标记为已取消，避免后续显示
            isDialogDismissed = true
        }
    }


    private val lifecycleCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            if (f === this@BaseSafeDialogFragment && pendingShowRequest && !isDialogDismissed) {
                fm.beginTransaction().show(this@BaseSafeDialogFragment).commitAllowingStateLoss()
                pendingShowRequest = false
            }
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            if (f === this@BaseSafeDialogFragment) {
                fm.unregisterFragmentLifecycleCallbacks(this)
            }
        }
    }

    private fun canShowDialog(): Boolean {
        return isAdded &&
                activity != null &&
                !requireActivity().isFinishing &&
                !requireActivity().isDestroyed
    }

//    进阶优化 使用协程管理生命周期

//    override fun show(manager: FragmentManager, tag: String?) {
//        if (canShowDialog2()) {
//            super.show(manager, tag)
//        } else {
//            pendingShowRequest = true
////            repeatOnLifecycle(Lifecycle.State.STARTED) {
////                if (pendingShowRequest) {
////                    super.show(manager, tag)
////                    pendingShowRequest = false
////                }
////            }
//            lifecycleScope.launchWhenStarted {
//                if (pendingShowRequest) {
//                    super.show(manager, tag)
//                    pendingShowRequest = false
//                }
//            }
//        }
//    }
//
//    private fun canShowDialog2() =
//        isAdded &&
//                activity?.let { !it.isFinishing && !it.isDestroyed } == true


    /**
     * 设置对话框宽度（像素）
     */
    fun setDialogWidth(width: Int): BaseSafeDialogFragment {
        this.dialogWidth = width
        return this
    }

    /**
     * 设置对话框高度（像素）
     */
    fun setDialogHeight(height: Int): BaseSafeDialogFragment {
        this.dialogHeight = height
        return this
    }

    /**
     * 设置对话框宽度为屏幕宽度的百分比
     */
    fun setDialogWidthPercent(percent: Float): BaseSafeDialogFragment {
        if (percent in 0.1f..1.0f) {
            val context = context ?: return this
//            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//            val displayMetrics = windowManager.defaultDisplay?.metrics ?: return this
//            this.dialogWidth = (displayMetrics.widthPixels * percent).toInt()
            val displayMetrics = getWindowSize(context)
            this.dialogWidth = (displayMetrics.first * percent).toInt()
        }
        return this
    }

    /**
     * 设置对话框高度为屏幕高度的百分比
     */
    fun setDialogHeightPercent(percent: Float): BaseSafeDialogFragment {
        if (percent in 0.1f..1.0f) {
            val context = context ?: return this
//            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
//            val displayMetrics = windowManager.defaultDisplay?.metrics ?: return this
//            this.dialogHeight = (displayMetrics.heightPixels * percent).toInt()

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
    fun setImmersive(immersive: Boolean): BaseSafeDialogFragment {
        isImmersive = immersive
        return this
    }

    /**
     * 设置点击外部区域是否关闭对话框
     */
    fun setCancelOnTouchOutside(cancel: Boolean): BaseSafeDialogFragment {
        cancelOnTouchOutside = cancel
        isCancelable = cancel
        return this
    }

    /**
     * 设置对话框动画
     */
    fun setDialogAnimation(@StyleRes animationResId: Int): BaseSafeDialogFragment {
        dialogAnimation = animationResId
        return this
    }
}