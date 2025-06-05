package com.matheuslutero.newsapp.ui.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.matheuslutero.newsapp.R

@Composable
fun NewsAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkColorScheme(
            primary = colorResource(R.color.primary),
            onPrimary = colorResource(R.color.onPrimary),
            secondary = colorResource(R.color.secondary),
            onSecondary = colorResource(R.color.onSecondary),
        )
        else -> lightColorScheme(
            primary = colorResource(R.color.primary),
            onPrimary = colorResource(R.color.onPrimary),
            secondary = colorResource(R.color.secondary),
            onSecondary = colorResource(R.color.onSecondary),
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
