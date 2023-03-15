package com.example.tracker_presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing

@Composable
fun UnitDisplay(
    unit: String,
    amount: Int,
    modifier: Modifier = Modifier,
    unitTextColor: Color = MaterialTheme.colors.onBackground,
    amountTextColor: Color = MaterialTheme.colors.onBackground,
    unitTextSize: TextUnit = 14.sp,
    amountTextSize: TextUnit = 20.sp
) {
    Row(
        modifier = modifier
    ) {
        Text(
            text = amount.toString(),
            style = MaterialTheme.typography.h1,
            color = amountTextColor,
            fontSize = amountTextSize,
            modifier = Modifier.alignBy(LastBaseline)
        )
        Spacer(Modifier.width(LocalSpacing.current.extraSmall))
        Text(
            text = unit,
            style = MaterialTheme.typography.body1,
            color = unitTextColor,
            fontSize = unitTextSize,
            modifier = Modifier.alignBy(LastBaseline)
        )
    }
}
