package it.leva.domain.repository

import it.leva.domain.model.PreferenceType
import it.leva.domain.model.SuperHero
import it.leva.domain.state.DataState

interface HeroRepository {
    suspend fun fetchHeroes(): DataState<List<SuperHero>>
    suspend fun fetchHero(heroId: Int): DataState<SuperHero>
    suspend fun fetchHeroesFromPreference(preferenceType: PreferenceType): DataState<List<SuperHero>>
}