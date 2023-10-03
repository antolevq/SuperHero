package it.leva.data.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_preference")
data class CharacterPreferenceEntity(
    @PrimaryKey @ColumnInfo(name="character_id") val charactedId: Int,
    @ColumnInfo(name="preference") val preference: Preference
)

enum class Preference(val value: Int) {
    LIKED(1),
    DISLIKED(-1)
}
