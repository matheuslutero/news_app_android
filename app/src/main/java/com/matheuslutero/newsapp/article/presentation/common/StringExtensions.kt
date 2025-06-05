package com.matheuslutero.newsapp.article.presentation.common

fun String.sanitize() = replace(Regex("\\[\\+\\d+ chars]"), "")
