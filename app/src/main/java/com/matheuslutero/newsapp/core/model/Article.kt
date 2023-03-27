package com.matheuslutero.newsapp.core.model

import android.os.Parcelable
import java.util.Date
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String? = null,
    val description: String? = null,
    val content: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: Date? = null,
) : Parcelable
