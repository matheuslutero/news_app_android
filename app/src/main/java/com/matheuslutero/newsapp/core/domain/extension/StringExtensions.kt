package com.matheuslutero.newsapp.core.domain.extension

fun String.sanitize() = replace(Regex("\\[\\+\\d+ chars]"), "")
