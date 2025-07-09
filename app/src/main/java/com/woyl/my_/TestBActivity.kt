package com.woyl.my_

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class TestBActivity : AppCompatActivity(){

    var byteArray = ByteArray(1) // 10MB
    var count = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_b)
        Log.e("mlt", "onCreate ------TestBActivity")

        findViewById<AppCompatButton>(R.id.button3).setOnClickListener {
            // 模拟内存泄漏
            byteArray = ByteArray(10 * 1024 * 1024 * count++)
            val runtime = Runtime.getRuntime()
            val maxMemory = runtime.maxMemory()
            val dalvikUserData =runtime.totalMemory() -  runtime.freeMemory()
            Log.e("mlt", "Total Memory: ${maxMemory / (1024 * 1024)} MB, Dalvik User Data: ${dalvikUserData / (1024 * 1024)} MB")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("mlt", "onDestroy -------TestBActivity")
    }
}