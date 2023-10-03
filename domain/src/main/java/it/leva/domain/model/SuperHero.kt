package it.leva.domain.model

data class SuperHero(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val thumbnailUrl: String = "",
    val resourceURI: String = "",
    val websiteUrlList: String = "",
    val comicList: List<UriAndName> = listOf(),
    val storyList: List<UriAndName> = listOf(),
    val eventList: List<UriAndName> = listOf(),
    val seriesList: List<UriAndName> = listOf(),
    var userPreference: UserPreference = UserPreference.NONE
)

enum class UserPreference {
    LIKE, DISLIKE, NONE
}
data class UriAndName(
    val resourceURI: String = "",
    val name: String = ""
)
