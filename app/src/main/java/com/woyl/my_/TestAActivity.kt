package com.woyl.my_

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.woyl.lt_woyl.utils.ExtUtils.startActivity

class TestAActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_a)
        Log.e("mlt", "onCreate ------TestAActivity")

        findViewById<AppCompatButton>(R.id.button2).setOnClickListener {
            // 模拟内存泄漏
            startActivity<TestBActivity>()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("mlt", "onDestroy --------TestAActivity")
    }
}