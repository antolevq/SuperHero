package it.leva.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.leva.data.persistence.entity.CharacterPreferenceEntity
import it.leva.data.persistence.entity.Preference

@Dao
interface CharacterPreferenceDao {
    @Query("SELECT * FROM character_preference")
    fun getAllCharacterPreferences(): List<CharacterPreferenceEntity>

    @Query("SELECT * FROM character_preference WHERE preference = :preference")
    fun getAllCharacterPreferencesWherePreference(preference: Preference): List<CharacterPreferenceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterPreference(characterPreference: CharacterPreferenceEntity)

    @Query("SELECT * FROM character_preference WHERE character_id = :id LIMIT 1")
    fun getCharacterPreferenceById(id: Int): CharacterPreferenceEntity?

}