package it.leva.data.dto

data class CharacterDataWrapperDTO(
    val code: Int?,
    val status: String?,
    val data: CharacterDataContainerDTO?
)

data class CharacterDataContainerDTO(
    val results: List<CharacterDTO>?
)
