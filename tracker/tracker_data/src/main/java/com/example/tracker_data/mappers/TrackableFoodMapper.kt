package com.example.tracker_data.mappers

import com.example.tracker_data.remote.dto.Product
import com.example.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt

fun Product.toTrackableFood(): TrackableFood? {
    val caloriesPer100g = this.nutriments.energyKcal100g.roundToInt()
    val fatPer100g = this.nutriments.fat100g.roundToInt()
    val proteinPer100g = this.nutriments.proteins100g.roundToInt()
    val carbsPer100g = this.nutriments.carbohydrates100g.roundToInt()
    return TrackableFood(
        name = this.productName ?: return null,
        imageFrontThumbUrl,
        caloriesPer100g,
        carbsPer100g,
        proteinPer100g,
        fatPer100g
    )
}
