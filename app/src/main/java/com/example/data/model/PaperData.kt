package com.example.data.model

data class QuestionOption(
    val text: String,
    val isCorrect: Boolean
)

enum class QuestionType {
    MATCH,
    TRUE_FALSE,
    FILL_BLANK,
    MCQ,
    SHORT_ANSWER,
    MEDIUM_ANSWER,
    LONG_ANSWER,
    MAP_POINT
}

data class QuestionItem(
    val id: String,
    val questionNumber: Int,
    val section: String, // "A", "B", "C", "D"
    val chapter: String,
    val questionType: QuestionType,
    val questionText: String,
    val options: List<QuestionOption> = emptyList(),
    val hint: String? = null,
    val correctValue: String = "",
    val modelAnswer: String = "",
    val bulletPoints: List<String> = emptyList(),
    val marks: Int = 1
)

data class MatchPair(
    val leftId: Int,
    val leftText: String,
    val correctRightText: String,
    val distractorRightText: String
)

data class MapLocation(
    val id: String,
    val title: String,
    val category: String, // "Soil", "National Park", "Heritage Port"
    val regionText: String,
    val state: String,
    val description: String,
    val relativeX: Float, // 0.0 to 1.0 on map graphic
    val relativeY: Float  // 0.0 to 1.0 on map graphic
)

data class ChapterInfo(
    val number: Int,
    val titleGujarati: String,
    val titleEnglish: String,
    val subjectCategory: String, // "ઇતિહાસ (History)", "ભૂગોળ (Geography)", "અર્થશાસ્ત્ર (Economics)"
    val overview: String,
    val keyPoints: List<String>,
    val impTopics: List<String>
)

data class FlashcardItem(
    val id: Int,
    val chapter: Int,
    val question: String,
    val answer: String,
    val category: String
)
