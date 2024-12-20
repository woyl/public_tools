package com.woyl.lt_woyl.utils

import kotlin.time.DurationUnit
import kotlin.time.toDuration

/**
 * Description:
 * Author: luyao
 * Date: 2022/8/3 10:13
 */
object TimerCounter {

    private val countMap = hashMapOf<String, Long>()

    fun start(tag: String) {
        countMap[tag] = System.currentTimeMillis()
    }

    fun end(tag: String) {
        countMap[tag]?.let {
            val duration =
                (System.currentTimeMillis() - it).toDuration(DurationUnit.MILLISECONDS).toString()
            countMap.remove(tag)
        }
    }
}