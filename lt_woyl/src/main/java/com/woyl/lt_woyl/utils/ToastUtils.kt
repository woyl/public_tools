package com.woyl.lt_woyl.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import java.lang.Exception

object ToastUtils {

    private var toast: Toast? = null
    private var isShow = true

    /**全局控制是否显示Toast*/
    fun controlToastShow(isShowToast: Boolean) {
        isShow = isShowToast
    }

    /**取消Toast显示*/
    fun cancelToastShow() {
        if (isShow && toast != null) {
            toast?.cancel()
        }
    }

    /**短时间显示*/
    @SuppressLint("ShowToast")
    fun showShortToast(context: Context, message: CharSequence?) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            } else {
                //如果当前Toast没有消失， 直接显示内容，不需要重新设置
                toast?.setText(message)
            }
            toast?.show()
        }
    }

    /**
     * 短时间显示
     * 资源ID:getResources().getString(R.string.xxxxxx)
     * @param context
     * @param resId
     * */
    @SuppressLint("ShowToast")
    fun showShortToast(context: Context, resId: Int?) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, resId!!, Toast.LENGTH_SHORT)
            } else {
                toast?.setText(resId!!)
            }
            toast?.show()
        }
    }

    /**长时间显示*/
    @SuppressLint("ShowToast")
    fun showLongToast(context: Context, message: String?) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            } else {
                toast?.setText(message)
            }
            toast?.show()
        }
    }

    /**
     * 长时间显示
     * @param context
     * @param resId 资源ID:getResources().getString(R.string.xxxxxx)
     * */
    @SuppressLint("ShowToast")
    fun showLongToast(context: Context, resId: Int?) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, resId!!, Toast.LENGTH_LONG)
            } else {
                toast?.setText(resId!!)
            }
            toast?.show()
        }
    }

    /**
     * 自定义显示Toast时间
     * @param context
     * @param resId
     * @param duration
     * */
    @SuppressLint("ShowToast")
    fun showTimeToast(context: Context, resId: Int?, duration: Int) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, resId!!, duration)
            } else {
                toast?.setText(resId!!)
            }
            toast?.show()
        }
    }

    /**
     * 自定义显示Toast时间
     * @param context
     * @param message
     * @param duration
     * */
    @SuppressLint("ShowToast")
    fun showTimeToast(context: Context, message: CharSequence?, duration: Int) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message!!, duration)
            } else {
                toast?.setText(message!!)
            }
            toast?.show()
        }
    }

    /**自定义Toast的View
     *@param context
     * @param message
     * @param duration
     * @param view
     *
     * */
    @SuppressLint("ShowToast")
    fun showToastView(context: Context, message: CharSequence?, duration: Int, view: View?) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message!!, duration)
            } else {
                toast?.setText(message!!)
            }
            if (view != null) {
                toast?.view = view
            }
            toast?.show()
        }
    }


    /**自定义Toast的位置
     *@param context
     * @param message
     * @param duration
     * @param
     *
     * */
    @SuppressLint("ShowToast")
    fun showToastGravity(
        context: Context,
        message: CharSequence?,
        duration: Int,
        gravity: Int?,
        xOffset: Int?,
        yOffset: Int?
    ) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message!!, duration)
            } else {
                toast?.setText(message!!)
            }
            toast?.setGravity(gravity!!, xOffset!!, yOffset!!)
            toast?.show()
        }
    }

    /**
     * 自定义带图片和文字的Toast，最终的效果就是上面是图片，下面是文字
     * @param context
     * @param message
     * @param iconResId 图片的资源id,如:R.drawable.icon
     * @param duration
     * @param gravity
     * @param xOffset
     * @param yOffset
     */
    @SuppressLint("ShowToast")
    fun showToastWithImageAndText(
        context: Context,
        message: CharSequence,
        iconResId: Int,
        duration: Int,
        gravity: Int,
        xOffset: Int,
        yOffset: Int
    ) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, duration)
            } else {
                toast?.setText(message)
            }
            toast?.setGravity(gravity, xOffset, yOffset)
            val toastView = toast?.view as LinearLayout
            val imageView = ImageView(context)
            imageView.setImageResource(iconResId)
            toastView.addView(imageView, 0)
            toast?.show()
        }
    }

    /**
     * 自定义Toast,针对类型CharSequence
     * @param context
     * @param message
     * @param duration
     * @param view
     * @param isGravity true,表示后面的三个布局参数生效,false,表示不生效
     * @param gravity
     * @param xOffset
     * @param yOffset
     * @param isMargin true,表示后面的两个参数生效，false,表示不生效
     * @param horizontalMargin
     * @param verticalMargin
     */
    @SuppressLint("ShowToast")
    fun customToastAll(
        context: Context,
        message: CharSequence,
        duration: Int,
        view: View?,
        isGravity: Boolean,
        gravity: Int,
        xOffset: Int,
        yOffset: Int,
        isMargin: Boolean,
        horizontalMargin: Float,
        verticalMargin: Float
    ) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, message, duration)
            } else {
                toast?.setText(message)
            }
            if (view != null) {
                toast?.view = view
            }
            if (isMargin) {
                toast?.setMargin(horizontalMargin, verticalMargin)
            }
            if (isGravity) {
                toast?.setGravity(gravity, xOffset, yOffset)
            }
            toast?.show()
        }
    }

    /**
     * 自定义Toast,针对类型resId
     * @param context
     * @param resId
     * @param duration
     * @param view :应该是一个布局，布局中包含了自己设置好的内容
     * @param isGravity true,表示后面的三个布局参数生效,false,表示不生效
     * @param gravity
     * @param xOffset
     * @param yOffset
     * @param isMargin true,表示后面的两个参数生效，false,表示不生效
     * @param horizontalMargin
     * @param verticalMargin
     */
    @SuppressLint("ShowToast")
    fun customToastAll(
        context: Context,
        resId: Int,
        duration: Int,
        view: View?,
        isGravity: Boolean,
        gravity: Int,
        xOffset: Int,
        yOffset: Int,
        isMargin: Boolean,
        horizontalMargin: Float,
        verticalMargin: Float
    ) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, resId, duration)
            } else {
                toast?.setText(resId)
            }
            if (view != null) {
                toast?.view = view
            }
            if (isMargin) {
                toast?.setMargin(horizontalMargin, verticalMargin)
            }
            if (isGravity) {
                toast?.setGravity(gravity, xOffset, yOffset)
            }
            toast?.show()
        }
    }
}