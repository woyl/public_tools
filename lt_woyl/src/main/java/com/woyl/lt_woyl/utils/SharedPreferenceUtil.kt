package com.woyl.lt_woyl.utils

import android.annotation.SuppressLint
import android.content.Context

class SharedPreferenceUtil private constructor(){
    companion object {
        const val FILE_NAME = "l_sharepreferce"
        val mInstance : SharedPreferenceUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SharedPreferenceUtil()
        }
    }

    /**
     * 存入键值对
     *
     */
    @SuppressLint("CommitPrefEdits")
    fun put(context: Context?, key :String, value: Any){
        //判断类型
//        val type = value.javaClass.simpleName
        val sharedPreferences = context?.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        when (value){
            is Int -> editor?.putInt(key,value)
            is Boolean -> editor?.putBoolean(key,value)
            is Long -> editor?.putLong(key,value)
            is Float -> editor?.putFloat(key,value)
            is String -> editor?.putString(key,value)
            else -> throw IllegalArgumentException("This type of data cannot be saved!")
        }?.apply()
    }

    /**
     *
     * 读取数据
     */
    fun get(context: Context?, key :String?, value: Any?) : Any?{
        val sharedPreferences = context?.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
        return when (value){
            is Int ->   sharedPreferences?.getInt(key,value)
            is Boolean ->  sharedPreferences?.getBoolean(key,value)
            is Long ->  sharedPreferences?.getLong(key,value)
            is Float ->  sharedPreferences?.getFloat(key,value)
            is String ->  sharedPreferences?.getString(key,value)
            else -> throw IllegalArgumentException("This type of data cannot be saved!")
        }
    }

}