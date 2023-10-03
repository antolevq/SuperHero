package it.leva.data.network.service

import it.leva.data.dto.CharacterDataWrapperDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroService {
    @GET("characters?limit=100")
    suspend fun getSuperheroes(): CharacterDataWrapperDTO

    @GET("characters/{characterId}")
    suspend fun getSuperheroDetails(@Path("characterId") characterId: Int): CharacterDataWrapperDTO
}