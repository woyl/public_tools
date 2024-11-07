package com.woyl.lt_woyl.view.recyclerview

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.woyl.lt_woyl.utils.ScreenUtils

class RecyclerItemCenterDecoration : ItemDecoration() {
    /**
     * 自定义默认的Item的边距
     */
    private val kkk_mPageMargin_lll = 18 //dp

    /**
     * 第一个item的左边距
     */
    private var kkk_mLeftPageVisibleWidth_lll = 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        // 计算一下第一个item距离屏幕左边的距离：(屏幕的宽度-item的宽度)/2。其中item的宽度=实际ImagView的宽度+margin。

        view.measure(0, 0)
        // 默认值
        var kkk_childViewWidth_lll = CCC_dpToPx_DDD(120)
        if (view.measuredWidth > 0) {
            kkk_childViewWidth_lll = view.measuredWidth
        }
        //获取实际居左距离
        kkk_mLeftPageVisibleWidth_lll =
            (ScreenUtils.getScreenWith(view.context) / 2 - kkk_childViewWidth_lll / 2)
        //获取当前Item的position
        val kkk_position_lll = parent.getChildAdapterPosition(view)
        //获得Item的数量
        val kkk_itemCount_lll = parent.adapter!!.itemCount
        var kkk_leftMagin_lll = 0
        var kkk_rightMagin_lll = 0
        if (kkk_position_lll == 0) {
            if (kkk_mLeftPageVisibleWidth_lll < CCC_dpToPx_DDD(kkk_mPageMargin_lll)) {
                kkk_leftMagin_lll = kkk_mLeftPageVisibleWidth_lll
                kkk_rightMagin_lll = kkk_mLeftPageVisibleWidth_lll
            } else {
                kkk_leftMagin_lll = kkk_mLeftPageVisibleWidth_lll
                kkk_rightMagin_lll = CCC_dpToPx_DDD(kkk_mPageMargin_lll)
            }
        } else if (kkk_position_lll == kkk_itemCount_lll - 1) {
            if (kkk_mLeftPageVisibleWidth_lll < CCC_dpToPx_DDD(kkk_mPageMargin_lll)) {
                kkk_rightMagin_lll = kkk_mLeftPageVisibleWidth_lll
                kkk_leftMagin_lll = kkk_mLeftPageVisibleWidth_lll
            } else {
                kkk_rightMagin_lll = kkk_mLeftPageVisibleWidth_lll
                kkk_leftMagin_lll = CCC_dpToPx_DDD(kkk_mPageMargin_lll)
            }
        } else {
            kkk_leftMagin_lll = CCC_dpToPx_DDD(kkk_mPageMargin_lll)
            kkk_rightMagin_lll = CCC_dpToPx_DDD(kkk_mPageMargin_lll)
        }

        val layoutParams = view.layoutParams as RecyclerView.LayoutParams
        //10,10分别是item到上下的margin
        layoutParams.setMargins(kkk_leftMagin_lll, 10, kkk_rightMagin_lll, 10)
        view.layoutParams = layoutParams
        super.getItemOffsets(outRect, view, parent, state)
    }

    /**
     * dp转换成px
     *
     * @param dp：
     */
    private fun CCC_dpToPx_DDD(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
    }
}