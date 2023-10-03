package it.leva.data.repository

import it.leva.data.dto.CharacterDTO
import it.leva.data.dto.mapToDomain
import it.leva.data.network.service.HeroService
import it.leva.data.persistence.dataSource.PreferenceDataSource
import it.leva.data.persistence.entity.Preference
import it.leva.domain.model.PreferenceType
import it.leva.domain.model.SuperHero
import it.leva.domain.model.UserPreference
import it.leva.domain.repository.HeroRepository
import it.leva.domain.state.DataState
import java.lang.Exception
import javax.net.ssl.HttpsURLConnection

class HeroRepositoryImpl(
    private val heroService: HeroService,
    private val dataSource: PreferenceDataSource?
) : HeroRepository {

    override suspend fun fetchHeroesFromPreference(preferenceType: PreferenceType): DataState<List<SuperHero>> {
        return try {
            val characterList = mutableListOf<SuperHero>()
            val preference =
                if (preferenceType == PreferenceType.LIKE) Preference.LIKED else Preference.DISLIKED
            dataSource?.getAllCharacterPreferencesWhereType(preference)
                .let { entities ->
                    entities?.forEach {
                        val currentHeroResponse =
                            heroService.getSuperheroDetails(characterId = it.charactedId)
                        if (currentHeroResponse.code == HttpsURLConnection.HTTP_OK) {
                            currentHeroResponse.data?.results?.firstOrNull()?.mapToDomain()
                                ?.let { superHero ->
                                    characterList.add(superHero.apply {
                                        userPreference =
                                            if (preference == Preference.LIKED) UserPreference.LIKE else UserPreference.DISLIKE
                                    })
                                }

                        }
                    }
                }
            if (characterList.size > 0)
                DataState.Success(characterList.toList())
            else
                DataState.Error("Empty List")
        } catch (e: Exception) {
            DataState.Error(e.localizedMessage.orEmpty())
        }
    }

    override suspend fun fetchHeroes(): DataState<List<SuperHero>> {
        return try {
            val response = heroService.getSuperheroes()
            when (response.code) {
                HttpsURLConnection.HTTP_OK -> {
                    val domainResults = response.data?.results?.map { it.mapToDomain() }
                    dataSource?.getAllCharacterPreferences().let {
                        it?.forEach { entity ->
                            domainResults?.firstOrNull { it.id == entity.charactedId }?.userPreference =
                                if (entity.preference == Preference.LIKED) UserPreference.LIKE else UserPreference.DISLIKE
                        }
                    }
                    DataState.Success(domainResults)
                }

                else -> {
                    DataState.Error(response.status.orEmpty())
                }
            }
        } catch (e: Exception) {
            DataState.Error(e.localizedMessage.orEmpty())
        }
    }

    override suspend fun fetchHero(heroId: Int): DataState<SuperHero> {
        return try {
            val response = heroService.getSuperheroDetails(heroId)
            when (response.code) {
                HttpsURLConnection.HTTP_OK -> {
                    DataState.Success(response.data?.results?.firstOrNull()?.mapToDomain())
                }

                else -> {
                    DataState.Error(response.status.orEmpty())
                }
            }
        } catch (e: Exception) {
            DataState.Error(e.localizedMessage.orEmpty())
        }
    }


}