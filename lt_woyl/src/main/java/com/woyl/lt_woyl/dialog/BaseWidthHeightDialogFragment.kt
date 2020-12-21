package com.woyl.lt_woyl.dialog

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.woyl.lt_woyl.R
import java.util.*

abstract class BaseWidthHeightDialogFragment :DialogFragment {

    private var withLeft = 0
    private var withRight = 0
    private var heightTop = 0
    private var heigthButtom = 0
    private var orientation = 0

    var views :View ?= null
    var inflater: LayoutInflater?=null

    constructor(withLeft: Int, withRight: Int, heightTop: Int, heigthButtom: Int) : super() {
        this.withLeft = withLeft
        this.withRight = withRight
        this.heightTop = heightTop
        this.heigthButtom = heigthButtom
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.inflater = inflater
        initViews()

        return views
    }

    override fun onResume() {
        val window = dialog?.window
        window?.setGravity(orientation)
        val dm = resources.displayMetrics
        val layoutParms = window?.attributes
        val with = dm.widthPixels
        val height =  dm.heightPixels
        layoutParms?.width = with - withLeft - withRight
        layoutParms?.height = height - heightTop - heigthButtom
        window?.setWindowAnimations(R.style.popmenu_animation)
        setStyle(STYLE_NORMAL, R.style.dialogStyle)
        super.onResume()
    }

    override fun getContext(): Context? {
        return activity
    }

    override fun onStart() {
        super.onStart()
        val displayMetrics = DisplayMetrics()
        Objects.requireNonNull(activity)?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        Objects.requireNonNull(dialog?.window)?.attributes?.height?.let {
            Objects.requireNonNull(
                Objects.requireNonNull(dialog)?.window
            )?.setLayout(
                displayMetrics.widthPixels,
                it
            )
        }
        dialog?.window?.setBackgroundDrawableResource(R.color.transparent)
    }


    abstract fun initViews()
}