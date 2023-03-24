package com.example.onboarding_presentation.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.core.domain.model.ActivityLevel
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.onboarding_presentation.R
import com.example.onboarding_presentation.components.ActionButton
import com.example.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

@Composable
fun ActivityScreen(
    onNextClick: () -> Unit,
) {
    val viewModel = getViewModel<ActivityViewModel>()
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNextClick()
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.large)
    ) {
        Column(
            Modifier.fillMaxSize()
                .align(Alignment.Center)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.whats_your_activity_level),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.medium))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.low),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedColor = Color.White,
                    isSelected = viewModel.selectedActivityLevel == ActivityLevel.Low,
                    onClick = {
                        viewModel.onActivityLevelSelect(ActivityLevel.Low)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(Modifier.width(spacing.medium))
                SelectableButton(
                    text = stringResource(id = R.string.medium),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedColor = Color.White,
                    isSelected = viewModel.selectedActivityLevel == ActivityLevel.Medium,
                    onClick = {
                        viewModel.onActivityLevelSelect(ActivityLevel.Medium)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(Modifier.width(spacing.medium))
                SelectableButton(
                    text = stringResource(id = R.string.high),
                    color = MaterialTheme.colors.primaryVariant,
                    selectedColor = Color.White,
                    isSelected = viewModel.selectedActivityLevel == ActivityLevel.High,
                    onClick = {
                        viewModel.onActivityLevelSelect(ActivityLevel.High)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
        ActionButton(
            text = stringResource(R.string.next),
            onClick = { viewModel.onNextClicked() },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
