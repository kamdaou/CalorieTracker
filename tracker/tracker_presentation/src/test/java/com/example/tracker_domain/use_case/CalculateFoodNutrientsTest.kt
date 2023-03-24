package com.example.tracker_domain.use_case

import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.preferences.IPreferences
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFood
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random


internal class CalculateFoodNutrientsTest {


    private lateinit var calculateFoodNutrients: CalculateFoodNutrients

    @Before
    fun setUp() {
        val preferences = mockk<IPreferences>(relaxed = true)
        every {
            preferences.loadUserInfos()
        } returns UserInfo(
            age = 20,
            weight = 80f,
            height = 170,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f,
            gender = Gender.Male,
            goalType = GoalType.LoseWeight,
            activityLevel = ActivityLevel.High
        )
        calculateFoodNutrients = CalculateFoodNutrients(preferences)

    }

    @Test
    fun `calculate calories for Breakfast works`() {
        val trackedFoods = (1..50).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                fat = Random.nextInt(100),
                protein = Random.nextInt(100),
                calories = Random.nextInt(2000),
                imageUrl = null,
                mealType = MealType.fromString(
                    listOf("breakfast", "diner", "snack", "lunch").random()
                ),
                amount = 100,
                date = LocalDate.now(),
            )
        }

        val result = calculateFoodNutrients(trackedFoods)

        val expectedCalories = trackedFoods.filter {
            it.mealType == MealType.Breakfast
        }.sumOf {
            it.calories
        }

        val breakFastCalories = result.mealNutrients.values
            .filter {
                it.mealType == MealType.Breakfast
            }
            .sumOf {
                it.calories
            }
        assertThat(breakFastCalories).isEqualTo(expectedCalories)
    }
}