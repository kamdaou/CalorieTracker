package com.example.tracker_presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.use_cases.FilterOutDigits
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.use_case.TrackerUseCases
import com.example.tracker_presentation.R
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val trackerUseCases: TrackerUseCases,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {
    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearch -> {
                searchFood()
            }

            is SearchEvent.OnAmountFoodChange -> {
                state = state.copy(
                    trackableFoods = state.trackableFoods.map {
                        if (it.trackableFood == event.trackableFood) {
                            it.copy(amount = filterOutDigits(event.amount))
                        } else {
                            it
                        }
                    }
                )
            }

            is SearchEvent.OnFocusChange -> {
                state = state.copy(
                    isHintVisible = state.query.isBlank() && !event.isFocused
                )
            }

            is SearchEvent.OnQueryChange -> {
                state = state.copy(
                    query = event.query
                )
            }

            is SearchEvent.OnTrackFoodClick -> {
                trackFood(event)
            }

            is SearchEvent.OnToggleTrackableFood -> {
                state = state.copy(
                    trackableFoods = state.trackableFoods.map {
                        if (it.trackableFood == event.trackableFood) {
                            it.copy(isExpended = !it.isExpended)
                        } else {
                            it
                        }
                    }
                )
            }
        }
    }

    private fun trackFood(event: SearchEvent.OnTrackFoodClick) {
        viewModelScope.launch {
            val uiState = state.trackableFoods.find {
                it.trackableFood == event.food
            }
            trackerUseCases.trackedFood(
                uiState?.trackableFood ?: return@launch,
                amount = uiState.amount.toIntOrNull() ?: return@launch,
                mealType = event.mealType,
                date = event.date
            )
        }
    }

    private fun searchFood() {

        viewModelScope.launch {
            state = state.copy(
                isSearching = true,
                trackableFoods = emptyList()
            )
            trackerUseCases.searchFood(query = state.query)
                .onFailure {
                    state = state.copy(
                        isSearching = false,
                    )
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            message = UiText.StringResource(R.string.error_something_went_wrong)
                        )
                    )
                }
                .onSuccess { foods ->
                    state = state.copy(
                        trackableFoods = foods.map {
                            TrackableFoodUiState(it)
                        },
                        isSearching = false,
                        query = ""
                    )
                }
        }
    }
}