package it.leva.data.network.service.test

import it.leva.data.dto.CharacterDataWrapperDTO
import it.leva.data.network.service.HeroService
import it.leva.data.util.ResourceHelper

class MockCharactersApi : HeroService{
    companion object{
        private const val CHARACTERS_JSON = "characters.json"
    }
    override suspend fun getSuperheroes(): CharacterDataWrapperDTO {
        return ResourceHelper.getFile(CHARACTERS_JSON)
    }

    override suspend fun getSuperheroDetails(characterId: Int): CharacterDataWrapperDTO {
        return ResourceHelper.getFile(CHARACTERS_JSON)
    }
}