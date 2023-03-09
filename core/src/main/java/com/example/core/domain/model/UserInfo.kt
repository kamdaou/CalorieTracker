package com.example.core.domain.model

data class UserInfo(
    val age: Int,
    val weight: Float,
    val height: Int,
    val carbRatio: Float,
    val fatRatio: Float,
    val proteinRatio: Float,
    val gender: Gender,
    val goalType: GoalType,
    val activityLevel: ActivityLevel
)
