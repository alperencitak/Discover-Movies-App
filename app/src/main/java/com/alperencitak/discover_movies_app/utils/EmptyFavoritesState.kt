package com.alperencitak.discover_movies_app.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alperencitak.discover_movies_app.ui.theme.SoftRed

@Composable
fun EmptyFavoritesState(
    nunito: FontFamily,
    onExploreClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = SoftRed,
                modifier = Modifier.size(64.dp)
            )
            
            Text(
                text = "No Favorites Yet",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = nunito,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
            
            Text(
                text = "Start exploring and add movies to your favorites",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = nunito,
                    color = Color.White.copy(alpha = 0.7f)
                ),
                textAlign = TextAlign.Center
            )
            
            Button(
                onClick = onExploreClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = SoftRed
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = "Explore Movies",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = nunito,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
    }
}