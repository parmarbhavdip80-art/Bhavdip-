package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarked_questions")
data class BookmarkedQuestion(
    @PrimaryKey val questionId: String,
    val section: String,
    val questionNumber: Int,
    val questionText: String,
    val modelAnswer: String,
    val chapter: String,
    val timestamp: Long = System.currentTimeMillis()
)
