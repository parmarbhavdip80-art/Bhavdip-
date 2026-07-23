package com.example.ui.navigation

sealed class Screen(val route: String, val titleGujarati: String) {
    object Home : Screen("home", "મુખ્ય પૃષ્ઠ")
    object ModelPaper : Screen("model_paper", "મોડેલ પેપર (૪૦ ગુણ)")
    object ChapterNotes : Screen("chapter_notes", "પ્રકરણ નોટ્સ")
    object MapPractice : Screen("map_practice", "નક્શાપૂર્તિ")
    object Flashcards : Screen("flashcards", "ફ્લેશકાર્ડ્સ")
    object History : Screen("history", "સ્કોર અને બુકમાર્ક")
}
