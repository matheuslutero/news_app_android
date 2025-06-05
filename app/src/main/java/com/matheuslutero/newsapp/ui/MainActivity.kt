package com.matheuslutero.newsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.matheuslutero.newsapp.ui.core.theme.NewsAppTheme
import com.matheuslutero.newsapp.ui.detail.composable.ArticleDetailView
import com.matheuslutero.newsapp.ui.list.composable.ArticlesView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@Serializable
object Articles

@Serializable
object ArticleDetail

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Articles
                ) {
                    composable<Articles> {
                        ArticlesView(
                            onArticleClick = {
                                navController.navigate(ArticleDetail)
                            }
                        )
                    }
                    composable<ArticleDetail> {
                        ArticleDetailView(
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}
