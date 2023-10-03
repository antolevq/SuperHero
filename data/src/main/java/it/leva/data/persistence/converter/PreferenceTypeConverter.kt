package it.leva.data.persistence.converter

import androidx.room.TypeConverter
import it.leva.data.persistence.entity.Preference

class PreferenceTypeConverter {
    @TypeConverter
    fun toPreference(value: Int) = Preference.values().find { it.value == value }

    @TypeConverter
    fun toInt(value: Preference) = value.value
}