package com.example.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.IPreferences
import com.example.core.domain.use_cases.FilterOutDigits
import com.example.core.util.UiEvent
import com.example.onboarding_domain.use_case.ValidateNutrients
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class NutrientGoalViewModel(
    private val sharedPref: IPreferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
): ViewModel() {

    var state by mutableStateOf(NutrientGoalState())
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.onCarbsRatioEnter -> {
                state = state.copy(
                    carbsRatio = event.ratio
                )
            }
            is NutrientGoalEvent.onFatsRatioEnter -> {
                state = state.copy(
                    fatsRatio = event.ratio
                )
            }
            is NutrientGoalEvent.onProteinRatioEnter -> {
                state = state.copy(
                    proteinsRatio = event.ratio
                )
            }
            is NutrientGoalEvent.onNextClick -> {
                val result = validateNutrients(
                    fatsRatioText = state.fatsRatio,
                    carbsRatioText = state.carbsRatio,
                    proteinRatioText = state.proteinsRatio
                )
                when(result) {
                    is ValidateNutrients.Result.Success -> {
                        sharedPref.saveProteinRatio(result.proteinRatio)
                        sharedPref.saveCarbRatio(result.carbsRatio)
                        sharedPref.saveFatRatio(result.fatsRatio)
                        viewModelScope.launch {
                            _uiEvent.send(
                                UiEvent.Navigate
                            )
                        }
                    }
                    is ValidateNutrients.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(
                                UiEvent.ShowSnackBar(result.message)
                            )
                        }
                    }
                }
            }
        }
    }
}