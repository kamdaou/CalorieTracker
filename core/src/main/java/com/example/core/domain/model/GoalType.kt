package com.example.core.domain.model

sealed class GoalType(val name: String) {
    object GainWeight: GoalType(name = "gain_weight")
    object LoseWeight: GoalType(name = "lose_weight")
    object KeepWeight: GoalType(name = "keep_weight")

    companion object {
        fun fromString(name: String): GoalType {
            return when(name) {
                "gain_weight" -> GainWeight
                "lose_weight" -> LoseWeight
                "keep_weight" -> KeepWeight
                else -> KeepWeight
            }
        }
    }
}
