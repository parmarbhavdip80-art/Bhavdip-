package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.BookmarkedQuestion
import com.example.data.local.QuizAttempt
import com.example.ui.theme.*
import com.example.ui.viewmodel.StudyViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryBookmarksScreen(
    viewModel: StudyViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val attempts by viewModel.attemptsHistory.collectAsState()
    val bookmarks by viewModel.bookmarksList.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        containerColor = BentoBackground,
        topBar = {
            TopAppBar(
                title = { Text("સ્કોર ઇતિહાસ અને બુકમાર્ક", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = BentoOnBackground) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = BentoOnBackground)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BentoBackground)
            )
        },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Tab Selector
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = BentoSurface,
                contentColor = BentoPrimary
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("ટેસ્ટ ઇતિહાસ (${attempts.size})", fontWeight = FontWeight.Bold, fontSize = 13.sp) }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("બુકમાર્ક્સ (${bookmarks.size})", fontWeight = FontWeight.Bold, fontSize = 13.sp) }
                )
            }

            if (selectedTab == 0) {
                if (attempts.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "હજી સુધી કોઈ ટેસ્ટ પ્રયાસ રેકોર્ડ થયો નથી.\nમોડેલ પેપર વિભાગ A સોલ્વ કરો!",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = 80.dp)
                    ) {
                        items(attempts) { attempt ->
                            AttemptHistoryCard(attempt = attempt)
                        }
                    }
                }
            } else {
                if (bookmarks.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "કોઈ પ્રશ્ન બુકમાર્ક કરેલ નથી.\nમોડેલ પેપરમાંથી કોઈપણ પ્રશ્ન સેવ કરો!",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = 80.dp)
                    ) {
                        items(bookmarks) { bookmark ->
                            BookmarkCard(
                                bookmark = bookmark,
                                onDelete = { viewModel.removeBookmark(bookmark.questionId) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AttemptHistoryCard(attempt: QuizAttempt) {
    val dateStr = remember(attempt.timestamp) {
        val sdf = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
        sdf.format(Date(attempt.timestamp))
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = BentoSurface),
        shape = RoundedCornerShape(24.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BentoOutline),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    color = BentoLilacCard,
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.size(44.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = BentoPrimary
                        )
                    }
                }

                Column {
                    Text(
                        text = "વિભાગ A ટેસ્ટ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = BentoOnSurface
                    )
                    Text(
                        text = dateStr,
                        fontSize = 11.sp,
                        color = BentoOnSurfaceVariant
                    )
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${attempt.scoreSectionA} / ${attempt.totalSectionA} ગુણ",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    color = BentoPrimary
                )
                Text(
                    text = "${attempt.percentage.toInt()}% પરિણામ",
                    fontSize = 11.sp,
                    color = BentoOnSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun BookmarkCard(bookmark: BookmarkedQuestion, onDelete: () -> Unit) {
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = BentoPrimary,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "વિભાગ ${bookmark.section} • પ્રશ્ન ${bookmark.questionNumber}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                IconButton(onClick = onDelete, modifier = Modifier.size(24.dp)) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remove Bookmark",
                        tint = BentoSecondary
                    )
                }
            }

            Text(
                text = bookmark.questionText,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                color = BentoOnSurface
            )

            SolutionBlock(modelAnswer = bookmark.modelAnswer)
        }
    }
}
