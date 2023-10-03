package it.leva.domain.usecase

import it.leva.domain.model.PreferenceType

interface PreferenceUseCase {
    suspend fun insertPreference(heroId: Int, preferenceType: PreferenceType)
}