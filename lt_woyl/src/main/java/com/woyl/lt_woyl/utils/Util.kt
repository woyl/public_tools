package com.woyl.lt_woyl.utils

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.*

object Util {

    fun <T> T.throttle(during: Long = 500L): T {
        return Proxy.newProxyInstance(this!!::class.java.classLoader, this!!::class.java.interfaces,
            object : InvocationHandler {

                private var lastInvokeTime = System.currentTimeMillis()

                override fun invoke(proxy: Any, method: Method, args: Array<out Any>?): Any? {
                    val current = System.currentTimeMillis()
                    return if (current - lastInvokeTime > during) {
                        lastInvokeTime = current;
                        method.invoke(this@throttle, *args.orEmpty())
                    } else {
                        resolveDefaultReturnValue(method)
                    }

                }

            }
        ) as T
    }

    private fun resolveDefaultReturnValue(method: Method): Any? {
        return when (method.returnType.name.lowercase(Locale.US)) {
            Void::class.java.simpleName.lowercase(Locale.US) -> null
            else -> throw IllegalArgumentException("throttle只能作用于无返回值函数")
        }
    }

//    button.setOnClickListener(View.OnClickListener {
//        // 此处的代码会被代理，支持防抖
//    }.throttle())
}