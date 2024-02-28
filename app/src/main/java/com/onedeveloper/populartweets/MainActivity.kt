package com.onedeveloper.populartweets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.onedeveloper.populartweets.api.TweetsyApi
import com.onedeveloper.populartweets.screens.CategoryScreen
import com.onedeveloper.populartweets.screens.DetailScreen
import com.onedeveloper.populartweets.ui.theme.PopularTweetsTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var tweetsyApi: TweetsyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PopularTweetsTheme {
                AppRoutes()
            }
        }
    }
}

@Composable
fun AppRoutes() {
    val navigationNavController: NavHostController = rememberNavController()
    NavHost(navController = navigationNavController, startDestination = "category") {
        composable(route = "category") {
            CategoryScreen{
                navigationNavController.navigate("detail/${it}")
            }
        }
        composable(route = "detail/{category}", arguments = listOf(
            navArgument("category") {
                type = NavType.StringType
            }
        )) {
            val category:String = it.arguments?.getString("category").toString()
            DetailScreen()
        }
    }
}
