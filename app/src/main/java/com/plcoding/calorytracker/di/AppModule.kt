package com.plcoding.calorytracker.di

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.core.data.DefaultPreferencesImpl
import com.example.core.domain.preferences.IPreferences
import com.example.core.domain.use_cases.FilterOutDigits
import com.example.onboarding_domain.use_case.ValidateNutrients
import com.example.onboarding_presentation.activity.ActivityViewModel
import com.example.onboarding_presentation.age.AgeViewModel
import com.example.onboarding_presentation.gender.GenderViewModel
import com.example.onboarding_presentation.goal.GoalViewModel
import com.example.onboarding_presentation.height.HeightViewModel
import com.example.onboarding_presentation.nutrient_goal.NutrientGoalViewModel
import com.example.onboarding_presentation.weight.WeightViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<SharedPreferences> {
        androidContext().getSharedPreferences("shared_pref", MODE_PRIVATE)
    }

    single<IPreferences> {
        DefaultPreferencesImpl(get())
    }

    viewModel {
        GenderViewModel(get())
    }

    viewModel {
        AgeViewModel(get(), get())
    }

    viewModel {
        HeightViewModel(get(), get())
    }

    viewModel {
        WeightViewModel(get())
    }

    viewModel {
        ActivityViewModel(get())
    }

    viewModel {
        GoalViewModel(get())
    }

    single {
        FilterOutDigits()
    }

    viewModel {
        NutrientGoalViewModel(get(), get(), get())
    }
}
