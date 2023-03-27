package com.matheuslutero.newsapp.core.extension

import android.content.Context
import com.matheuslutero.newsapp.R
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

private const val SECOND = 1
private const val MINUTE = 60 * SECOND
private const val HOUR = 60 * MINUTE
private const val DAY = 24 * HOUR
private const val MONTH = 30 * DAY
private const val YEAR = 12 * MONTH

fun Date.toTimeSpanString(context: Context): String {
    val now = Calendar.getInstance(TimeZone.getTimeZone("UTC")).time
    val before = Calendar.getInstance(TimeZone.getTimeZone("UTC")).also { it.time = this }.time
    val diff = ((now.time - before.time) / 1000).toInt()
    val resources = context.resources

    return when {
        diff < MINUTE -> {
            resources.getString(R.string.time_just_now)
        }
        diff < HOUR -> {
            val count = diff / MINUTE
            resources.getQuantityString(R.plurals.time_minutes_ago, count, count)
        }
        diff < DAY -> {
            val count = diff / HOUR
            resources.getQuantityString(R.plurals.time_hours_ago, count, count)
        }
        diff < MONTH -> {
            val count = diff / DAY
            resources.getQuantityString(R.plurals.time_days_ago, count, count)
        }
        diff < YEAR -> {
            val count = diff / MONTH
            resources.getQuantityString(R.plurals.time_months_ago, count, count)
        }
        else -> {
            val count = diff / YEAR
            resources.getQuantityString(R.plurals.time_years_ago, count, count)
        }
    }
}
