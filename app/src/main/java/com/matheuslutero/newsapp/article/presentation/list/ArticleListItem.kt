package com.matheuslutero.newsapp.article.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.matheuslutero.newsapp.article.domain.model.Article
import com.matheuslutero.newsapp.article.presentation.common.toTimeSpanString
import com.matheuslutero.newsapp.article.presentation.common.NetworkImage

@Composable
fun ArticlesListItem(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit = {}
) {
    Row(
        modifier
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top,
    ) {
        NetworkImage(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            imageUrl = article.urlToImage,
            error = rememberVectorPainter(Icons.Outlined.Image),
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            if (article.title != null) {
                Text(
                    text = article.title.toString(),
                    style = MaterialTheme.typography.titleMedium,
                )
            }

            if (article.publishedAt != null) {
                Text(
                    text = article.publishedAt.toTimeSpanString(),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
