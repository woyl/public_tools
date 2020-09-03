package com.woyl.lt_woyl.utils

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


object KeyBoardUtils {

    /**
     * 打开软键盘
     *
     * @param mEditText 输入框
     */
    fun showKeybord(mEditText: EditText?,context: Context) {
        if (mEditText == null) {
            return
        }
        val imm: InputMethodManager = context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
        mEditText.setSelection(mEditText.text.length)
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     */
    fun hideKeybord(mEditText: EditText?,context: Context) {
        if (mEditText == null) {
            return
        }
        val imm: InputMethodManager = context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    /**
     * @param @param editText
     * @param @param action<br></br>
     * EditorInfo.IME_ACTION_SEARCH 搜索 <br></br>
     * EditorInfo.IME_ACTION_SEND 发送<br></br>
     * EditorInfo.IME_ACTION_NEXT 下一步<br></br>
     * EditorInfo.IME_ACTION_DONE 完成<br></br>
     * EditorInfo.IME_ACTION_NEXT 去往<br></br>
     * @return void
     * @Desp: 设置输入法中回车按钮的显示内容
     */
    fun setImeOptions(editText: EditText?, action: Int) {
        if (editText == null) {
            return
        }
        editText.imeOptions = action
    }

    /**
     * 输入框获取焦点
     *
     * @param mEditText
     */
    fun requestFocus(mEditText: EditText?) {
        if (mEditText != null) {
            Handler().postDelayed(Runnable {
                mEditText.requestFocus()
                mEditText.isFocusable = true
                mEditText.isFocusableInTouchMode = true
            }, 50)
        }
    }

    /**
     * 输入框获取焦点弹出输入框
     *
     * @param mEditText
     */
    fun requestShowKeyBord(mEditText: EditText?,context: Context) {
        if (mEditText != null) {
            Handler().postDelayed(Runnable {
                mEditText.requestFocus()
                mEditText.isFocusable = true
                mEditText.isFocusableInTouchMode = true
                showKeybord(mEditText,context)
            }, 50)
        }
    }


    /**
     * EditText获取焦点并显示软键盘
     */
    fun showSoftInputFromWindow(activity: Activity, editText: EditText?) {
//        editText.setFocusable(true);
//        editText.setFocusableInTouchMode(true);
//        editText.requestFocus();
        //显示软键盘
//        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        //如果上面的代码没有弹出软键盘 可以使用下面另一种方式
        val imm: InputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }
}