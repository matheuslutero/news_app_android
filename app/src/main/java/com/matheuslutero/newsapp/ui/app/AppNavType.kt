package com.matheuslutero.newsapp.ui.app

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.matheuslutero.newsapp.core.model.Article
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object AppNavType {
    val ArticleType = object : NavType<Article>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: SavedState, key: String): Article? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Article {
            return Json.decodeFromString(value)
        }

        override fun serializeAsValue(value: Article): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: SavedState, key: String, value: Article) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}
