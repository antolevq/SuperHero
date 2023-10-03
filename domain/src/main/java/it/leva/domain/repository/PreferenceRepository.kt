package it.leva.domain.repository

import it.leva.domain.model.PreferenceType

interface PreferenceRepository {
    suspend fun insertPreference(heroId: Int, preference: PreferenceType)
}