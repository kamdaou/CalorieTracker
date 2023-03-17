package com.example.tracker_presentation.search

import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackableFood
import java.time.LocalDate

sealed class SearchEvent {
    data class OnTrackFoodClick(
        val food: TrackableFood,
        val mealType: MealType,
        val date: LocalDate
    ) : SearchEvent()
    data class OnFocusChange(val isFocused: Boolean) : SearchEvent()
    data class OnQueryChange(
        val query: String,
        val mealType: MealType,
        val date: LocalDate
    ) : SearchEvent()

    data class OnToggleTrackableFood(val trackableFood: TrackableFood) : SearchEvent()
    object OnSearch : SearchEvent()
    data class OnAmountFoodChange(
        val trackableFood: TrackableFood,
        val amount: String,
    ) : SearchEvent()
}
