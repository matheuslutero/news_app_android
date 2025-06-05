package com.matheuslutero.newsapp.navigation

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.matheuslutero.newsapp.article.domain.model.Article
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object NavTypes {
    val ArticleType = object : NavType<Article>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: SavedState, key: String): Article? {
            return Json.Default.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Article {
            return Json.Default.decodeFromString(value)
        }

        override fun serializeAsValue(value: Article): String {
            return Uri.encode(Json.Default.encodeToString(value))
        }

        override fun put(bundle: SavedState, key: String, value: Article) {
            bundle.putString(key, Json.Default.encodeToString(value))
        }
    }
}
