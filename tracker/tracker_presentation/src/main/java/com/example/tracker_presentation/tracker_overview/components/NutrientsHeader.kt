package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing
import androidx.compose.foundation.layout.*
import com.example.tracker_presentation.R
import com.example.tracker_presentation.components.UnitDisplay
import com.example.tracker_presentation.tracker_overview.TrackerOverviewState
import com.plcoding.calorytracker.ui.theme.CarbColor
import com.plcoding.calorytracker.ui.theme.FatColor
import com.plcoding.calorytracker.ui.theme.ProteinColor

@Composable
fun NutrientsHeader(
    state: TrackerOverviewState,
    modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    val animatedCalorieCount = animateIntAsState(
        targetValue = state.totalCalories
    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                )
            )
            .background(MaterialTheme.colors.primary)
            .padding(
                horizontal = spacing.large,
                vertical = spacing.extraLarge
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            UnitDisplay(
                amount = animatedCalorieCount.value,
                unit = stringResource(id = R.string.kcal),
                amountTextColor = MaterialTheme.colors.onPrimary,
                amountTextSize = 40.sp,
                unitTextColor = MaterialTheme.colors.onPrimary,
                modifier = Modifier.align(Alignment.Bottom)
            )
            Column {
                Text(
                    text = stringResource(id = R.string.your_goal),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary
                )
                UnitDisplay(
                    amount = state.caloriesGoal,
                    unit = stringResource(id = R.string.kcal),
                    amountTextColor = MaterialTheme.colors.onPrimary,
                    amountTextSize = 40.sp,
                    unitTextColor = MaterialTheme.colors.onPrimary,
                )
            }
        }
        Spacer(modifier = Modifier.height(spacing.small))
        NutrientsBar(
            carbs = state.totalCarbs,
            protein = state.totalProtein,
            fats = state.totalFat,
            calories = state.totalCalories,
            calorieGoal = state.caloriesGoal,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
        Spacer(modifier = Modifier.height(spacing.large))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NutrientBarInfo(
                value = state.totalCarbs,
                goal = state.carbsGoal,
                name = stringResource(id = R.string.carbs),
                color = CarbColor,
                modifier = Modifier.size(90.dp)
            )
            NutrientBarInfo(
                value = state.totalProtein,
                goal = state.proteinGoal,
                name = stringResource(id = R.string.protein),
                color = ProteinColor,
                modifier = Modifier.size(90.dp)
            )
            NutrientBarInfo(
                value = state.totalFat,
                goal = state.fatGoal,
                name = stringResource(id = R.string.fat),
                color = FatColor,
                modifier = Modifier.size(90.dp)
            )
        }
    }
}
