package com.example.core.domain.model

sealed class ActivityLevel(val name: String) {
    object Low: ActivityLevel(name = "low")
    object Medium: ActivityLevel(name = "medium")
    object High: ActivityLevel(name = "high")

    companion object {
        fun genderFromString(name: String): ActivityLevel {
            return when(name) {
                "low" -> Low
                "medium" -> Medium
                "high" -> High
                else -> Medium
            }
        }
    }
}
