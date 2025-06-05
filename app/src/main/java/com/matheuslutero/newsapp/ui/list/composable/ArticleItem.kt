package com.matheuslutero.newsapp.ui.list.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.ui.core.composable.NetworkImage

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit = {}
) {
    Row(
        modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Top,
    ) {
        NetworkImage(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            imageUrl = article.urlToImage,
            error = rememberVectorPainter(Icons.Outlined.Image),
        )

        Text(
            text = article.title.toString(),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
                .padding(8.dp)

        )
    }
}
