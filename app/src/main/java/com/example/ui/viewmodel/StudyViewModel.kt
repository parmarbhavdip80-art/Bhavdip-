package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.BookmarkedQuestion
import com.example.data.local.QuizAttempt
import com.example.data.model.*
import com.example.data.repository.StudyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StudyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StudyRepository

    init {
        val dao = AppDatabase.getDatabase(application).studyDao()
        repository = StudyRepository(dao)
        observeData()
    }

    // State Holders
    private val _userAnswers = MutableStateFlow<Map<String, String>>(emptyMap())
    val userAnswers: StateFlow<Map<String, String>> = _userAnswers.asStateFlow()

    private val _sectionAScore = MutableStateFlow(0)
    val sectionAScore: StateFlow<Int> = _sectionAScore.asStateFlow()

    private val _quizGraded = MutableStateFlow(false)
    val quizGraded: StateFlow<Boolean> = _quizGraded.asStateFlow()

    private val _isStudyMode = MutableStateFlow(false)
    val isStudyMode: StateFlow<Boolean> = _isStudyMode.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedChapterFilter = MutableStateFlow<Int?>(null)
    val selectedChapterFilter: StateFlow<Int?> = _selectedChapterFilter.asStateFlow()

    private val _currentFlashcardIndex = MutableStateFlow(0)
    val currentFlashcardIndex: StateFlow<Int> = _currentFlashcardIndex.asStateFlow()

    private val _isFlashcardFlipped = MutableStateFlow(false)
    val isFlashcardFlipped: StateFlow<Boolean> = _isFlashcardFlipped.asStateFlow()

    private val _selectedMapLocation = MutableStateFlow<MapLocation?>(null)
    val selectedMapLocation: StateFlow<MapLocation?> = _selectedMapLocation.asStateFlow()

    private val _attemptsHistory = MutableStateFlow<List<QuizAttempt>>(emptyList())
    val attemptsHistory: StateFlow<List<QuizAttempt>> = _attemptsHistory.asStateFlow()

    private val _bookmarksList = MutableStateFlow<List<BookmarkedQuestion>>(emptyList())
    val bookmarksList: StateFlow<List<BookmarkedQuestion>> = _bookmarksList.asStateFlow()

    // Loaded Data
    val questions: List<QuestionItem> by lazy { repository.getModelPaperQuestions() }
    val chapters: List<ChapterInfo> by lazy { repository.getChapters() }
    val flashcards: List<FlashcardItem> by lazy { repository.getFlashcards() }
    val mapLocations: List<MapLocation> by lazy { repository.getMapLocations() }

    private fun observeData() {
        viewModelScope.launch {
            repository.allAttempts.collectLatest {
                _attemptsHistory.value = it
            }
        }
        viewModelScope.launch {
            repository.allBookmarks.collectLatest {
                _bookmarksList.value = it
            }
        }
    }

    // --- Quiz Actions ---
    fun selectAnswer(questionId: String, answer: String) {
        _userAnswers.value = _userAnswers.value.toMutableMap().apply {
            put(questionId, answer)
        }
    }

    fun gradeSectionA() {
        val secAQuestions = questions.filter { it.section == "A" }
        var score = 0

        for (q in secAQuestions) {
            val userAns = _userAnswers.value[q.id]?.trim() ?: ""
            if (userAns.isNotEmpty()) {
                if (q.questionType == QuestionType.FILL_BLANK) {
                    // Check if answer contains expected keywords (e.g. ગીર or જમીન)
                    if (q.id == "secA_q5" && (userAns.contains("ગીર") || userAns.contains("ગિર") || userAns.contains("Gir"))) {
                        score++
                    } else if (q.id == "secA_q6" && (userAns.contains("જમીન") || userAns.contains("જમી") || userAns.contains("Land"))) {
                        score++
                    }
                } else if (userAns == q.correctValue) {
                    score++
                }
            }
        }

        _sectionAScore.value = score
        _quizGraded.value = true
        _isStudyMode.value = true // Automatically reveal solutions in study mode

        viewModelScope.launch {
            repository.saveAttempt(score)
        }
    }

    fun toggleStudyMode() {
        _isStudyMode.value = !_isStudyMode.value
    }

    fun setStudyMode(enabled: Boolean) {
        _isStudyMode.value = enabled
    }

    fun resetQuiz() {
        _userAnswers.value = emptyMap()
        _sectionAScore.value = 0
        _quizGraded.value = false
        _isStudyMode.value = false
    }

    // --- Search & Filter ---
    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setChapterFilter(chapterNumber: Int?) {
        _selectedChapterFilter.value = chapterNumber
    }

    // --- Flashcards ---
    fun nextFlashcard() {
        if (_currentFlashcardIndex.value < flashcards.size - 1) {
            _currentFlashcardIndex.value++
            _isFlashcardFlipped.value = false
        }
    }

    fun prevFlashcard() {
        if (_currentFlashcardIndex.value > 0) {
            _currentFlashcardIndex.value--
            _isFlashcardFlipped.value = false
        }
    }

    fun flipFlashcard() {
        _isFlashcardFlipped.value = !_isFlashcardFlipped.value
    }

    // --- Map Locations ---
    fun selectMapLocation(location: MapLocation?) {
        _selectedMapLocation.value = location
    }

    // --- Bookmarks ---
    fun toggleBookmark(question: QuestionItem) {
        viewModelScope.launch {
            val isBookmarkedCurrently = _bookmarksList.value.any { it.questionId == question.id }
            if (isBookmarkedCurrently) {
                repository.removeBookmark(question.id)
            } else {
                repository.toggleBookmark(question)
            }
        }
    }

    fun removeBookmark(questionId: String) {
        viewModelScope.launch {
            repository.removeBookmark(questionId)
        }
    }

    fun isQuestionBookmarked(questionId: String): Boolean {
        return _bookmarksList.value.any { it.questionId == questionId }
    }
}
