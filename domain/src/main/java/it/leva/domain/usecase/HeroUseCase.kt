package it.leva.domain.usecase

import it.leva.domain.model.PreferenceType
import it.leva.domain.model.SuperHero
import it.leva.domain.state.ViewState
import kotlinx.coroutines.flow.Flow

interface HeroUseCase {
    suspend fun fetchHeroes(): Flow<ViewState<List<SuperHero>>>
    suspend fun fetchHero(heroId: Int): Flow<ViewState<SuperHero>>
    suspend fun fetchHeroesFromPreference(preferenceType: PreferenceType): Flow<ViewState<List<SuperHero>>>
}