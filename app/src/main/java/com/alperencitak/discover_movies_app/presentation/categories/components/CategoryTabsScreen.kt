package com.alperencitak.discover_movies_app.presentation.categories.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.domain.model.Genre

@Composable
fun CategoryTabsScreen(
    categories: List<Genre>,
    selected: Int,
    onCategorySelected: (Genre) -> Unit
) {
    val selectedTabIndex = categories.indexOfFirst { it.id == selected }.coerceAtLeast(0)
    val nunito = FontFamily(Font(R.font.nunito_black))

    Column {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp,
            containerColor = MaterialTheme.colorScheme.background,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .height(3.dp),
                    color = Color.Red
                )
            }
        ) {
            categories.forEachIndexed { index, genre ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        onCategorySelected(genre)
                    },
                    text = {
                        Text(
                            text = genre.name,
                            fontFamily = nunito,
                            color = if (index == selectedTabIndex) Color.Red else Color.White
                        )
                    }
                )
            }
        }
    }
}
