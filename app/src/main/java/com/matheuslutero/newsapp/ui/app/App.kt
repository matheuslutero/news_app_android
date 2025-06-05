package com.matheuslutero.newsapp.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.matheuslutero.newsapp.core.model.Article
import com.matheuslutero.newsapp.ui.detail.composable.ArticleDetailView
import com.matheuslutero.newsapp.ui.list.composable.ArticlesView
import kotlin.reflect.typeOf

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
                        navController.navigate(Route.ArticleDetail(it))
                    }
                )
            }
            composable<Route.ArticleDetail>(
                typeMap = mapOf(
                    typeOf<Article>() to AppNavType.ArticleType
                )
            ) {
                val route = it.toRoute<Route.ArticleDetail>()
                ArticleDetailView(
                    article = route.article,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
