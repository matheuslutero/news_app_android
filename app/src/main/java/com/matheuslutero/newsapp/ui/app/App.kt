package com.matheuslutero.newsapp.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matheuslutero.newsapp.ui.detail.composable.ArticleDetailView
import com.matheuslutero.newsapp.ui.list.composable.ArticlesView

@Composable
@Preview
fun App() {
    NewsAppTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.Articles
        ) {
            composable<Route.Articles> {
                ArticlesView(
                    onArticleClick = {
                        navController.navigate(Route.ArticleDetail)
                    }
                )
            }
            composable<Route.ArticleDetail> {
                ArticleDetailView(
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
