package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_attempts")
data class QuizAttempt(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val scoreSectionA: Int,
    val totalSectionA: Int = 10,
    val percentage: Float,
    val mode: String = "EXAM",
    val studentName: String = "ભવદીપ"
)
