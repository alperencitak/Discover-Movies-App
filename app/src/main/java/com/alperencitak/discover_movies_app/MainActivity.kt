package com.alperencitak.discover_movies_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alperencitak.discover_movies_app.ui.theme.DiscoverMoviesAppTheme
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftRed
import com.alperencitak.discover_movies_app.view.MovieCategoryScreen
import com.alperencitak.discover_movies_app.view.MovieDetailScreen
import com.alperencitak.discover_movies_app.view.MovieListScreen
import com.alperencitak.discover_movies_app.view.NavScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiscoverMoviesAppTheme {
                ScaffoldWithNavBar()
            }
        }
    }
}

@Composable
fun ScaffoldWithNavBar(){
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val nunito = FontFamily(
        Font(R.font.nunito_black)
    )
    Scaffold(
        bottomBar = {
            if(currentRoute != "movie_detail/{movieId}"){
                NavigationBar(
                    containerColor = SoftBlack,
                    modifier = Modifier
                        .padding(horizontal = 32.dp, vertical = 14.dp)
                        .height(64.dp)
                        .clip(CircleShape)
                        .shadow(8.dp)
                ){
                    NavigationBarItem(
                        selected = currentRoute == "main",
                        onClick = {
                            if(currentRoute != "main"){
                                navController.navigate("main")
                            }
                        },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home Icon")},
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = SoftRed,
                            selectedIconColor = SoftBlack,
                            unselectedIconColor = SoftRed
                        )
                    )
                    NavigationBarItem(
                        selected = currentRoute == "categories",
                        onClick = {
                            if(currentRoute != "categories"){
                                navController.navigate("categories")
                            }
                        },
                        icon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = SoftRed,
                            selectedIconColor = SoftBlack,
                            unselectedIconColor = SoftRed
                        )
                    )
                }
            }
        }
    ){ paddingValues ->
        NavScreen(navController, paddingValues)
    }
}