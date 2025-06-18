package com.woyl.my_.ui

abstract class TestIn<in T> {
//    abstract val num3 : @UnsafeVariance T
//    abstract fun getNum3() : @UnsafeVariance T
//
//    abstract var num4 : @UnsafeVariance T
    abstract fun addNum4(t : T)
}