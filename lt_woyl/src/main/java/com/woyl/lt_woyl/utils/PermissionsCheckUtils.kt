package com.woyl.lt_woyl.utils

import android.app.AppOpsManager
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Binder
import android.os.Build
import android.os.Process
import android.provider.Settings
import java.lang.reflect.Field
import java.lang.reflect.Method


object PermissionsCheckUtils {

    fun hasAllPermissionsGranted(grantResults: IntArray): Boolean {
        if (grantResults.isNotEmpty()) {
            for (grantResult in grantResults) {
                if (grantResult == PackageManager.PERMISSION_DENIED) {
                    return false
                }
            }
            return true
        }
        return false
    }

    /**
     * 判断是否开启定位服务
     *
     * @param context
     * @return
     */
    fun isLocationService(context: Context): Boolean {
        val manager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val gps = manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val netWork = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        return gps || netWork
    }

    /**
     * 悬浮
     */
    fun checkFloatPermission(context: Context): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return true
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                var cls = Class.forName("android.content.Context")
                val declaredField: Field = cls.getDeclaredField("APP_OPS_SERVICE")
                declaredField.isAccessible = true
                var obj: Any? = declaredField.get(cls) as? String ?: return false
                val str2 = obj as String
                obj = cls.getMethod("getSystemService", String::class.java)
                    .invoke(context, str2)
                cls = Class.forName("android.app.AppOpsManager")
                val declaredField2: Field = cls.getDeclaredField("MODE_ALLOWED")
                declaredField2.isAccessible = true
                val checkOp: Method = cls.getMethod(
                    "checkOp", Integer.TYPE, Integer.TYPE,
                    String::class.java
                )
                val result =
                    checkOp.invoke(obj, 24, Binder.getCallingUid(), context.packageName) as Int
                result == declaredField2.getInt(cls)
            } catch (e: Exception) {
                false
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val appOpsMgr =
                    context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
                val mode = appOpsMgr.checkOpNoThrow(
                    "android:system_alert_window", Process.myUid(), context
                        .packageName
                )
                when (mode) {
                    AppOpsManager.MODE_ALLOWED -> {
                        true
                    }
                    AppOpsManager.MODE_IGNORED -> {
                        false
                    }
                    else -> {
                        false
                    }
                }
            } else {
                Settings.canDrawOverlays(context)
            }
        }
    }

}