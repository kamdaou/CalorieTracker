package com.example.tracker_data.di

import androidx.room.Room
import com.example.tracker_data.local.TrackerDao
import com.example.tracker_data.local.TrackerDatabase
import com.example.tracker_data.remote.IOpenFoodApi
import com.example.tracker_data.repository.TrackerRepositoryImpl
import com.example.tracker_domain.repository.ITrackerRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val trackerDataModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    single<IOpenFoodApi> {
        Retrofit.Builder()
            .baseUrl(IOpenFoodApi.BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(IOpenFoodApi::class.java)
    }

    single {
        Room.databaseBuilder(
            androidContext(),
            TrackerDatabase::class.java,
            "tracker_db"
        ).build().dao
    }

    single<ITrackerRepository> {
        TrackerRepositoryImpl(get(), get())
    }


}
