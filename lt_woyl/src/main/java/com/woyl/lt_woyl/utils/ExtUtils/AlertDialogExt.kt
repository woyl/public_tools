package com.woyl.lt_woyl.utils.ExtUtils

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.woyl.lt_woyl.R
import com.woyl.lt_woyl.utils.PixelUtils

//https://mp.weixin.qq.com/s/zmOt6UoaezuksBEeTl43VA
lateinit var mDialog: AlertDialog
var TextView.textColor: Int
    get() {
        return this.textColors.defaultColor
    }
    set(value) {
        this.setTextColor(value)
    }

fun createDialog(ctx: Context, body: AlertDialog.Builder.() -> Unit) {
    val dialog = AlertDialog.Builder(ctx)
    dialog.body()
}

@RequiresApi(Build.VERSION_CODES.M)
inline fun AlertDialog.Builder.rootLayout(
    root: View,
    render: GradientDrawable.() -> Unit = {},
    job: View.() -> Unit
) {
    with(GradientDrawable()) {
        //默认样式
        render()
        root.background = this
    }
    root.setPadding(
        PixelUtils.dp2px(application, 10f),
        PixelUtils.dp2px(application, 10f),
        PixelUtils.dp2px(application, 10f),
        PixelUtils.dp2px(application, 10f)
    )
    root.job()
    mDialog = setView(root).create()
    mDialog.showDialog()
}

inline fun View.title(dialog_title:Int,titleJob: TextView.() -> Unit) {
    val title = findViewById<TextView>(dialog_title)
    //可以加一些标题的默认操作，比如字体颜色，字体大小
    title.titleJob()
}

inline fun View.message(dialog_message:Int,messageJob: TextView.() -> Unit) {
    val message = findViewById<TextView>(dialog_message)
    //可以加一些内容的默认操作，比如字体颜色，字体大小，居左对齐还是居中对齐
    message.messageJob()
}

inline fun View.negativeBtn(dialog_negative_btn_text:Int,negativeJob: TextView.() -> Unit) {
    val negativeBtn = findViewById<TextView>(dialog_negative_btn_text)
    negativeBtn.visibility = View.VISIBLE
    negativeBtn.negativeJob()
}

inline fun View.positiveBtn(dialog_positive_btn_text:Int,positiveJob: TextView.() -> Unit) {
    val positiveBtn = findViewById<TextView>(dialog_positive_btn_text)
    positiveBtn.positiveJob()
}

inline fun TextView.clickEvent(crossinline event: () -> Unit) {
    setOnClickListener {
        mDialog.dismiss()
        event()
    }
}

fun AlertDialog.showDialog() {
    show()
    val mWindow = window
    mWindow?.setBackgroundDrawableResource(R.color.transparent)
    val group: ViewGroup = mWindow?.decorView as ViewGroup
    val child: ViewGroup = group.getChildAt(0) as ViewGroup
    child.post {
        val param: WindowManager.LayoutParams? = mWindow.attributes
        param?.width = (screenWidth * 0.8).toInt()
        param?.gravity = Gravity.CENTER
        mWindow.setGravity(Gravity.CENTER)
        mWindow.attributes = param
    }
}