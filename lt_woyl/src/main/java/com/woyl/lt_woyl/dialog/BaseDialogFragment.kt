package com.woyl.lt_woyl.dialog

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.woyl.lt_woyl.R
import java.util.*

/**
 * 解决方案
1. 该异常表示fragment已经被添加过，通常是因为重复添加fragment导致的，建议调用FragmentTransaction.add方法，先判断fragment.isAdded()。
[解决方案]：以下是参考解决方案：
if (fragment.isAdded()) {
fragmentManager.beginTransaction().show(fragment).commit();
} else {
fragmentManager.beginTransaction().remove(fragment).commit();
frament = new Fragment();
fragmentManager.beginTransaction().add(R.id.layout_frame, fragment).commit();
}

2. 该异常还经常发生在使用DialogFragment的场景下，DialogFragment也是Fragment的一个子类，其show()方法等同于FragmentTransaction.add()方法，dismiss()方法等同于FragmentTransaction.remove()方法。所以发生异常的原因同上。解决方案如下：
if (dialogFragment.isAdded())
dialogFragment.dismiss();
else
dialogFragment.show();
 * */
abstract class BaseDialogFragment : DialogFragment {
    private var isWidth: Boolean
    private var ori: Int
    private var isTranslucent = false
    protected var views: View? = null
    protected var inflater: LayoutInflater? = null

    constructor(isWidth: Boolean, ori: Int) {
        this.isWidth = isWidth
        this.ori = ori
    }

    constructor(isWidth: Boolean, ori: Int, isTranslucent: Boolean) {
        this.isWidth = isWidth
        this.ori = ori
        this.isTranslucent = isTranslucent
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Objects.requireNonNull(this.dialog)?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.inflater = inflater
        initViews()
        return views
    }

    override fun onResume() {
        val window = dialog?.window
        window?.let {
            it.setGravity(ori)
            val layoutParams: ViewGroup.LayoutParams = it.attributes
            if (isWidth) {
                val dm = resources.displayMetrics
                val width = dm.widthPixels
                layoutParams.width = (width * 0.8).toInt()
            }
            it.setWindowAnimations(R.style.popmenu_animation)
        }
        super.onResume()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (isAdded) {
            dismiss()
        } else {
            super.show(manager, tag)
        }
    }

    override fun getContext(): Context? {
        return activity
    }

    fun isShowing() :Boolean{
        return dialog != null && dialog?.isShowing == true
    }

    override fun onStart() {
        super.onStart()

        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        dialog?.window?.attributes?.height?.let {
            dialog?.window?.setLayout(displayMetrics.widthPixels, it)
        }
        if (isTranslucent) {
            setStyle(STYLE_NORMAL, R.style.dialogStyleTranslucent)
        } else {
            setStyle(STYLE_NORMAL, R.style.dialogStyle)
        }
        dialog?.window?.setBackgroundDrawableResource(R.color.transparent)
    }

    protected abstract fun initViews()
}