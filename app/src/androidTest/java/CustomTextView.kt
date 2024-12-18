import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import com.woyl.lt_woyl.utils.ExtUtils.countUppercaseLetters


class CustomTextView : AppCompatTextView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, style: Int) : super(context, attrs, style)

    // 创建画笔对象
    private var mPaint: Paint? = null

    // 文本内容、字体大小和颜色的默认值
    private var mTextColor: Int = Color.WHITE
    private var mTextSize = sp2px(15)


    init {
        // 在这里可以添加自定义的初始化代码
        mPaint = Paint().apply {
            isAntiAlias = true  // 启用抗锯齿
            setColor(mTextColor) // 设置文本颜色
            textSize = mTextSize.toFloat() // 设置文本大小
        }

    }

    private fun sp2px(sp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, sp.toFloat(),
            resources.displayMetrics
        ).toInt()
    }

    // 添加其他自定义方法和属性
    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)


        Log.e(
            "mlt", "font bottom:" + getPaint().getFontMetricsInt().bottom +
                    "  \ndescent:" + getPaint().getFontMetricsInt().descent +
                    " \nascent:" + getPaint().getFontMetricsInt().ascent +
                    " \ntop:" + getPaint().getFontMetricsInt().top +
                    " \nbaseline:" + getBaseline()
        )

        /**
         * TextView组件的属性
         */
        Log.e(
            "mlt", "textview bottom:" + getBottom() +
                    " \ntop:" + getTop() +
                    " \nbaseline:" + getBaseline() +
                    " \nheight:" + height
        )


        val kkk_content_lll = text.toString()
        Log.e("mlt", ".....${kkk_content_lll}......")
        val contextWidth = mPaint?.measureText(kkk_content_lll)
        val withM = measuredWidth
        Log.e(
            "mlt",
            "...contextWidth..${contextWidth}..withM..${withM}.........${measuredHeight}.....${height}........"
        )

        mPaint?.let {
            val xx = 0f
            val yy =
                height / 2 + (it.getFontMetricsInt().bottom - it.getFontMetricsInt().top) / 2 - it.getFontMetricsInt().bottom.toFloat()
            if (withM > 0 && contextWidth != null) {
                val line = contextWidth / withM
                if (line > 1) {
                    // 一行可以容纳几个字
                    val num = Math.round(withM / textSize)
                    Log.e("mlt", "..num...${num}...xx..${xx}...yy..${yy}....textSize.textSizetextSize ${textSize}.....")
                    if (num > 0) {
                        val title = kkk_content_lll.substring(0, num)
                        val sss = title.countUppercaseLetters()
                        Log.e(
                            "mlt",
                            "......title.....${title}.........................sss...${sss}......"
                        )
                        val title2: String?
                        if (sss >= 5) {
                            title2 = kkk_content_lll.substring(0, num - 1)
                            canvas.drawText(title2.trim() + "...", xx, yy, it)
                        } else {
                            val str = title.count { it == ' ' }
                            Log.e(
                                "mlt",
                                "......strstrstrstrstrstr.....${str}"
                            )
                            if (str > 0) {
                                title2 = kkk_content_lll.substring(0, num + str)
                            } else {
                                title2 = kkk_content_lll.substring(0, num)
                            }

                            canvas.drawText(title2.trim() + "...", xx, yy, it)
                        }
                    } else {
                        ellipsize = TextUtils.TruncateAt.END
                        canvas.drawText(kkk_content_lll, xx, yy, it)
                    }
                } else {
                    ellipsize = TextUtils.TruncateAt.END
                    canvas.drawText(kkk_content_lll, xx, yy, it)
                }
            } else {
                ellipsize = TextUtils.TruncateAt.END
                canvas.drawText(kkk_content_lll, xx, yy, it)
            }
        }

    }
}