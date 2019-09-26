package com.tat.dms.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    @SuppressLint("WrongConstant")
    fun isOnline(context: Context): Boolean {
        val connMgr = context.getSystemService("connectivity") as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
    fun setGenerateId(): String {
        val currentDate = Calendar.getInstance().time
        val format = SimpleDateFormat("yyyyMMdd HHmmss")
        return format.format(currentDate)
    }
}