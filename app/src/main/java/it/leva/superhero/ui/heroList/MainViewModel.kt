package it.leva.superhero.ui.heroList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.leva.domain.model.PreferenceType
import it.leva.domain.model.SuperHero
import it.leva.domain.state.ViewState
import it.leva.domain.usecase.HeroUseCase
import it.leva.domain.usecase.PreferenceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val heroUseCase: HeroUseCase,
    private val preferenceUseCase: PreferenceUseCase
) : ViewModel() {

    private val _heroListViewState = MutableLiveData<ViewState<List<SuperHero>>>()
    val heroListViewState: LiveData<ViewState<List<SuperHero>>> = _heroListViewState

    private val _heroViewState = MutableLiveData<ViewState<SuperHero>>()
    val heroViewState: LiveData<ViewState<SuperHero>> = _heroViewState

    fun fetchHeroes() {
        viewModelScope.launch {
            heroUseCase.fetchHeroes().collect {
                _heroListViewState.postValue(it)
            }
        }
    }

    fun fetchHero(id: Int) {
        viewModelScope.launch {
            heroUseCase.fetchHero(id).collect {
                _heroViewState.postValue(it)
            }
        }
    }

    fun insertPreference(heroId: Int, preferenceType: PreferenceType) {
        viewModelScope.launch(Dispatchers.IO) {
            preferenceUseCase.insertPreference(heroId = heroId, preferenceType = preferenceType)
        }
    }

    fun fetchHeroesFromPreference(preferenceType: PreferenceType) {
        viewModelScope.launch {
            heroUseCase.fetchHeroesFromPreference(preferenceType).collect {
                _heroListViewState.postValue(it)
            }
        }
    }

}