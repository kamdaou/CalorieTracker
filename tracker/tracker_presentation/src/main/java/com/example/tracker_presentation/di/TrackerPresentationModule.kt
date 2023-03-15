package com.example.tracker_presentation.di

import com.example.tracker_presentation.tracker_overview.TrackerOverviewViewModel
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trackerPresentationModule = module {
    viewModel {
        TrackerOverviewViewModel(get(), get())
    }
}
