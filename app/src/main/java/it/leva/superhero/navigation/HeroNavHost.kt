package it.leva.superhero.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.leva.domain.model.PreferenceType
import it.leva.superhero.ui.detail.HeroDetailApp
import it.leva.superhero.ui.heroList.HeroListApp
import it.leva.superhero.ui.likeDislike.LikeDislikeScreenApp

@Composable
fun HeroNavHost() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationItem.HeroList.route) {
        composable(NavigationItem.HeroList.route) {
            HeroListApp(navHostController = navController) { heroId ->
                navController.navigate("detail/$heroId")
            }
        }
        composable(NavigationItem.LikeList.route) {
            LikeDislikeScreenApp(
                preferenceType = PreferenceType.LIKE,
                navHostController = navController
            )
        }
        composable(NavigationItem.DislikeList.route) {
            LikeDislikeScreenApp(
                preferenceType = PreferenceType.DISLIKE,
                navHostController = navController
            )
        }
        composable(NavigationItem.detail.route) {
            it.arguments?.getString("heroId")?.let { safeHeroId ->
                HeroDetailApp(heroId = safeHeroId.toInt(), navHostController = navController)
            }

        }
    }
    /*composable("details/{id}",
        arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })
    ) {
        val id = requireNotNull(it.arguments).getLong("id")
        DetailsScreen(id)*/
}