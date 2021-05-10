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
        return view
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
            if (isTranslucent) {
                setStyle(STYLE_NORMAL, R.style.dialogStyleTranslucent)
            } else {
                setStyle(STYLE_NORMAL, R.style.dialogStyle)
            }
        }
        super.onResume()
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (isAdded) {
            manager.beginTransaction().remove(this).commit()
        }
        super.show(manager, tag)
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
        dialog?.window?.setBackgroundDrawableResource(R.color.transparent)
    }

    protected abstract fun initViews()
}