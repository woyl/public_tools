package com.woyl.lt_woyl.utils.ExtUtils.SystemUiExt

import android.view.View
import com.woyl.lt_woyl.utils.ExtUtils.handleInset

/**
 * Description:
 * Author: luyao
 * Date: 2023/10/10 13:21
 */
fun View.handleRootInset() {
    handleInset { view, insets ->
        view.setPadding(insets.left, insets.top, insets.right, 0)
    }
}