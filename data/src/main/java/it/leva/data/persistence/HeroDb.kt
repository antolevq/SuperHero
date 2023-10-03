package it.leva.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import it.leva.data.persistence.converter.PreferenceTypeConverter
import it.leva.data.persistence.dao.CharacterPreferenceDao
import it.leva.data.persistence.entity.CharacterPreferenceEntity

@Database(entities = [CharacterPreferenceEntity::class], version = 1, exportSchema = false)
@TypeConverters(PreferenceTypeConverter::class)
abstract class HeroDb : RoomDatabase() {
    abstract fun CharacterPreferenceDao(): CharacterPreferenceDao

    companion object {
        fun instance(context: Context): HeroDb {
            return Room.databaseBuilder(
                context, HeroDb::class.java,
                "superhero_database"
            ).fallbackToDestructiveMigration().build()
        }
    }
}