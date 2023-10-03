package it.leva.superhero.navigation


sealed class NavigationItem(var route: String, var title: String) {
    object HeroList : NavigationItem("heroList", "Hero List")
    object LikeList : NavigationItem("likeList", "Like List")
    object DislikeList : NavigationItem("dislikeList", "Dislike List")
    object detail : NavigationItem("detail/{heroId}", "Detail")
}
