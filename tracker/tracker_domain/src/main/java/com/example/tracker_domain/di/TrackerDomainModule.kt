package com.example.tracker_domain.di

import com.example.core.data.DefaultPreferencesImpl
import com.example.core.domain.preferences.IPreferences
import com.example.tracker_domain.use_case.CalculateFoodNutrients
import com.example.tracker_domain.use_case.DeleteTrackedFood
import com.example.tracker_domain.use_case.GetFoodForDate
import com.example.tracker_domain.use_case.InsertTrackedFood
import com.example.tracker_domain.use_case.SearchFood
import com.example.tracker_domain.use_case.TrackerUseCases
import org.koin.dsl.module
import java.util.prefs.Preferences

val trackerDomainModule = module {

    single {
        DeleteTrackedFood(get())
    }
    single {
        InsertTrackedFood(get())
    }
    single {
        CalculateFoodNutrients(get())
    }
    single {
        GetFoodForDate(get())
    }
    single {
        SearchFood(get())
    }

    single {
        TrackerUseCases(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}
