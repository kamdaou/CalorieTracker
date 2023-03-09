package com.example.onboarding_presentation.nutrient_goal

sealed class NutrientGoalEvent {
    data class onFatsRatioEnter(val ratio: String): NutrientGoalEvent()
    data class onCarbsRatioEnter(val ratio: String): NutrientGoalEvent()
    data class onProteinRatioEnter(val ratio: String): NutrientGoalEvent()
    object onNextClick: NutrientGoalEvent()
}
