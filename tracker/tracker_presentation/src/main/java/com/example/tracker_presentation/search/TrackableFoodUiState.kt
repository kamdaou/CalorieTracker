package com.example.tracker_presentation.search

import com.example.tracker_domain.model.TrackableFood

data class TrackableFoodUiState(
    val trackableFood: TrackableFood,
    val isExpended: Boolean = false,
    val amount: String = ""
)
