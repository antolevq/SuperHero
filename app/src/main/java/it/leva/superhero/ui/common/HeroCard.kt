package it.leva.superhero.ui.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import it.leva.domain.model.SuperHero
import it.leva.domain.model.UserPreference
import it.leva.superhero.R
import it.leva.superhero.ui.components.HeroLabel
import it.leva.superhero.ui.components.LabelType

@Composable
fun HeroCard(superHero: SuperHero, onCardClick: (heroId: Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clickable { onCardClick.invoke(superHero.id) },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        ListItem(
            modifier = Modifier.clip(MaterialTheme.shapes.small),
            headlineContent = {
                HeroLabel(
                    text = superHero.name,
                    labelType = LabelType.NORMAL
                )
            },
            supportingContent = {
                HeroLabel(
                    text = superHero.description,
                    labelType = LabelType.SMALL
                )
            },
            trailingContent = {
                if (superHero.userPreference != UserPreference.NONE) {
                    Icon(
                        modifier = Modifier.width(16.dp),
                        painter = painterResource(
                            id = when (superHero.userPreference) {
                                UserPreference.LIKE -> R.drawable.like_icon
                                else -> R.drawable.dislike_icon
                            }
                        ),
                        contentDescription = null
                    )
                }
            },
            leadingContent = {
                AsyncImage(
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape),
                    model = superHero.thumbnailUrl,
                    contentDescription = superHero.name,
                    contentScale = ContentScale.Crop
                )
            }
        )
    }
}

