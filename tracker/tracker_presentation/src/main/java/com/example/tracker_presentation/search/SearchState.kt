package com.example.tracker_presentation.search

data class SearchState(
    val query: String = "",
    val trackableFoods: List<TrackableFoodUiState> = emptyList(),
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false
)
