package com.example.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetSearchingCoutriesUseCase
import com.example.domain.usecase.GetSearchingHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getSearchingCoutriesUseCase: GetSearchingCoutriesUseCase,
    private val getSearchingHistoryUseCase: GetSearchingHistoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenContract())
    val uiState = _uiState.asStateFlow()

    fun reducer(intent: SearchScreenIntent) {
        when (intent) {
            is SearchScreenIntent.GetSearchingCountriesList -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(
                            countriesList = getSearchingCoutriesUseCase.invoke(
                                country = _uiState.value.searchingName.toString()
                            ).searchResultList
                        )
                    }
                }
            }

            is SearchScreenIntent.GetWeatherInCountry -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(searchingHistoryList = getSearchingHistoryUseCase.invoke().searchResultList)
                    }
                }
            }

            is SearchScreenIntent.UpdateSearchingName -> {
                viewModelScope.launch(context = Dispatchers.IO) {
                    _uiState.update {
                        it.copy(searchingName = intent.country)
                    }
                }
            }
        }
    }

}