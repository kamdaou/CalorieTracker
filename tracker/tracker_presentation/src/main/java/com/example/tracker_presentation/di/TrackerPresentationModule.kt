package com.example.tracker_presentation.di

import com.example.tracker_presentation.search.SearchViewModel
import com.example.tracker_presentation.tracker_overview.TrackerOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trackerPresentationModule = module {
    viewModel {
        TrackerOverviewViewModel(get(), get())
    }

    viewModel {
        SearchViewModel(get(), get())
    }
}
