package com.alperencitak.discover_movies_app.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alperencitak.discover_movies_app.R
import com.alperencitak.discover_movies_app.ui.theme.SoftBlack
import com.alperencitak.discover_movies_app.ui.theme.SoftGray
import com.alperencitak.discover_movies_app.ui.theme.SoftRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainSearchBar(onSearch: (String) -> Unit){
    var query by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }

    SearchBar(
        inputField = {
            TextField(
                value = query,
                onValueChange = {
                    query = it
                    onSearch(query)
                },
                placeholder = { Text(stringResource(R.string.search)) },
                modifier = Modifier.fillMaxWidth().background(SoftBlack),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Black,
                    unfocusedContainerColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                    errorContainerColor = Color.Red,
                    unfocusedTextColor = SoftRed,
                    focusedTextColor = SoftRed,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedPlaceholderColor = SoftRed,
                    focusedPlaceholderColor = Color.Gray
                ),
                shape = RoundedCornerShape(16.dp)
            )
        },
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .background(SoftBlack)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        tonalElevation = 4.dp,
        shadowElevation = 8.dp,
        windowInsets = WindowInsets.systemBars
    ) {}
}