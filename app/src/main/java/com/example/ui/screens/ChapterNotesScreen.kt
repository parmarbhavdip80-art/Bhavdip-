package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ChapterInfo
import com.example.ui.theme.*
import com.example.ui.viewmodel.StudyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterNotesScreen(
    viewModel: StudyViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val chapters = viewModel.chapters
    val selectedFilter by viewModel.selectedChapterFilter.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    val filteredChapters = chapters.filter { chapter ->
        val matchesFilter = selectedFilter == null || chapter.number == selectedFilter
        val matchesSearch = searchQuery.isEmpty() ||
                chapter.titleGujarati.contains(searchQuery, ignoreCase = true) ||
                chapter.overview.contains(searchQuery, ignoreCase = true) ||
                chapter.keyPoints.any { it.contains(searchQuery, ignoreCase = true) }
        matchesFilter && matchesSearch
    }

    Scaffold(
        containerColor = BentoBackground,
        topBar = {
            TopAppBar(
                title = { Text("પ્રકરણ વાઇઝ આઈએમપી નોટ્સ", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = BentoOnBackground) },
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
            // Search Field
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.setSearchQuery(it) },
                placeholder = { Text("પ્રકરણ કે કીવર્ડ શોધો...", fontSize = 12.sp, color = BentoOnSurfaceVariant) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = BentoPrimary) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = BentoSurface,
                    unfocusedContainerColor = BentoSurface,
                    focusedBorderColor = BentoPrimary,
                    unfocusedBorderColor = BentoOutline
                )
            )

            // Chapter Filter Row
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = selectedFilter == null,
                        onClick = { viewModel.setChapterFilter(null) },
                        label = { Text("બધા (૬ પ્રકરણ)", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = BentoPrimary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
                items(chapters) { ch ->
                    FilterChip(
                        selected = selectedFilter == ch.number,
                        onClick = { viewModel.setChapterFilter(if (selectedFilter == ch.number) null else ch.number) },
                        label = { Text("પ્રકરણ ${ch.number}", fontSize = 11.sp, fontWeight = FontWeight.Bold) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = BentoPrimary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            // Chapter List
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(filteredChapters) { chapter ->
                    ChapterCard(chapter = chapter)
                }
            }
        }
    }
}

@Composable
fun ChapterCard(chapter: ChapterInfo) {
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
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        color = BentoPrimary,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "પ્રકરણ ${chapter.number}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    Text(
                        text = chapter.subjectCategory,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = BentoSecondary
                    )
                }
            }

            Text(
                text = chapter.titleGujarati,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = BentoOnSurface
            )

            Text(
                text = chapter.overview,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = BentoOnSurfaceVariant
            )

            Divider(color = BentoOutline)

            Text(
                text = "📌 અગત્યના કી પોઇન્ટ્સ:",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = BentoPrimary
            )

            chapter.keyPoints.forEach { point ->
                Row(verticalAlignment = Alignment.Top) {
                    Text("• ", fontWeight = FontWeight.Bold, color = BentoPrimary)
                    Text(text = point, fontSize = 12.sp, lineHeight = 16.sp, color = BentoOnSurface)
                }
            }

            Divider(color = BentoOutline)

            Text(
                text = "🔥 મોસ્ટ રીપીટેડ બોર્ડ પ્રશ્નો:",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = BentoSecondary
            )

            chapter.impTopics.forEach { topic ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Book,
                        contentDescription = null,
                        tint = BentoSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = topic, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = BentoOnSurface)
                }
            }
        }
    }
}
