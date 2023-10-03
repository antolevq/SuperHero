package it.leva.data

import it.leva.data.dto.CharacterDataWrapperDTO
import it.leva.data.network.service.HeroService
import it.leva.data.network.service.test.MockCharactersApi
import it.leva.data.repository.HeroRepositoryImpl
import it.leva.domain.model.SuperHero
import it.leva.domain.repository.HeroRepository
import it.leva.domain.state.DataState
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class CharacterRepositoryTest : KoinTest {
    private val heroRepository: HeroRepository by inject(named("success_repository"))

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            module {
                single<HeroService>(named("success_api")) { MockCharactersApi() }
                factory<HeroRepository>(named("success_repository")) {
                    HeroRepositoryImpl(heroService = get(named("success_api")), null)
                }
            }
        )
    }

    @Test
    fun assert_characters_is_not_empty() {
        var superHeroes: List<SuperHero>? = null
        runBlocking {
            val state = heroRepository.fetchHeroes()
            if (state is DataState.Success) {
                superHeroes = state.data
            }
            assert(superHeroes.isNullOrEmpty().not())
        }
    }
}