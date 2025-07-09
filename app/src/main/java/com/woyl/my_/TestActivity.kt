package com.woyl.my_

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.woyl.lt_woyl.utils.ExtUtils.startActivity
import java.lang.ref.WeakReference


class TestActivity : AppCompatActivity() {
    fun test() {
        // 使用静态内部类和弱引用
        val thread = Thread(MyRunnable(WeakReference(this)))
        thread.start()
    }

    companion object {
        class MyRunnable(private val activityRef: WeakReference<TestActivity>) : Runnable {
            override fun run() {
                val activity = activityRef.get() ?: return
                // 执行操作，确保在 activity 有效时
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        findViewById<AppCompatButton>(R.id.button).setOnClickListener {
            startActivity<TestAActivity>()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 停止线程或取消任务
    }
}