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