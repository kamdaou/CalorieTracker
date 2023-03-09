package com.example.core.domain.preferences

import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo

interface IPreferences {
    fun saveAge(age: Int)
    fun saveWeight(weight: Float)
    fun saveHeight(height: Int)
    fun saveCarbRatio(ratio: Float)
    fun saveFatRatio(ration: Float)
    fun saveProteinRatio(ration: Float)
    fun saveGender(gender: Gender)
    fun saveGoalType(goalType: GoalType)
    fun saveActivityLevel(activityLevel: ActivityLevel)

    fun loadUserInfos(): UserInfo

    companion object {
        const val AGE_KEY = "age_key"
        const val WEIGHT_KEY = "weight_key"
        const val HEIGHT_KEY = "height_key"
        const val CARB_RATIO_KEY = "carb_ratio_key"
        const val FAT_RATIO_KEY = "fat_ratio_key"
        const val PROTEIN_RATIO_KEY = "protein_ratio_key"
        const val GENDER_KEY = "gender_key"
        const val GOAL_TYPE_KEY = "goal_type_key"
        const val ACTIVITY_LEVEL_KEY = "activity_level_key"
    }
}
