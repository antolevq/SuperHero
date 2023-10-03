package it.leva.domain.usecase

import it.leva.domain.model.PreferenceType
import it.leva.domain.repository.PreferenceRepository

class PreferenceUseCaseImpl(
    private val preferenceRepository: PreferenceRepository
) : PreferenceUseCase {
    override suspend fun insertPreference(heroId: Int, preferenceType: PreferenceType) {
        preferenceRepository.insertPreference(heroId = heroId, preference = preferenceType)
    }
}