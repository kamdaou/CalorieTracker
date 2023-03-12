package com.example.tracker_domain.use_case

import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.ITrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodForDate(
    private val trackerRepository: ITrackerRepository
) {
    suspend operator fun invoke(
        date: LocalDate
    ): Flow<List<TrackedFood>> {
        return trackerRepository.getFoodsForDate(date)
    }
}
