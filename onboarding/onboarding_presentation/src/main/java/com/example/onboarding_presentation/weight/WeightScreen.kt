package com.example.onboarding_presentation.weight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.onboarding_presentation.R
import com.example.onboarding_presentation.UnitTextField
import com.example.onboarding_presentation.components.ActionButton
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

@Composable
fun WeightScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    scaffoldState: ScaffoldState
) {
    val viewModel = getViewModel<WeightViewModel>()
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
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
            Modifier
                .fillMaxSize()
                .align(Alignment.Center)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.whats_your_weight),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.medium))
            UnitTextField(
                value = viewModel.weight,
                onValueChange = viewModel::onWeightEnter,
                unit = stringResource(
                    id = R.string.kg
                )
            )
        }
        ActionButton(
            text = stringResource(R.string.next),
            onClick = { viewModel.onNextClicked() },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
