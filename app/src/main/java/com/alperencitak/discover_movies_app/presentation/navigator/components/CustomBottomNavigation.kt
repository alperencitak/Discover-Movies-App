package com.alperencitak.discover_movies_app.presentation.navigator.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.ui.theme.DiscoverMoviesAppTheme
import com.alperencitak.discover_movies_app.ui.theme.MyAppTheme

@Composable
fun CustomBottomNavigation(
    items: List<CustomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth().height(84.dp).statusBarsPadding(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selected == index,
                onClick = { onItemClick(index) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Icon(
//                            painter = painterResource(item.icon),
//                            contentDescription = null,
//                            modifier = Modifier.size(36.dp)
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Text(
//                            text = item.text,
//                            style = MaterialTheme.typography.labelSmall
//                        )
//                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
                    unselectedTextColor = MaterialTheme.colorScheme.secondary,
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }

}

data class CustomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview(){
    MyAppTheme {
        CustomBottomNavigation(
            items = listOf(
                CustomNavigationItem(
                    icon = R.drawable.home,
                    text = "Home"
                ),
                CustomNavigationItem(
                    icon = R.drawable.search,
                    text = "Search"
                ),
                CustomNavigationItem(
                    icon = R.drawable.category,
                    text = "Categories"
                ),
                CustomNavigationItem(
                    icon = R.drawable.favorite,
                    text = "Favorites"
                )
            ),
            selected = 0,
            onItemClick = {}
        )
    }
}
