package com.example.onboarding_presentation.activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.preferences.IPreferences
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ActivityViewModel(private val preferences: IPreferences): ViewModel() {

    var selectedActivityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNextClicked() {
        viewModelScope.launch {
            preferences.saveActivityLevel(selectedActivityLevel)
            _uiEvent.send(UiEvent.Navigate(Route.GOAL))
        }
    }

    fun onActivityLevelSelect(activityLevel: ActivityLevel) {
        selectedActivityLevel = activityLevel
    }
}
