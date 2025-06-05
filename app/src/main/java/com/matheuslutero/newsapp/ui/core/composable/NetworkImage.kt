package com.matheuslutero.newsapp.ui.core.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    colorFilter: ColorFilter? = null,
    placeholder: Painter? = null,
    error: Painter? = null,
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .build()

    AsyncImage(
        modifier = modifier,
        model = imageRequest,
        contentDescription = contentDescription,
        contentScale = contentScale,
        colorFilter = colorFilter,
        placeholder = placeholder,
        error = error,
    )
}
