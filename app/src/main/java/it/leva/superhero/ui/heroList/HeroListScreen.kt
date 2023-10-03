package it.leva.superhero.ui.heroList

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
import it.leva.superhero.ui.common.HeroErrorScreen
import it.leva.superhero.ui.components.HeroAppBar
import it.leva.superhero.ui.components.HeroProgress
import org.koin.androidx.compose.koinViewModel

@Composable
fun HeroListApp(
    mainViewModel: MainViewModel = koinViewModel(),
    navHostController: NavHostController,
    onSuperHeroClick: (heroId: Int) -> Unit
) {
    val viewState = remember { mutableStateOf<ViewState<List<SuperHero>>?>(null) }
    val heroesState = remember {
        mutableStateOf<List<SuperHero>>(listOf())
    }
    LaunchedEffect(Unit) {
        mainViewModel.fetchHeroes()
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
                onCardClick = onSuperHeroClick,
                navHostController = navHostController,
                onHeroLike = {
                    mainViewModel.insertPreference(it, PreferenceType.LIKE)
                }, onHeroDislike = {
                    mainViewModel.insertPreference(it, PreferenceType.DISLIKE)
                }
            )
        }

        null -> {}
    }


}


@Composable
private fun HeroListScreen(
    superHeroesMutableState: MutableState<List<SuperHero>>,
    navHostController: NavHostController,
    onCardClick: (heroId: Int) -> Unit,
    onHeroLike: (heroId: Int) -> Unit,
    onHeroDislike: (heroId: Int) -> Unit,
) {
    Scaffold(topBar = {
        HeroAppBar(
            title = NavigationItem.HeroList.title,
            showLikeButton = true,
            showDisikeButton = true,
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
                        HeroItem(
                            superHero = it,
                            onCardClick = onCardClick,
                            onLikeChoice = onHeroLike,
                            onDislikeChoice = onHeroDislike
                        )
                    })
            }
        }
    })
}


