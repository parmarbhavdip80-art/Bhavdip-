package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.QuestionItem
import com.example.data.model.QuestionType
import com.example.ui.theme.*
import com.example.ui.viewmodel.StudyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelPaperScreen(
    viewModel: StudyViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isStudyMode by viewModel.isStudyMode.collectAsState()
    val userAnswers by viewModel.userAnswers.collectAsState()
    val quizGraded by viewModel.quizGraded.collectAsState()
    val scoreSectionA by viewModel.sectionAScore.collectAsState()
    val questions = viewModel.questions

    Scaffold(
        containerColor = BentoBackground,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "મોડેલ પ્રશ્નપત્ર (૪૦ ગુણ)",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            color = BentoOnBackground
                        )
                        Text(
                            text = "પ્રકરણ ૧, ૨, ૩, ૮, ૯, ૧૫",
                            fontSize = 11.sp,
                            color = BentoOnSurfaceVariant
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = BentoOnBackground)
                    }
                },
                actions = {
                    TextButton(onClick = { viewModel.toggleStudyMode() }) {
                        Icon(
                            imageVector = if (isStudyMode) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = null,
                            tint = BentoPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isStudyMode) "પરીક્ષા" else "અભ્યાસ",
                            fontWeight = FontWeight.Bold,
                            color = BentoPrimary,
                            fontSize = 12.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BentoBackground
                )
            )
        },
        modifier = modifier
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // Header Board Card
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = BentoSurface),
                    shape = RoundedCornerShape(24.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BentoOutline),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "ગુજરાત માધ્યમિક અને ઉચ્ચતર માધ્યમિક શિક્ષણ બોર્ડ, ગાંધીનગર",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoOnSurfaceVariant
                        )
                        Text(
                            text = "પ્રથમ સામાયિક કસોટી / એકમ કસોટી (૨૦૨૫-૨૬)",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = BentoPrimary,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        Text(
                            text = "વિષય: સામાજિક વિજ્ઞાન (કોડ: 10)",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoSecondary
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(BentoLilacCard, RoundedCornerShape(16.dp))
                                .border(1.dp, BentoLilacCardBorder, RoundedCornerShape(16.dp))
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(text = "ધોરણ: ૧૦", fontSize = 11.sp, fontWeight = FontWeight.ExtraBold, color = BentoOnSurface)
                            Text(text = "કુલ ગુણ: ૪૦", fontSize = 11.sp, fontWeight = FontWeight.ExtraBold, color = BentoOnSurface)
                            Text(text = "સમય: ૧:૩૦ કલાક", fontSize = 11.sp, fontWeight = FontWeight.ExtraBold, color = BentoOnSurface)
                        }
                    }
                }
            }

            // Study Mode Banner
            item {
                StudyModeBanner(
                    isStudyMode = isStudyMode,
                    onToggle = { viewModel.toggleStudyMode() }
                )
            }

            // General Instructions
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = WarmSurfaceVariant),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = AmberPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "સામાન્ય સૂચનાઓ:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = AmberOnContainer
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "• ચાર વિભાગો (A, B, C, D) આપેલા છે. બધા પ્રશ્નો ફરજિયાત છે.", fontSize = 11.sp)
                        Text(text = "• વિભાગ D માં આપેલ નક્શાપૂર્તિ (પ્રશ્ન ૨૧) ફરજિયાત છે.", fontSize = 11.sp)
                    }
                }
            }

            // SECTION A Header
            item {
                SectionHeaderBadge(
                    sectionTitle = "વિભાગ A : હેતુલક્ષી પ્રશ્નો (ઓનલાઇન ક્વિઝ)",
                    totalMarks = "૧૦ ગુણ"
                )
            }

            // Section A Questions (Q1 to Q10)
            val secAQuestions = questions.filter { it.section == "A" }
            items(secAQuestions) { question ->
                QuestionItemView(
                    question = question,
                    userAnswer = userAnswers[question.id] ?: "",
                    isStudyMode = isStudyMode || quizGraded,
                    onSelectAnswer = { ans -> viewModel.selectAnswer(question.id, ans) },
                    isBookmarked = viewModel.isQuestionBookmarked(question.id),
                    onToggleBookmark = { viewModel.toggleBookmark(question) }
                )
            }

            // Section A Score Check Button
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = AmberContainer),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "વિભાગ A સ્કોર કેલ્ક્યુલેટર",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = AmberOnContainer
                        )
                        Text(
                            text = "બધા ૧૦ હેતુલક્ષી પ્રશ્નોના ઉત્તરો આપ્યા પછી સ્કોર ચકાસો.",
                            fontSize = 11.sp,
                            color = AmberOnContainer.copy(alpha = 0.8f)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            onClick = { viewModel.gradeSectionA() },
                            colors = ButtonDefaults.buttonColors(containerColor = AmberPrimary),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("grade_section_a_btn")
                        ) {
                            Icon(imageVector = Icons.Default.EmojiEvents, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "સ્કોર ચકાસો",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }

                        if (quizGraded) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Surface(
                                color = EmeraldContainer,
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text(
                                            text = "અભિનંદન ભવદીપ! તમારો સ્કોર:",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            color = EmeraldOnContainer
                                        )
                                        Text(
                                            text = "$scoreSectionA / ૧૦ ગુણ",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 18.sp,
                                            color = EmeraldTertiary
                                        )
                                    }

                                    Button(
                                        onClick = { viewModel.resetQuiz() },
                                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldTertiary),
                                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                                    ) {
                                        Text(text = "ફરી પ્રયાસ કરો", fontSize = 11.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // SECTION B Header
            item {
                SectionHeaderBadge(
                    sectionTitle = "વિભાગ B : ટૂંકજવાબી પ્રશ્નો (દરેકના ૨ ગુણ)",
                    totalMarks = "૧૦ ગુણ"
                )
            }

            val secBQuestions = questions.filter { it.section == "B" }
            items(secBQuestions) { question ->
                QuestionItemView(
                    question = question,
                    userAnswer = "",
                    isStudyMode = isStudyMode,
                    onSelectAnswer = {},
                    isBookmarked = viewModel.isQuestionBookmarked(question.id),
                    onToggleBookmark = { viewModel.toggleBookmark(question) }
                )
            }

            // SECTION C Header
            item {
                SectionHeaderBadge(
                    sectionTitle = "વિભાગ C : મુદ્દાસર પ્રશ્નો (દરેકના ૩ ગુણ)",
                    totalMarks = "૧૨ ગુણ"
                )
            }

            val secCQuestions = questions.filter { it.section == "C" }
            items(secCQuestions) { question ->
                QuestionItemView(
                    question = question,
                    userAnswer = "",
                    isStudyMode = isStudyMode,
                    onSelectAnswer = {},
                    isBookmarked = viewModel.isQuestionBookmarked(question.id),
                    onToggleBookmark = { viewModel.toggleBookmark(question) }
                )
            }

            // SECTION D Header
            item {
                SectionHeaderBadge(
                    sectionTitle = "વિભાગ D : સવિસ્તાર પ્રશ્ન અને નક્શાપૂર્તિ",
                    totalMarks = "૦૮ ગુણ"
                )
            }

            val secDQuestions = questions.filter { it.section == "D" }
            items(secDQuestions) { question ->
                QuestionItemView(
                    question = question,
                    userAnswer = "",
                    isStudyMode = isStudyMode,
                    onSelectAnswer = {},
                    isBookmarked = viewModel.isQuestionBookmarked(question.id),
                    onToggleBookmark = { viewModel.toggleBookmark(question) }
                )
            }
        }
    }
}

@Composable
fun QuestionItemView(
    question: QuestionItem,
    userAnswer: String,
    isStudyMode: Boolean,
    onSelectAnswer: (String) -> Unit,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = BentoSurface),
        shape = RoundedCornerShape(24.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BentoOutline),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Question Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = BentoPrimaryContainer,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = question.chapter,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = BentoOnPrimaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "[${question.marks} ગુણ]",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = BentoPrimary
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = question.questionText,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp,
                        lineHeight = 22.sp,
                        color = BentoOnSurface
                    )
                }

                IconButton(
                    onClick = onToggleBookmark,
                    modifier = Modifier.testTag("bookmark_${question.id}")
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) BentoPrimary else Color.Gray
                    )
                }
            }

            // Question Interactive Body based on type
            when (question.questionType) {
                QuestionType.MATCH, QuestionType.MCQ -> {
                    Spacer(modifier = Modifier.height(10.dp))
                    question.options.forEach { option ->
                        val isSelected = userAnswer == option.text
                        Surface(
                            color = if (isSelected) BentoLilacCard else BentoSurface,
                            shape = RoundedCornerShape(16.dp),
                            border = androidx.compose.foundation.BorderStroke(
                                2.dp,
                                if (isSelected) BentoPrimary else BentoOutline
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { onSelectAnswer(option.text) }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = isSelected,
                                    onClick = { onSelectAnswer(option.text) },
                                    colors = RadioButtonDefaults.colors(selectedColor = BentoPrimary)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = option.text,
                                    fontSize = 13.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = BentoOnSurface
                                )
                            }
                        }
                    }
                }

                QuestionType.TRUE_FALSE -> {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        question.options.forEach { option ->
                            val isSelected = userAnswer == option.text
                            Surface(
                                color = if (isSelected) BentoLilacCard else BentoSurface,
                                shape = RoundedCornerShape(16.dp),
                                border = androidx.compose.foundation.BorderStroke(
                                    2.dp,
                                    if (isSelected) BentoPrimary else BentoOutline
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { onSelectAnswer(option.text) }
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = { onSelectAnswer(option.text) },
                                        colors = RadioButtonDefaults.colors(selectedColor = BentoPrimary)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = option.text,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = BentoOnSurface
                                    )
                                }
                            }
                        }
                    }
                }

                QuestionType.FILL_BLANK -> {
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = userAnswer,
                        onValueChange = { onSelectAnswer(it) },
                        placeholder = { Text("તમારો જવાબ અહીં લખો...", fontSize = 12.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp)
                    )
                    question.hint?.let { hint ->
                        Text(
                            text = hint,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                            modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                        )
                    }
                }

                else -> {
                    // For Short, Medium, Long Answer & Map
                }
            }

            // Solution block when Study Mode is enabled
            if (isStudyMode) {
                SolutionBlock(
                    modelAnswer = question.modelAnswer,
                    bulletPoints = question.bulletPoints
                )
            }
        }
    }
}
