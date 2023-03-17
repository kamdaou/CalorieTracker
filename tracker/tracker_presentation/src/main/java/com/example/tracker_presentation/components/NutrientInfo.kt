package com.example.tracker_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun NutrientInfo(
    name: String,
    unit: String,
    amount: Int,
    modifier: Modifier = Modifier,
    unitTextColor: Color = MaterialTheme.colors.onBackground,
    amountTextColor: Color = MaterialTheme.colors.onBackground,
    unitTextSize: TextUnit = 14.sp,
    amountTextSize: TextUnit = 20.sp,
    nameTextStyle: TextStyle = MaterialTheme.typography.body1
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitDisplay(
            amount = amount,
            unit = unit,
            amountTextSize = amountTextSize,
            amountTextColor = amountTextColor,
            unitTextSize = unitTextSize,
            unitTextColor = unitTextColor
        )
        Text(
            text = name,
            color = MaterialTheme.colors.onBackground,
            style = nameTextStyle,
            fontWeight = FontWeight.Light
        )
    }
}
