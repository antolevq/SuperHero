package it.leva.data.repository

import it.leva.data.persistence.dataSource.PreferenceDataSource
import it.leva.data.persistence.entity.CharacterPreferenceEntity
import it.leva.data.persistence.entity.Preference
import it.leva.domain.model.PreferenceType
import it.leva.domain.repository.PreferenceRepository

class PreferenceRepositoryImpl(
    private val dataSource: PreferenceDataSource
) : PreferenceRepository {
    override suspend fun insertPreference(heroId: Int, preference: PreferenceType) {
        val characterPreferenceEntity = CharacterPreferenceEntity(
            charactedId = heroId,
            preference = if (preference == PreferenceType.LIKE) Preference.LIKED else Preference.DISLIKED
        )
        dataSource.insertCharacterPreference(characterPreferenceEntity)
    }
}