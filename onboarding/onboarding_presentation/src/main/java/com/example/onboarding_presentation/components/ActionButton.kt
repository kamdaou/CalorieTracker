package com.example.onboarding_presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.core_ui.LocalSpacing
import com.example.core_ui.R

@Composable
fun ActionButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(100.dp),
        enabled = isEnabled
    ) {
        Text(
            text = text,
            style = textStyle,
            modifier = Modifier.padding(LocalSpacing.current.small)
        )
    }
}