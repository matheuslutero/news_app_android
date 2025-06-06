package com.matheuslutero.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.matheuslutero.newsapp.app.AppTheme
import com.matheuslutero.newsapp.article.domain.model.Article
import com.matheuslutero.newsapp.article.presentation.detail.ArticleDetailScreen
import com.matheuslutero.newsapp.article.presentation.list.ArticleListScreen
import kotlin.reflect.typeOf

@Composable
fun NavRoot() {
    AppTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.Articles
        ) {
            composable<Route.Articles> {
                ArticleListScreen(
                    onArticleClick = {
                        navController.navigate(Route.ArticleDetail(it))
                    }
                )
            }
            composable<Route.ArticleDetail>(
                typeMap = mapOf(
                    typeOf<Article>() to NavTypes.ArticleType
                )
            ) {
                val route = it.toRoute<Route.ArticleDetail>()
                ArticleDetailScreen(
                    article = route.article,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
