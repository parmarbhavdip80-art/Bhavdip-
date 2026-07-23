package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyDao {
    @Query("SELECT * FROM quiz_attempts ORDER BY timestamp DESC")
    fun getAllAttempts(): Flow<List<QuizAttempt>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttempt(attempt: QuizAttempt): Long

    @Query("SELECT * FROM bookmarked_questions ORDER BY timestamp DESC")
    fun getAllBookmarks(): Flow<List<BookmarkedQuestion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkedQuestion)

    @Query("DELETE FROM bookmarked_questions WHERE questionId = :questionId")
    suspend fun deleteBookmark(questionId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarked_questions WHERE questionId = :questionId)")
    fun isBookmarked(questionId: String): Flow<Boolean>
}
