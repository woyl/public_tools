package com.woyl.lt_woyl.utils.ExtUtils


import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import android.annotation.SuppressLint
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.WindowManager
import androidx.fragment.app.Fragment

/**
 * Description:
 * Author:
 * Date: 2022/7/28 11:23
 */

fun Context.snackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun Context.snackBar(view: View, @StringRes resId: Int) {
    Snackbar.make(view, resId, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.toast(message: CharSequence?): Toast =
    requireActivity().toast(message)

fun Fragment.toast(@StringRes message: Int): Toast =
    requireActivity().toast(message)

fun Context.toast(message: CharSequence?): Toast =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).fixBadTokenException().apply { show() }

fun Context.toast(@StringRes message: Int): Toast =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).fixBadTokenException().apply { show() }

fun Fragment.longToast(message: CharSequence?): Toast =
    requireActivity().longToast(message)

fun Fragment.longToast(@StringRes message: Int): Toast =
    requireActivity().longToast(message)

fun Context.longToast(message: CharSequence?): Toast =
    Toast.makeText(this, message, Toast.LENGTH_LONG).fixBadTokenException().apply { show() }

fun Context.longToast(@StringRes message: Int): Toast =
    Toast.makeText(this, message, Toast.LENGTH_LONG).fixBadTokenException().apply { show() }

fun Toast.fixBadTokenException(): Toast = apply {
    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
        try {
            @SuppressLint("DiscouragedPrivateApi")
            val tnField = Toast::class.java.getDeclaredField("mTN")
            tnField.isAccessible = true
            val tn = tnField.get(this)

            val handlerField = tnField.type.getDeclaredField("mHandler")
            handlerField.isAccessible = true
            val handler = handlerField.get(tn) as Handler

            val looper = checkNotNull(Looper.myLooper()) {
                "Can't toast on a thread that has not called Looper.prepare()"
            }
            handlerField.set(tn, object : Handler(looper) {
                override fun handleMessage(msg: Message) {
                    try {
                        handler.handleMessage(msg)
                    } catch (ignored: WindowManager.BadTokenException) {
                    }
                }
            })
        } catch (ignored: IllegalAccessException) {
        } catch (ignored: NoSuchFieldException) {
        }
    }
}