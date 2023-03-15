package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.plcoding.calorytracker.ui.theme.CarbColor
import com.plcoding.calorytracker.ui.theme.FatColor
import com.plcoding.calorytracker.ui.theme.ProteinColor

@Composable
fun NutrientsBar(
    fats: Int,
    protein: Int,
    carbs: Int,
    calories: Int,
    calorieGoal: Int,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colors.background
    val calorieExceededColor = MaterialTheme.colors.error
    val fatsWidthRatio = remember {
        Animatable(0f)
    }
    val proteinWidthRatio = remember {
        Animatable(0f)
    }
    val carbsWidthRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = fats) {
        fatsWidthRatio.animateTo(
            targetValue = fats * 4f / calorieGoal
        )
    }
    LaunchedEffect(key1 = carbs) {
        carbsWidthRatio.animateTo(
            targetValue = carbs * 4f / calorieGoal
        )
    }
    LaunchedEffect(key1 = protein) {
        proteinWidthRatio.animateTo(
            targetValue = protein * 4f / calorieGoal
        )
    }

    Canvas(modifier = modifier) {
        val carbsWidth = carbsWidthRatio.value * size.width
        val fatsWidth = fatsWidthRatio.value * size.width
        val proteinWidth = proteinWidthRatio.value * size.width
        if (calories <= calorieGoal) {
            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = FatColor,
                size = Size(
                    width = carbsWidth + proteinWidth + fatsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = ProteinColor,
                size = Size(
                    width = carbsWidth + proteinWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = CarbColor,
                size = Size(
                    width = carbsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
        }
        else {
            drawRoundRect(
                color = calorieExceededColor,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }
}
