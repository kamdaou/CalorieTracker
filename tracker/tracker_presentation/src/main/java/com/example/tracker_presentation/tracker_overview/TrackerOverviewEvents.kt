package com.example.tracker_presentation.tracker_overview

import com.example.tracker_domain.model.TrackedFood

sealed class TrackerOverviewEvents {
    object OnPreviousDayClick: TrackerOverviewEvents()
    object OnNextDayClick: TrackerOverviewEvents()
    data class OnDeleteFood(val trackedFood: TrackedFood): TrackerOverviewEvents()
    data class OnAddFoodClick(val meal: Meal): TrackerOverviewEvents()
    data class OnToggleMealClick(val meal: Meal): TrackerOverviewEvents()
}
