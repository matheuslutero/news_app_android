package com.matheuslutero.newsapp.article.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    error: Painter? = null,
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .crossfade(true)
        .build()

    AsyncImage(
        modifier = modifier,
        model = imageRequest,
        contentDescription = contentDescription,
        contentScale = contentScale,
        error = error,
    )
}
