package com.example.tracker_data.repository

import com.example.tracker_data.local.TrackerDao
import com.example.tracker_data.mappers.toTrackedFood
import com.example.tracker_data.mappers.toTrackableFood
import com.example.tracker_data.mappers.toTrackedFoodEntity
import com.example.tracker_data.remote.IOpenFoodApi
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.ITrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: IOpenFoodApi
) : ITrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val result = api.searchFood(
                query, page, pageSize
            ).products.mapNotNull { product ->
                product.toTrackableFood()
            }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(trackedFood: TrackedFood) {
        dao.insertFood(trackedFood.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(trackedFood: TrackedFood) {
        dao.deleteFood(trackedFood.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.searchFood(
            localDate.dayOfMonth,
            localDate.monthValue,
            localDate.year
        ).map {
            it.map { it1 ->
                it1.toTrackedFood()
            }
        }
    }
}