package com.woyl.lt_woyl.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

object TimeUtil {

    fun secondConvertHourMinSecond(kkk_second_lll: Long?): String {
        var str = "00:00:00"
        if (kkk_second_lll == null || kkk_second_lll < 0) {
            return str
        }

        // 得到小时
        val h = kkk_second_lll / 3600
        str = if (h > 0) {
            if (h > 0) (if (h < 10) "0$h" else h).toString() + ":" else "00:"
        } else {
            ""
        }

        // 得到分钟
        val m = kkk_second_lll % 3600 / 60
        str += (if (m < 10) "0$m" else m).toString() + ":"

        //得到剩余秒
        val s = kkk_second_lll % 60
        str += if (s < 10) "0$s" else s
        return str
    }


    fun secondConvertMinSecond(second: Long?): String {
        var str = "00:00"
        if (second == null || second < 0) {
            return str
        }

        val d = second / (3600 * 24)
        str = if (d > 0) {
            if (d > 0) (if (d < 10) "0$d" else d).toString() + ":" else "00:"
        } else {
            ""
        }

        // 小时
        val h = second % (3600 * 24) / 3600
        str += if (h > 0) {
            if (h > 0) (if (h < 10) "0$h" else h).toString() + ":" else "00:"
        } else {
            ""
        }

        // 分钟
        val m = second % 3600 / 60
        str += (if (m < 10) "0$m" else m).toString() + ":"

        //剩余秒
        val s = second % 60
        str += if (s < 10) "0$s" else s
        return str
    }

    /**
     * 进一法获取天数
     * @param kkk_time_lll 时间秒
     */
    fun getDay(kkk_time_lll: Int): Int {
        var kkk_day_lll = 0

        if (kkk_time_lll % 86400 == 0) {
            kkk_day_lll = TimeUnit.SECONDS.toDays(kkk_time_lll.toLong()).toInt()
        } else {
            kkk_day_lll = TimeUnit.SECONDS.toDays(kkk_time_lll.toLong()).toInt()
            kkk_day_lll += 1
        }
        return kkk_day_lll
    }


    /**
     * 判断剩余时间是否小于24小时
     */
    fun getlastOneDay(kkk_time_lll: Int): Boolean {
        if (kkk_time_lll < 86400) return true
        return false
    }


    /**
     * 调此方法输入所要转换的时间输入例如（"2014-06-14-16-09-00"）返回时间戳
     *
     * @param time
     * @return
     */
    fun dataOne(time: String?): Long {
        val sdr = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.CHINA
        )
        val date: Date
        var ltime: Long = 0
        try {
            date = sdr.parse(time)
            ltime = date.time
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ltime
    }


    /**
     * 获取当天0点的时间戳
     * */
    fun getStartTime(): Long {
        val kkk_calendar_lll: Calendar = Calendar.getInstance()
        kkk_calendar_lll.set(Calendar.HOUR_OF_DAY, 0)
        kkk_calendar_lll.set(Calendar.MINUTE, 0)
        kkk_calendar_lll.set(Calendar.SECOND, 0)
        kkk_calendar_lll.set(Calendar.MILLISECOND, 0)

//        return kkk_calendar_lll.timeInMillis / 1000
        return kkk_calendar_lll.timeInMillis
    }

    /**
     * 获取当天23：59:59时间戳
     * */
    fun getEndTime(): Long {
        val kkk_calendar_lll: Calendar = Calendar.getInstance()
        kkk_calendar_lll.set(Calendar.HOUR_OF_DAY, 23)
        kkk_calendar_lll.set(Calendar.MINUTE, 59)
        kkk_calendar_lll.set(Calendar.SECOND, 59)
        kkk_calendar_lll.set(Calendar.MILLISECOND, 999)

//        return kkk_calendar_lll.timeInMillis / 1000
        return kkk_calendar_lll.timeInMillis
    }

    /**
     * 时间戳转yyyy.MM.dd  hh:mm
     *
     * @param times
     * @return
     */
    fun toDateYmdHan3(times: Long): String? {
        val format = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.CHINA
        )
        format.timeZone = TimeZone.getTimeZone("GMT+8")
        return format.format(times)
    }

    /**
     * 时间转换 MM/dd-yyyy
     * */
    fun toDateMMddYYYY(times: Long): String? {
        val format = SimpleDateFormat(
            "MM/dd-yyyy",
            Locale.CHINA
        )
        return format.format(times)
    }

    /**
     * 得到分
     * */
    fun secondConvertGetMinSecond(kkk_second_lll: Long?): String {
        var str = ""
        if (kkk_second_lll == null || kkk_second_lll < 0) {
            return str
        }

        // 得到分钟
        val m = kkk_second_lll % 3600 / 60
        str += (if (m < 10) "0$m" else m).toString()
        return str
    }

    /**
     * 得到s
     * */
    fun secondConvertGetSecondSecond(kkk_second_lll: Long?): String {
        var str = ""
        if (kkk_second_lll == null || kkk_second_lll < 0) {
            return str
        }

        //得到剩余秒
        val s = kkk_second_lll % 60
        str += if (s < 10) "0$s" else s
        return str
    }
}