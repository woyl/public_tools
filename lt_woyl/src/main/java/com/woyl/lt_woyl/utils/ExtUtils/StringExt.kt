package com.woyl.lt_woyl.utils.ExtUtils

import android.util.Base64
import java.util.regex.Matcher
import java.util.regex.Pattern
import android.graphics.Color
import android.text.format.Formatter
import androidx.core.util.PatternsCompat
import org.json.JSONObject
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

fun String.isNumeric(): Boolean {
    return this.all { it.isDigit() }
}

fun String.isWebUrlRegex(): Boolean {
    val regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"
    val pattern: Pattern = Pattern.compile(regex)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.base64() = Base64.encodeToString(toByteArray(), Base64.DEFAULT)
fun String.deCodeBase64() = Base64.decode(this, Base64.DEFAULT)

/**
 * Regex of exact phone number. Update at 2021.05.13.
 * - China mobile: 134,135,136,137,138,139,147,148,150,151,152,157,158,159,172,178,182,183,184,187,188,195,198
 * - China unicom: 130,131,132,145,146,155,156,166,175,176,185,186,196
 * - China telecom: 133,149,153,173,174,177,180,181,189,191,193,199
 * - China nrta: 190,192,197
 * - China mobile virtual: 165,1703,1705,1706
 * - China unicom virtual: 167,1704,1707,1708,1709,171
 * - China telecom virtual: 162,1700,1701,1702
 */
const val REGEX_PHONE_EXACT: String =
    "^1(3\\d|4[5-9]|5[0-35-9]|6[2567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$"

const val REGEX_ID_CARD_15: String =
    "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"

const val REGEX_ID_CARD_18: String =
    "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$"

inline val randomUUIDString: String
    get() = UUID.randomUUID().toString()

fun Long.toFileSizeString(): String =
    Formatter.formatFileSize(application, this)

fun Long.toShortFileSizeString(): String =
    Formatter.formatShortFileSize(application, this)

fun String.parseColor(): Int =
    Color.parseColor(this)

fun String.limitLength(length: Int): String =
    if (this.length <= length) this else substring(0, length)

fun String.isPhone(): Boolean =
    REGEX_PHONE_EXACT.toRegex().matches(this)

fun String.isDomainName(): Boolean =
    PatternsCompat.DOMAIN_NAME.matcher(this).matches()

fun String.isEmail(): Boolean =
    PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun String.isIP(): Boolean =
    PatternsCompat.IP_ADDRESS.matcher(this).matches()

/**
 *  Regular expression pattern to match most part of RFC 3987
 *  Internationalized URLs, aka IRIs.
 */
fun String.isWebUrl(): Boolean =
    PatternsCompat.WEB_URL.matcher(this).matches()

fun String.isIDCard15(): Boolean =
    REGEX_ID_CARD_15.toRegex().matches(this)

fun String.isIDCard18(): Boolean =
    REGEX_ID_CARD_18.toRegex().matches(this)

fun String.isJson(): Boolean =
    try {
        JSONObject(this)
        true
    } catch (e: Exception) {
        false
    }

fun Float.toNumberString(fractionDigits: Int = 2, minIntDigits: Int = 1, isGrouping: Boolean = false, isHalfUp: Boolean = true): String =
    toDouble().toNumberString(fractionDigits, minIntDigits, isGrouping, isHalfUp)

fun Double.toNumberString(fractionDigits: Int = 2, minIntDigits: Int = 1, isGrouping: Boolean = false, isHalfUp: Boolean = true): String =
    (NumberFormat.getInstance() as DecimalFormat).apply {
        isGroupingUsed = isGrouping
        roundingMode = if (isHalfUp) RoundingMode.HALF_UP else RoundingMode.DOWN
        minimumIntegerDigits = minIntDigits
        minimumFractionDigits = fractionDigits
        maximumFractionDigits = fractionDigits
    }.format(this)


fun String.countUppercaseLetters(): Int {
    val pattern = "\\p{Upper}".toRegex()
    return pattern.findAll(this).count()
}