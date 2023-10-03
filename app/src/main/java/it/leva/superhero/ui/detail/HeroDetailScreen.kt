package it.leva.superhero.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import it.leva.domain.model.SuperHero
import it.leva.domain.state.ViewState
import it.leva.superhero.R
import it.leva.superhero.ui.common.HeroErrorScreen
import it.leva.superhero.ui.components.HeroAppBar
import it.leva.superhero.ui.components.HeroLabel
import it.leva.superhero.ui.components.HeroProgress
import it.leva.superhero.ui.components.LabelType
import it.leva.superhero.ui.heroList.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HeroDetailApp(
    mainViewModel: MainViewModel = koinViewModel(),
    heroId: Int,
    navHostController: NavHostController
) {
    val viewState = remember { mutableStateOf<ViewState<SuperHero>?>(null) }
    LaunchedEffect(Unit) {
        mainViewModel.fetchHero(heroId)
    }
    mainViewModel.heroViewState.observe(LocalLifecycleOwner.current) {
        viewState.value = it
    }
    when (viewState.value) {
        is ViewState.Error -> HeroErrorScreen(
            errorMessage = viewState.value?.errorMessage.orEmpty()
        )

        is ViewState.Success -> HeroDetailScreen(
            viewState.value?.data ?: SuperHero(),
            navHostController = navHostController
        )

        else -> HeroProgress()
    }
}

@Composable()
fun HeroDetailScreen(superHero: SuperHero, navHostController: NavHostController) {
    Scaffold(topBar = {
        HeroAppBar(
            title = superHero.name,
            showBackIcon = true,
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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .clickable { }, elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    AsyncImage(
                        model = superHero.thumbnailUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                    Row(modifier = Modifier.padding(8.dp)) {
                        HeroLabel(
                            text = superHero.description.orEmpty(),
                            labelType = LabelType.NORMAL
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    HeroLabel(
                        modifier = Modifier.padding(8.dp),
                        text = "Comics count: ${superHero.comicList.size}",
                        labelType = LabelType.SMALL
                    )
                    HeroLabel(
                        modifier = Modifier.padding(8.dp),
                        text = "Stories count: ${superHero.storyList.size}",
                        labelType = LabelType.SMALL
                    )
                    HeroLabel(
                        modifier = Modifier.padding(8.dp),
                        text = "Series count: ${superHero.seriesList.size}",
                        labelType = LabelType.SMALL
                    )
                    HeroLabel(
                        modifier = Modifier.padding(8.dp),
                        text = "Events count: ${superHero.eventList.size}",
                        labelType = LabelType.SMALL
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Row {
                        val uriHandler = LocalUriHandler.current
                        ClickableText(
                            text = AnnotatedString(stringResource(id = R.string.read_more)),
                            style = TextStyle(
                                color = Color.Blue,
                                fontSize = 24.sp,
                            ),
                            onClick = {
                                uriHandler.openUri(superHero.websiteUrlList)
                            })

                    }
                }
            }
        }
    })
}