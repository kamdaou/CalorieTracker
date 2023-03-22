package com.example.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.tracker_domain.model.MealType
import com.example.tracker_presentation.R
import com.example.tracker_presentation.components.DaySelector
import com.example.tracker_presentation.tracker_overview.components.AddButton
import com.example.tracker_presentation.tracker_overview.components.ExpendableMeal
import com.example.tracker_presentation.tracker_overview.components.NutrientsHeader
import com.example.tracker_presentation.tracker_overview.components.TrackedFoodItem
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.getViewModel

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
) {
    val viewModel = getViewModel<TrackerOverviewViewModel>()
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current


    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.medium)
    ) {
        item {
            NutrientsHeader(state = state)
            Spacer(Modifier.height(spacing.medium))
            DaySelector(
                date = viewModel.state.date,
                onPreviousDayClick = { viewModel.onEvent(TrackerOverviewEvents.OnPreviousDayClick) },
                onNextDayClick = { viewModel.onEvent(TrackerOverviewEvents.OnNextDayClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.small)
            )
            Spacer(Modifier.height(spacing.medium))
        }
        items(state.meals) { meal ->
            ExpendableMeal(
                meal = meal,
                onToggleClick = {
                    viewModel.onEvent(TrackerOverviewEvents.OnToggleMealClick(meal))
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacing.small)
                    ) {
                        val foods = state.trackedFoods.filter {
                            it.mealType == meal.mealType
                        }
                        foods.forEach { food ->
                            TrackedFoodItem(
                                trackedFood = food,
                                onDeleteClick = {
                                    viewModel.onEvent(
                                        TrackerOverviewEvents.OnDeleteFood(food)
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.height(spacing.medium))
                        }
                        AddButton(
                            text = stringResource(
                                id = R.string.add_meal,
                                meal.name.asString(context)
                            ),
                            onClick = {
                                viewModel.onEvent(TrackerOverviewEvents.OnAddFoodClick(meal))
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
