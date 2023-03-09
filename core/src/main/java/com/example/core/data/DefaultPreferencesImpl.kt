package com.example.core.data

import android.content.SharedPreferences
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.preferences.IPreferences

class DefaultPreferencesImpl(private val sharedPref: SharedPreferences) : IPreferences {
    override fun saveAge(age: Int) {
        sharedPref.edit()
            .putInt(IPreferences.AGE_KEY, age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit()
            .putFloat(IPreferences.WEIGHT_KEY, weight)
            .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPref.edit()
            .putInt(IPreferences.HEIGHT_KEY, height)
            .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(IPreferences.CARB_RATIO_KEY, ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(IPreferences.FAT_RATIO_KEY, ratio)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(IPreferences.PROTEIN_RATIO_KEY, ratio)
            .apply()
    }

    override fun saveGender(gender: Gender) {
        sharedPref.edit()
            .putString(IPreferences.GENDER_KEY, gender.name)
            .apply()
    }

    override fun saveGoalType(goalType: GoalType) {
        sharedPref.edit()
            .putString(IPreferences.GOAL_TYPE_KEY, goalType.name)
            .apply()
    }

    override fun saveActivityLevel(activityLevel: ActivityLevel) {
        sharedPref.edit()
            .putString(IPreferences.ACTIVITY_LEVEL_KEY, activityLevel.name)
            .apply()
    }

    override fun loadUserInfos(): UserInfo {
        val weight = sharedPref.getFloat(IPreferences.WEIGHT_KEY, -1f)
        val height = sharedPref.getInt(IPreferences.HEIGHT_KEY, -1)
        val age = sharedPref.getInt(IPreferences.AGE_KEY, -1)
        val gender = sharedPref.getString(IPreferences.GENDER_KEY, null)
        val activityLevel = sharedPref.getString(IPreferences.ACTIVITY_LEVEL_KEY, null)
        val goalType = sharedPref.getString(IPreferences.GOAL_TYPE_KEY, null)
        val carbRatio = sharedPref.getFloat(IPreferences.CARB_RATIO_KEY, -1f)
        val fatRatio = sharedPref.getFloat(IPreferences.FAT_RATIO_KEY, -1f)
        val proteinRatio = sharedPref.getFloat(IPreferences.PROTEIN_RATIO_KEY, -1f)
        return UserInfo(
            age = age,
            weight = weight,
            height = height,
            carbRatio = carbRatio,
            fatRatio = fatRatio,
            proteinRatio = proteinRatio,
            gender = Gender.fromString(gender ?: "female"),
            goalType = GoalType.fromString(goalType ?: "keep_weight"),
            activityLevel = ActivityLevel.genderFromString(
                activityLevel ?: "medium"
            )
        )
    }
}