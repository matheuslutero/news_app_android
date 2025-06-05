package com.matheuslutero.newsapp.core.extension

fun String.sanitize() = replace(Regex("\\[\\+\\d+ chars]"), "")
