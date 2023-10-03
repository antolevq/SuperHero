package it.leva.domain.usecase

import it.leva.domain.model.PreferenceType
import it.leva.domain.model.SuperHero
import it.leva.domain.repository.HeroRepository
import it.leva.domain.state.ViewState
import it.leva.domain.state.mapToViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HeroUseCaseImpl(
    private val heroRepository: HeroRepository
) : HeroUseCase {
    override suspend fun fetchHeroes(): Flow<ViewState<List<SuperHero>>> {
        return flow {
            emit(ViewState.Loading())
            emit(heroRepository.fetchHeroes().mapToViewState())
        }.flowOn(Dispatchers.Default)
    }

    override suspend fun fetchHero(heroId: Int): Flow<ViewState<SuperHero>> {
        return flow {
            emit(ViewState.Loading())
            emit(heroRepository.fetchHero(heroId).mapToViewState())
        }.flowOn(Dispatchers.Default)
    }

    override suspend fun fetchHeroesFromPreference(preferenceType: PreferenceType): Flow<ViewState<List<SuperHero>>> {
        return flow {
            emit(ViewState.Loading())
            emit(heroRepository.fetchHeroesFromPreference(preferenceType).mapToViewState())
        }.flowOn(Dispatchers.Default)
    }

}