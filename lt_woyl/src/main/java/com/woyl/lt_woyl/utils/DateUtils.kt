package com.woyl.lt_woyl.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Administrator on 2019/3/8.
 */
object DateUtils {
    /*
  * 将时间转换为时间戳
  */
    @SuppressLint("SimpleDateFormat")
    @Throws(ParseException::class)
    fun dateToStamp(s: String): String {
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = simpleDateFormat.parse(s)
        val ts = date.time
        res = ts.toString()
        return res
    }

    @SuppressLint("SimpleDateFormat")
/*
     * 将时间戳转换为时间
     */
    fun stampToDate(s: String): String {
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val lt = s.toLong()
        val date = Date(lt)
        res = simpleDateFormat.format(date)
        return res
    }

    @SuppressLint("SimpleDateFormat")
/*
     * 将时间戳转换为时间
     */
    fun stampToDate2(l: Long?): String {
        if (l == null) {
            return ""
        }
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = Date(l)
        res = simpleDateFormat.format(date)
        return res
    }

    @SuppressLint("SimpleDateFormat")
/*
    * 将时间戳转换为时间
    */
    fun stampToDate4(l: Long?): String {
        if (l == null) {
            return ""
        }
        val res: String
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val date = Date(l)
        res = simpleDateFormat.format(date)
        return res
    }

    @SuppressLint("SimpleDateFormat")
            /**
             * 将时间转化为时分
             */
    fun stampToDate3(l: Long?): String {
        if (l == null) {
            return ""
        }
        val res: String
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val date = Date(l)
        res = simpleDateFormat.format(date)
        return res
    }

    @SuppressLint("SimpleDateFormat")
    fun stampToDate6(s: String): String {
        val res: String
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        val lt = s.toLong()
        val date = Date(lt)
        res = simpleDateFormat.format(date)
        return res
    }

    @SuppressLint("SimpleDateFormat")
    fun stampToDate5(l: Long?): String {
        if (l == null) {
            return ""
        }
        val res: String
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        val date = Date(l)
        res = simpleDateFormat.format(date)
        return res
    }

    @SuppressLint("SimpleDateFormat")
            /**
             * 将时间转化为时分
             */
    fun stampToDate6(l: Long?): String {
        if (l == null) {
            return ""
        }
        val res: String
        val simpleDateFormat = SimpleDateFormat("HH")
        val date = Date(l)
        res = simpleDateFormat.format(date)
        return res
    }

    //前一个月
    @SuppressLint("SimpleDateFormat")
    fun getMonthAgo(date: Date): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val cander = Calendar.getInstance()
        cander.time = date
        cander.add(Calendar.MONTH, -1)
        val mothage = simpleDateFormat.format(cander.time)
        return mothage
    }

    //获取当天0点
    fun getTimesmorning(): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return (cal.timeInMillis / 1000)
    }

    //获取当天24点
    fun getTimesnight(): Long {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 24)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return (cal.timeInMillis / 1000)
    }

    @SuppressLint("SimpleDateFormat")
//获取当前时间前后前几天 如获取前7天日期则传-7即可；如果后7天则传7
    fun getOldDate(distanceDay: Int): String {
        val dft = SimpleDateFormat("yyyy-MM-dd")
        val beginDate = Date()
        val date = Calendar.getInstance()
        date.time = beginDate
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay)
        var endDate: Date? = null
        try {
            endDate = dft.parse(dft.format(date.time))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return dft.format(endDate)
    }

    @SuppressLint("SimpleDateFormat")
//获取当前时间前后前几天 如获取前7天日期则传-7即可；如果后7天则传7
    fun getOldDateLong(distanceDay: Int): Long {
        val dft = SimpleDateFormat("yyyy-MM-dd")
        val beginDate = Date()
        val date = Calendar.getInstance()
        date.time = beginDate
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay)
        var endDate: Date? = null
        try {
            endDate = dft.parse(dft.format(date.time))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date.time.time
    }

    @SuppressLint("SimpleDateFormat")
//获取当前时间前后前几天 如获取前7天日期则传-7即可；如果后7天则传7
    fun getOldYearDateLong(distanceDay: Int): Long {
        val dft = SimpleDateFormat("yyyy-MM-dd")
        val beginDate = Date()
        val date = Calendar.getInstance()
        date.time = beginDate
        date.set(Calendar.DATE, date.get(Calendar.YEAR) + distanceDay)
        var endDate: Date? = null
        try {
            endDate = dft.parse(dft.format(date.time))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date.time.time
    }

    /**判断时间是否在时间段内*/
    fun belongCalendar(nowTime: Date, beginTime: Date, startTime: Date): Boolean {
        //设置当前时间
        val date = Calendar.getInstance()
        date.time = nowTime
        //设置开始时间
        val start = Calendar.getInstance()
        start.time = beginTime
        //设置结束时间
        val end = Calendar.getInstance()
        end.time = startTime
        //处于开始时间之后，和结束时间之前的判断
        return date.after(start) && date.after(end)
    }

    private const val minute = 60 * 1000 // 1分钟
        .toLong()
    private const val hour = 60 * minute // 1小时

    private const val day = 24 * hour // 1天

    private const val month = 31 * day // 月

    private const val year = 12 * month // 年


    /**
     * 返回文字描述的日期
     *
     * @param
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    @Throws(ParseException::class)
    fun getTimeFormatText(time: String?): String? {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date: Date = format.parse(time) ?: return null
        val diff = Date().time - date.time
        var r: Long = 0
        if (diff > year) {
            r = diff / year
            return r.toString() + "年前"
        }
        if (diff > month) {
            r = diff / month
            return r.toString() + "个月前"
        }
        if (diff > day) {
            r = diff / day
            return r.toString() + "天前"
        }
        if (diff > hour) {
            r = diff / hour
            return r.toString() + "个小时前"
        }
        if (diff > minute) {
            r = diff / minute
            return r.toString() + "分钟前"
        }
        return "刚刚"
    }

    fun stringForTime(timeMs: Long): String? {
        val totalSeconds = timeMs / 1000
        val seconds = totalSeconds % 60
        val minutes = totalSeconds / 60 % 60
        val hours = totalSeconds / 3600
        return Formatter().format("%02d:%02d:%02d", hours, minutes, seconds).toString()
    }
}