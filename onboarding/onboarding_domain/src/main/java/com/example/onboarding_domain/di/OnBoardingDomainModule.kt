package com.example.onboarding_domain.di

import com.example.onboarding_domain.use_case.ValidateNutrients
import org.koin.dsl.module

val onBoardingDomainModule = module {
    single {
        ValidateNutrients()
    }
}
