package it.leva.data.persistence.dataSource

import it.leva.data.persistence.entity.CharacterPreferenceEntity
import it.leva.data.persistence.entity.Preference

interface PreferenceDataSource {
    suspend fun getAllCharacterPreferences(): List<CharacterPreferenceEntity>
    suspend fun insertCharacterPreference(characterPreference: CharacterPreferenceEntity)
    suspend fun getCharacterPreferenceById(id: Int): CharacterPreferenceEntity?
    suspend fun getAllCharacterPreferencesWhereType(preference: Preference): List<CharacterPreferenceEntity>
}