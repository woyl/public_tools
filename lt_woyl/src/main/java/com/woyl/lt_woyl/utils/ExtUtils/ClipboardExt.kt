package com.woyl.lt_woyl.utils.ExtUtils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.core.content.getSystemService
import android.content.ClipboardManager.OnPrimaryClipChangedListener
import android.content.Intent
import android.net.Uri

fun Context.copyToClipboard(text: String) {
    val service = getSystemService<ClipboardManager>()
    service?.setPrimaryClip(ClipData.newPlainText("text", text))
}

fun Context.getClipboardText(): String {
    val clipboard = getSystemService<ClipboardManager>() ?: return ""
    if (!clipboard.hasPrimaryClip()) return ""
    return clipboard.primaryClip?.getItemAt(0)?.text.toString()
}

fun CharSequence.copyToClipboard(label: CharSequence? = null) =
    (application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
        .setPrimaryClip(ClipData.newPlainText(label, this))

//fun Uri.copyToClipboard(label: CharSequence? = null) =
//    (application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
//        .setPrimaryClip(ClipData.newUri(contentResolver, label, this))

fun Intent.copyToClipboard(label: CharSequence? = null) =
    (application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
        .setPrimaryClip(ClipData.newIntent(label, this))

fun getTextFromClipboard(): CharSequence? =
    (application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
        .primaryClip?.takeIf { it.itemCount > 0 }?.getItemAt(0)?.coerceToText(application)

fun clearClipboard() =
    (application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
        .setPrimaryClip(ClipData.newPlainText(null, ""))

fun doOnClipboardChanged(listener: OnPrimaryClipChangedListener): OnPrimaryClipChangedListener =
    (application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
        .addPrimaryClipChangedListener(listener).let { listener }

fun OnPrimaryClipChangedListener.cancel() =
    (application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager)
        .removePrimaryClipChangedListener(this)