package com.woyl.lt_woyl.utils.ExtUtils

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Description:
 * Author: luyao
 * Date: 2023/7/20 13:33
 */
//inline fun Fragment.flowWithLifecycle(
//    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
//    crossinline block: suspend CoroutineScope.() -> Unit
//) {
//    viewLifecycleOwner.lifecycleScope.launch {
//        lifecycle.repeatOnLifecycle(minActiveState) {
//            block()
//        }
//    }
//}