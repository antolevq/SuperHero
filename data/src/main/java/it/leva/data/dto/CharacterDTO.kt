package it.leva.data.dto

import com.google.gson.annotations.SerializedName
import it.leva.domain.model.SuperHero
import it.leva.domain.model.UriAndName
import java.util.Date

data class CharacterDTO(
    val id: Int?,
    val name: String?,
    val description: String?,
    val date: Date?,
    val resourceURI: String?,
    val thumbnail: ImageDTO?,
    val urls: List<UrlDTO>?,
    val comics: BaseListDTO<ComicSummaryDTO>?,
    val stories: BaseListDTO<StorySummaryDTO>?,
    val events: BaseListDTO<EventSummaryDTO>?,
    val series: BaseListDTO<SeriesSummaryDTO>?,
)

data class ImageDTO(
    val path: String?,
    val extension: String?
)

data class UrlDTO(
    val type: String?,
    val url: String?
)

data class BaseListDTO<T>(
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<T>?
)

data class ComicSummaryDTO(
    val resourceURI: String?,
    val name: String?
)

data class StorySummaryDTO(
    val resourceURI: String?,
    val name: String?,
    val type: StorySummaryType?
)

enum class StorySummaryType {
    @SerializedName("interior")
    INTERIOR,

    @SerializedName("cover")
    COVER
}

data class EventSummaryDTO(
    val resourceURI: String?,
    val name: String?
)

data class SeriesSummaryDTO(
    val resourceURI: String?,
    val name: String?
)

fun CharacterDTO.mapToDomain() =
    SuperHero(
        id = this.id ?: 0,
        name = this.name.orEmpty(),
        description = this.description.orEmpty(),
        thumbnailUrl = "${this.thumbnail?.path.orEmpty()}/standard_xlarge.${this.thumbnail?.extension.orEmpty()}".replace(
            "http",
            "https"
        ),
        resourceURI = resourceURI.orEmpty(),
        websiteUrlList = this.urls?.firstOrNull()?.url.orEmpty(),
        comicList = this.comics?.items?.map { UriAndName(resourceURI.orEmpty(), name.orEmpty()) }
            ?: listOf(),
        storyList = this.stories?.items?.map { UriAndName(resourceURI.orEmpty(), name.orEmpty()) }
            ?: listOf(),
        eventList = this.events?.items?.map { UriAndName(resourceURI.orEmpty(), name.orEmpty()) }
            ?: listOf(),
        seriesList = this.series?.items?.map { UriAndName(resourceURI.orEmpty(), name.orEmpty()) }
            ?: listOf()

    )


