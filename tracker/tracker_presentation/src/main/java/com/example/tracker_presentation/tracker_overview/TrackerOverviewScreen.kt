package com.example.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.tracker_presentation.tracker_overview.components.NutrientsHeader
import org.koin.androidx.compose.getViewModel

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
) {
    val viewModel = getViewModel<TrackerOverviewViewModel>()
    val spacing = LocalSpacing.current
    val state = viewModel.state

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.medium)
    ) {
        item {
            NutrientsHeader(state = state)
        }
    }
}
