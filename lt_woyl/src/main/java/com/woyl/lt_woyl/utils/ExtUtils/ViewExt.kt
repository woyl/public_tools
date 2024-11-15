package com.woyl.lt_woyl.utils.ExtUtils

import android.view.View
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.TypedValue
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import java.io.File

import android.content.res.TypedArray
import android.graphics.Outline
import android.graphics.Rect
import android.util.AttributeSet
import android.view.*
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.annotation.StyleableRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.woyl.lt_woyl.R
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun dp2px(dp: Int) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    Resources.getSystem().displayMetrics
)

fun dp2px(dp: Float) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp,
    Resources.getSystem().displayMetrics
)

const val doubleClickInterval = 1000
var clickCount = 0
var lastClick = 0L
fun View.setOnDoubleClick(doubleClick: () -> Unit, showNote: () -> Unit) {
    setOnClickListener {
        if (clickCount == 0) {
            showNote()
            lastClick = System.currentTimeMillis()
            clickCount++
        } else {
            if (System.currentTimeMillis() - lastClick > doubleClickInterval) {
                showNote()
                lastClick = System.currentTimeMillis()
                clickCount++
            } else {
                doubleClick()
                clickCount = 0
                lastClick = 0L
            }
        }
    }
}

fun View.updateMargin(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) {
    (layoutParams as? ViewGroup.MarginLayoutParams)?.let {
        updateLayoutParams<ViewGroup.MarginLayoutParams> {
            left?.let { marginStart = left }
            right?.let { marginEnd = right }
            top?.let { topMargin = top }
            bottom?.let { bottomMargin = bottom }
        }
    }
}

fun ViewTreeObserver.addDisposableOnGlobalLayoutListener(listener: ViewTreeObserver.OnGlobalLayoutListener) {
    addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            removeOnGlobalLayoutListener(this)
            listener.onGlobalLayout()
        }
    })
}

fun View.createBitmap(bgColor: Int) : Bitmap {
    setBackgroundColor(bgColor)
    val bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
    this.draw(Canvas(bitmap))
    return bitmap
}

fun Bitmap.saveToLocal(file: File) {
    file.outputStream().buffered().use {
        compress(Bitmap.CompressFormat.JPEG, 100, it)
    }
}


private var View.lastClickTime: Long? by viewTags(R.id.tag_last_click_time)
var debouncingClickIntervals = 500

@Deprecated("Use doOnDebouncingClick instead", ReplaceWith("doOnDebouncingClick(clickIntervals, isSharingIntervals, block)"))
fun List<View>.doOnClick(clickIntervals: Int, isSharingIntervals: Boolean = false, block: () -> Unit) =
    doOnDebouncingClick(clickIntervals, isSharingIntervals, block)

@Deprecated("Use doOnDebouncingClick instead", ReplaceWith("doOnDebouncingClick(clickIntervals, isSharingIntervals, block)"))
fun View.doOnClick(clickIntervals: Int, isSharingIntervals: Boolean = false, block: () -> Unit) =
    doOnDebouncingClick(clickIntervals, isSharingIntervals, block)

fun List<View>.doOnDebouncingClick(clickIntervals: Int = debouncingClickIntervals, isSharingIntervals: Boolean = false, block: () -> Unit) =
    forEach { it.doOnDebouncingClick(clickIntervals, isSharingIntervals, block) }

fun View.doOnDebouncingClick(clickIntervals: Int = debouncingClickIntervals, isSharingIntervals: Boolean = false, block: () -> Unit) =
    setOnClickListener {
        val view = if (isSharingIntervals) context.asActivity()?.window?.decorView ?: this else this
        val currentTime = System.currentTimeMillis()
        if (currentTime - (view.lastClickTime ?: 0L) > clickIntervals) {
            view.lastClickTime = currentTime
            block()
        }
    }

inline fun List<View>.doOnClick(crossinline block: () -> Unit) = forEach { it.doOnClick(block) }

inline fun View.doOnClick(crossinline block: () -> Unit) = setOnClickListener { block() }

@Deprecated("Use doOnDebouncingLongClick instead", ReplaceWith("doOnDebouncingLongClick(clickIntervals, isSharingIntervals, block)"))
fun List<View>.doOnLongClick(clickIntervals: Int, isSharingIntervals: Boolean = false, block: () -> Unit) =
    doOnDebouncingLongClick(clickIntervals, isSharingIntervals, block)

@Deprecated("Use doOnDebouncingLongClick instead", ReplaceWith("doOnDebouncingLongClick(clickIntervals, isSharingIntervals, block)"))
fun View.doOnLongClick(clickIntervals: Int, isSharingIntervals: Boolean = false, block: () -> Unit) =
    doOnDebouncingLongClick(clickIntervals, isSharingIntervals, block)

fun List<View>.doOnDebouncingLongClick(clickIntervals: Int = debouncingClickIntervals, isSharingIntervals: Boolean = false, block: () -> Unit) =
    forEach { it.doOnDebouncingLongClick(clickIntervals, isSharingIntervals, block) }

fun View.doOnDebouncingLongClick(clickIntervals: Int = debouncingClickIntervals, isSharingIntervals: Boolean = false, block: () -> Unit) =
    doOnLongClick {
        val view = if (isSharingIntervals) context.asActivity()?.window?.decorView ?: this else this
        val currentTime = System.currentTimeMillis()
        if (currentTime - (view.lastClickTime ?: 0L) > clickIntervals) {
            view.lastClickTime = currentTime
            block()
        }
    }

inline fun List<View>.doOnLongClick(crossinline block: () -> Unit) = forEach { it.doOnLongClick(block) }

inline fun View.doOnLongClick(crossinline block: () -> Unit) =
    setOnLongClickListener {
        block()
        true
    }

fun View.expandClickArea(expandSize: Float) = expandClickArea(expandSize.toInt())

fun View.expandClickArea(expandSize: Int) =
    expandClickArea(expandSize, expandSize, expandSize, expandSize)

fun View.expandClickArea(top: Float, left: Float, right: Float, bottom: Float) =
    expandClickArea(top.toInt(), left.toInt(), right.toInt(), bottom.toInt())

fun View.expandClickArea(top: Int, left: Int, right: Int, bottom: Int) {
    val parent = parent as? ViewGroup ?: return
    parent.post {
        val rect = Rect()
        getHitRect(rect)
        rect.top -= top
        rect.left -= left
        rect.right += right
        rect.bottom += bottom
        val touchDelegate = parent.touchDelegate
        if (touchDelegate == null || touchDelegate !is MultiTouchDelegate) {
            parent.touchDelegate = MultiTouchDelegate(rect, this)
        } else {
            touchDelegate.put(rect, this)
        }
    }
}

var View.roundCorners: Float
    @Deprecated(NO_GETTER, level = DeprecationLevel.ERROR)
    get() = noGetter()
    set(value) {
        clipToOutline = true
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                outline.setRoundRect(0, 0, view.width, view.height, value)
            }
        }
    }

fun View?.isTouchedAt(x: Float, y: Float): Boolean =
    isTouchedAt(x.toInt(), y.toInt())

fun View?.isTouchedAt(x: Int, y: Int): Boolean =
    this?.locationOnScreen?.contains(x, y) == true

fun View.findTouchedChild(x: Float, y: Float): View? =
    findTouchedChild(x.toInt(), y.toInt())

fun View.findTouchedChild(x: Int, y: Int): View? =
    touchables.find { it.isTouchedAt(x, y) }

/**
 * Computes the coordinates of this view on the screen.
 */
inline val View.locationOnScreen: Rect
    get() = IntArray(2).let {
        getLocationOnScreen(it)
        Rect(it[0], it[1], it[0] + width, it[1] + height)
    }

@Deprecated(
    "Replace with new api",
    replaceWith = ReplaceWith("withStyledAttributes(set, attrs, defStyleAttr, defStyleRes, block)")
)
inline fun View.withStyledAttrs(
    set: AttributeSet?,
    @StyleableRes attrs: IntArray,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
    block: TypedArray.() -> Unit
) =
    context.withStyledAttributes(set, attrs, defStyleAttr, defStyleRes, block)

inline fun View.withStyledAttributes(
    set: AttributeSet?,
    @StyleableRes attrs: IntArray,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
    block: TypedArray.() -> Unit
) =
    context.withStyledAttributes(set, attrs, defStyleAttr, defStyleRes, block)

val View.rootWindowInsetsCompat: WindowInsetsCompat? by viewTags(R.id.tag_root_window_insets) {
    ViewCompat.getRootWindowInsets(this)
}

val View.windowInsetsControllerCompat: WindowInsetsControllerCompat? by viewTags(R.id.tag_window_insets_controller) {
    ViewCompat.getWindowInsetsController(this)
}

fun View.doOnApplyWindowInsets(action: (View, WindowInsetsCompat) -> WindowInsetsCompat) =
    ViewCompat.setOnApplyWindowInsetsListener(this, action)

fun <T> viewTags(key: Int) = object : ReadWriteProperty<View, T?> {
    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: View, property: KProperty<*>) =
        thisRef.getTag(key) as? T

    override fun setValue(thisRef: View, property: KProperty<*>, value: T?) =
        thisRef.setTag(key, value)
}

@Suppress("UNCHECKED_CAST")
fun <T> viewTags(key: Int, block: View.() -> T) = ReadOnlyProperty<View, T> { thisRef, _ ->
    if (thisRef.getTag(key) == null) {
        thisRef.setTag(key, block(thisRef))
    }
    thisRef.getTag(key) as T
}

class MultiTouchDelegate(bound: Rect, delegateView: View) : TouchDelegate(bound, delegateView) {
    private val map = mutableMapOf<View, Pair<Rect, TouchDelegate>>()
    private var targetDelegate: TouchDelegate? = null

    init {
        put(bound, delegateView)
    }

    fun put(bound: Rect, delegateView: View) {
        map[delegateView] = bound to TouchDelegate(bound, delegateView)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                targetDelegate = map.entries.find { it.value.first.contains(x, y) }?.value?.second
            }
            MotionEvent.ACTION_CANCEL -> {
                targetDelegate = null
            }
        }
        return targetDelegate?.onTouchEvent(event) ?: false
    }

    /**
     * 扩展方法，扩大点击区域
     * NOTE: 需要保证目标targetView有父View，否则无法扩大点击区域
     *
     * @param expandSize 扩大的大小，单位px
     */
    fun View.expandTouchView(expandSize: Int = 10.dpToPx()) {
        val parentView = (parent as? View)
        parentView?.post {
            val rect = Rect()
            getHitRect(rect) //getHitRect(rect)将视图在父容器中所占据的区域存储到rect中。
            rect.left -= expandSize
            rect.top -= expandSize
            rect.right += expandSize
            rect.bottom += expandSize
            parentView.touchDelegate = TouchDelegate(rect, this)
        }
    }
}