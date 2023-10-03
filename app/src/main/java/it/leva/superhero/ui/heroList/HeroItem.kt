package it.leva.superhero.ui.heroList

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import it.leva.domain.model.SuperHero
import it.leva.domain.model.UserPreference
import it.leva.superhero.ui.common.HeroCard
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeroItem(
    superHero: SuperHero,
    onCardClick: (heroId: Int) -> Unit,
    onLikeChoice: (heroId: Int) -> Unit,
    onDislikeChoice: (heroId: Int) -> Unit
) {
    val context = LocalContext.current
    var show by remember { mutableStateOf(true) }
    val currentItem by rememberUpdatedState(superHero)
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToEnd) {
                currentItem.userPreference = UserPreference.LIKE
                onLikeChoice.invoke(currentItem.id)
            } else if (it == DismissValue.DismissedToStart) {
                currentItem.userPreference = UserPreference.DISLIKE
                onDislikeChoice.invoke(currentItem.id)
            }
            true
        }, positionalThreshold = { 150.dp.toPx() }
    )
    AnimatedVisibility(
        show, exit = fadeOut(spring())
    ) {
        SwipeToDismiss(
            state = dismissState,
            modifier = Modifier,
            background = {
                DismissBackground(dismissState)
            },
            dismissContent = {
                HeroCard(superHero = superHero, onCardClick)
            }
        )
    }

    if (dismissState.currentValue != DismissValue.Default) {
        LaunchedEffect(Unit) {
            delay(400)
            dismissState.reset()
        }
    }


}