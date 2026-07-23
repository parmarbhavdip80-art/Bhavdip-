package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.StudyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashcardsScreen(
    viewModel: StudyViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val flashcards = viewModel.flashcards
    val currentIndex by viewModel.currentFlashcardIndex.collectAsState()
    val isFlipped by viewModel.isFlashcardFlipped.collectAsState()

    val currentCard = flashcards.getOrNull(currentIndex) ?: flashcards.first()

    Scaffold(
        containerColor = BentoBackground,
        topBar = {
            TopAppBar(
                title = { Text("ઝડપી પુનરાવર્તન ફ્લેશકાર્ડ્સ", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = BentoOnBackground) },
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
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Counter Progress
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "કાર્ડ ${currentIndex + 1} / ${flashcards.size}",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 14.sp,
                    color = BentoPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = (currentIndex + 1).toFloat() / flashcards.size.toFloat(),
                    color = BentoPrimary,
                    trackColor = BentoOutline,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
            }

            // Main Interactive Flip Card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (isFlipped) BentoBlueCard else BentoLilacCard
                ),
                shape = RoundedCornerShape(28.dp),
                border = androidx.compose.foundation.BorderStroke(
                    2.dp,
                    if (isFlipped) BentoBlueCardBorder else BentoLilacCardBorder
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .clickable { viewModel.flipFlashcard() }
                    .testTag("flashcard_flip_area")
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Surface(
                            color = BentoPrimary,
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = if (isFlipped) "ઉત્તર (Answer)" else "પ્રશ્ન / કન્સેપ્ટ (Question)",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = if (isFlipped) currentCard.answer else currentCard.question,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 18.sp,
                            lineHeight = 26.sp,
                            textAlign = TextAlign.Center,
                            color = BentoOnSurface
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.TouchApp,
                                contentDescription = null,
                                tint = BentoPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "કાર્ડ ફ્લિપ કરવા ટેપ કરો",
                                fontSize = 11.sp,
                                color = BentoOnSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Navigation Control Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { viewModel.prevFlashcard() },
                    enabled = currentIndex > 0,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = BentoPrimary
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "અગાઉનું", fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { viewModel.nextFlashcard() },
                    enabled = currentIndex < flashcards.size - 1,
                    colors = ButtonDefaults.buttonColors(containerColor = BentoPrimary),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "આગળનું", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                }
            }
        }
    }
}
