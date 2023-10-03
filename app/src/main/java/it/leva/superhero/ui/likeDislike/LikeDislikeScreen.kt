package it.leva.superhero.ui.likeDislike

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavHostController
import it.leva.domain.model.PreferenceType
import it.leva.domain.model.SuperHero
import it.leva.domain.state.ViewState
import it.leva.superhero.navigation.NavigationItem
import it.leva.superhero.ui.common.HeroCard
import it.leva.superhero.ui.common.HeroErrorScreen
import it.leva.superhero.ui.components.HeroAppBar
import it.leva.superhero.ui.components.HeroProgress
import it.leva.superhero.ui.heroList.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LikeDislikeScreenApp(
    mainViewModel: MainViewModel = koinViewModel(),
    preferenceType: PreferenceType,
    navHostController: NavHostController
) {
    val viewState = remember { mutableStateOf<ViewState<List<SuperHero>>?>(null) }
    val heroesState = remember {
        mutableStateOf<List<SuperHero>>(listOf())
    }
    LaunchedEffect(Unit) {
        mainViewModel.fetchHeroesFromPreference(preferenceType)
    }

    mainViewModel.heroListViewState.observe(LocalLifecycleOwner.current) {
        viewState.value = it
    }
    when (viewState.value) {
        is ViewState.Error -> HeroErrorScreen(
            errorMessage = viewState.value?.errorMessage.orEmpty()
        )

        is ViewState.Loading -> HeroProgress()

        is ViewState.Success -> {
            heroesState.value = viewState.value?.data ?: listOf()
            HeroListScreen(
                superHeroesMutableState = heroesState,
                navHostController = navHostController,
                preferenceType = preferenceType
            )
        }

        null -> {}
    }
}

@Composable
private fun HeroListScreen(
    superHeroesMutableState: MutableState<List<SuperHero>>,
    navHostController: NavHostController,
    preferenceType: PreferenceType
) {
    Scaffold(topBar = {
        HeroAppBar(
            title = NavigationItem.HeroList.title,
            showBackIcon = true,
            showLikeButton = preferenceType != PreferenceType.LIKE,
            showDisikeButton = preferenceType != PreferenceType.DISLIKE,
            navController = navHostController
        )
    }, content = { padding ->
        padding
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn {
                items(
                    items = superHeroesMutableState.value,
                    key = { item: SuperHero -> item.id },
                    itemContent = {
                        HeroCard(
                            superHero = it,
                            onCardClick = { },
                        )
                    })
            }
        }
    })
}