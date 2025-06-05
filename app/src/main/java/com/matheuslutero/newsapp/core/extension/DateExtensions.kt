package com.matheuslutero.newsapp.core.extension

import android.text.format.DateUtils
import java.util.Date

fun Date.toTimeSpanString(): String {
    return DateUtils.getRelativeTimeSpanString(
        time, Date().time, DateUtils.MINUTE_IN_MILLIS
    ).toString()
}
