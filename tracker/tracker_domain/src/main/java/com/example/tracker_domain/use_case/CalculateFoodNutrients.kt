package com.example.tracker_domain.use_case

import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.preferences.IPreferences
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt

class CalculateFoodNutrients(private val preferences: IPreferences) {

    operator fun invoke(
        trackedFoods: List<TrackedFood>
    ): Result {
        val allNutrients = trackedFoods
            .groupBy {
                it.mealType
            }
            .mapValues { entry ->
                val key = entry.key
                val value = entry.value
                MealNutrients(
                    proteins = value.sumOf { it.protein },
                    fats = value.sumOf { it.fat },
                    carbs = value.sumOf { it.carbs },
                    calories = value.sumOf { it.calories },
                    mealType = key
                )
            }
        val totalCarbs = allNutrients.values.sumOf { it.carbs }
        val totalFats = allNutrients.values.sumOf { it.fats }
        val totalCalories = allNutrients.values.sumOf { it.calories }
        val totalProteins = allNutrients.values.sumOf { it.proteins }

        val userInfo = preferences.loadUserInfos()
        val caloryGoal = dailyCaloryRequirement(userInfo)
        val carbsGoal = (caloryGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (caloryGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (caloryGoal * userInfo.fatRatio / 9f).roundToInt()

        return Result(
            carbsGoal = carbsGoal,
            proteinGoal = proteinGoal,
            fatGoal = fatGoal,
            caloriesGoal = caloryGoal,
            totalCarbs = totalCarbs,
            totalProteins = totalProteins,
            totalFats = totalFats,
            totalCalories = totalCalories,
            mealNutrients = allNutrients
        )
    }

    private fun bmr(userInfo: UserInfo): Int {
        return when(userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }
            is Gender.Female ->  {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
            }
        }
    }

    private fun dailyCaloryRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
        }
        val caloryExtra = when(userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }


    data class MealNutrients(
        val proteins: Int,
        val fats: Int,
        val carbs: Int,
        val calories: Int,
        val mealType: MealType
    )

    data class Result(
        val carbsGoal: Int,
        val proteinGoal: Int,
        val caloriesGoal: Int,
        val fatGoal: Int,
        val totalCalories: Int,
        val totalCarbs: Int,
        val totalFats: Int,
        val totalProteins: Int,
        val mealNutrients: Map<MealType, MealNutrients>
    )
}
