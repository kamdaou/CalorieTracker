package com.example.onboarding_domain.use_case

import com.example.core.util.UiText
import com.example.onboarding_domain.R

class ValidateNutrients {
    operator fun invoke(
        fatsRatioText: String,
        carbsRatioText: String,
        proteinRatioText: String
    ): Result {
        val fatsRatio = fatsRatioText.toIntOrNull()
        val carbsRatio = carbsRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()

        if (proteinRatio == null || carbsRatio == null || fatsRatio == null) {
            return Result.Error(message = UiText.StringResource(R.string.error_invalid_values))
        }
        if (proteinRatio + carbsRatio + fatsRatio != 100) {
            return Result.Error(
                message = UiText.StringResource(R.string.error_not_100_percent)
            )
        }
        return Result.Success(
            carbsRatio = carbsRatio / 100f,
            proteinRatio = proteinRatio / 100f,
            fatsRatio = fatsRatio /100f
        )
    }

    sealed class Result{
        data class Success(
            val carbsRatio: Float,
            val proteinRatio: Float,
            val fatsRatio: Float
        ): Result()

        data class Error(
            val message: UiText
        ): Result()
    }
}