package com.woyl.lt_woyl.utils.ExtUtils

import android.os.Build

inline val sdkVersionName: String get() = Build.VERSION.RELEASE

inline val sdkVersionCode: Int get() = Build.VERSION.SDK_INT

inline val deviceManufacturer: String get() = Build.MANUFACTURER