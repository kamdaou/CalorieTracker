package com.example.tracker_domain.use_case

data class TrackerUseCases(
    val deleteTrackedFood: DeleteTrackedFood,
    val trackedFood: InsertTrackedFood,
    val getFoodForDate: GetFoodForDate,
    val searchFood: SearchFood,
    val calculateFoodNutrients: CalculateFoodNutrients
)
