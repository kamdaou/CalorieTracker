package com.example.tracker_data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tracker_data.local.entity.TrackedFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteFood(trackedFoodEntity: TrackedFoodEntity)

    @Query("""
        SELECT *
        FROM TrackedFoodEntity
        WHERE dayOfMonth = :dayOfMonth AND month = :month AND year = :year
    """)
    fun searchFood(
        dayOfMonth: Int,
        month: Int,
        year: Int
    ): Flow<List<TrackedFoodEntity>>
}