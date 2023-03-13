package com.example.tracker_domain.use_case

import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.ITrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class DeleteTrackedFood(
    private val trackerRepository: ITrackerRepository
) {
    suspend operator fun invoke(
        trackedFood: TrackedFood
    ) {
        TrackedFood::class.java.simpleName
        return trackerRepository.deleteTrackedFood(trackedFood)
    }
}
