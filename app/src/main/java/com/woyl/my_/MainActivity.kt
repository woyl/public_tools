package com.woyl.my_

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.woyl.lt_woyl.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            ToastUtils.showLongToast(this,"ok")
        }
    }
}
