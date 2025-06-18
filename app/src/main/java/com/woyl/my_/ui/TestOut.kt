package com.woyl.my_.ui

abstract class TestOut<out T> {
//    abstract val num : T
//    abstract fun getNum() : T

//    abstract var num2 : @UnsafeVariance T
//    abstract fun addNum(t : @UnsafeVariance T)
}

//class TeestOut<T>(t : T) : TestOut<T>() {
//    override val num: T = t
//    override fun getNum(): T = num
////    override var num2: T = t
////    override fun addNum(t: @UnsafeVariance T) {
////        num2 = t
////    }
//
////    override val num: T = t
////
////    override fun getNum(): T {
////       return num
////    }
////
////    override var num2 : T
////        get() = num
////        set(value) {
////            field = value
////        }
////
////    override fun addNum(t: T) {
////        TODO("Not yet implemented")
////    }
//
//}