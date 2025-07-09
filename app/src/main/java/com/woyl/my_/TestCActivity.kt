package com.woyl.my_

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class TestCActivity : AppCompatActivity(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_c)
        Log.e("mlt", "onCreate -----TestCActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("mlt", "onDestroy-------TestCActivity")
    }
}