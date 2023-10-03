package it.leva.superhero.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import it.leva.superhero.R
import it.leva.superhero.navigation.NavigationItem
import it.leva.superhero.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroAppBar(
    title: String,
    showBackIcon: Boolean = false,
    showLikeButton: Boolean = false,
    showDisikeButton: Boolean = false,
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black
            )
        },
        navigationIcon = {
            if (showBackIcon) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Navigation icon"

                    )
                }
            }
        },
        actions = {
            if (showLikeButton) {
                IconButton(onClick = { navController.navigate(NavigationItem.LikeList.route) }) {
                    Icon(
                        modifier = Modifier
                            .width(24.dp)
                            .padding(2.dp),
                        painter = painterResource(id = R.drawable.like_icon),
                        contentDescription = null,
                    )
                }
            }
            if (showDisikeButton) {
                IconButton(onClick = { navController.navigate(NavigationItem.DislikeList.route) }) {
                    Icon(
                        modifier = Modifier
                            .width(24.dp)
                            .padding(2.dp),
                        painter = painterResource(id = R.drawable.dislike_icon),
                        contentDescription = null
                    )
                }
            }
        },
        colors = topAppBarColors(
            containerColor = Purple80,
            scrolledContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp)
        )
    )
}