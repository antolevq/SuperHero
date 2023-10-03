package it.leva.data.persistence.dataSource

import it.leva.data.persistence.HeroDb
import it.leva.data.persistence.entity.CharacterPreferenceEntity
import it.leva.data.persistence.entity.Preference

class PreferenceDataSourceImpl(private val heroDb: HeroDb) : PreferenceDataSource {
    override suspend fun getAllCharacterPreferences(): List<CharacterPreferenceEntity> {
        return heroDb.CharacterPreferenceDao().getAllCharacterPreferences()
    }

    override suspend fun insertCharacterPreference(characterPreference: CharacterPreferenceEntity) {
        heroDb.CharacterPreferenceDao()
            .insertCharacterPreference(characterPreference = characterPreference)
    }

    override suspend fun getCharacterPreferenceById(id: Int): CharacterPreferenceEntity? {
        return heroDb.CharacterPreferenceDao().getCharacterPreferenceById(id)
    }

    override suspend fun getAllCharacterPreferencesWhereType(preference: Preference): List<CharacterPreferenceEntity> {
        return heroDb.CharacterPreferenceDao()
            .getAllCharacterPreferencesWherePreference(preference = preference)
    }
}