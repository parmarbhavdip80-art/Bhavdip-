package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.*
import com.example.ui.viewmodel.StudyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: StudyViewModel,
    onNavigateToModelPaper: () -> Unit,
    onNavigateToChapters: () -> Unit,
    onNavigateToMap: () -> Unit,
    onNavigateToFlashcards: () -> Unit,
    onNavigateToHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    val attempts by viewModel.attemptsHistory.collectAsState()
    val bookmarks by viewModel.bookmarksList.collectAsState()

    Scaffold(
        containerColor = BentoBackground,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "GSEB ધોરણ ૧૦",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = BentoPrimary,
                                letterSpacing = 1.sp
                            )
                            Text(
                                text = "સામાજિક વિજ્ઞાન",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp,
                                color = BentoOnBackground
                            )
                        }

                        Surface(
                            color = BentoPrimaryContainer,
                            shape = RoundedCornerShape(20.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BentoLilacCardBorder)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .clip(CircleShape)
                                        .background(BentoPrimary)
                                )
                                Text(
                                    text = "લાઇવ ટેસ્ટ",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BentoOnPrimaryContainer
                                )
                            }
                        }
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
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // Bento Grid Top Row: Student Profile Card & Time/Timer Bento Cell
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Profile Bento Cell (Span 3 equivalent)
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = BentoSurface),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BentoOutline),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        modifier = Modifier.weight(2.8f)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Surface(
                                color = BentoPrimary,
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.size(44.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "ભ",
                                        color = Color.White,
                                        fontWeight = FontWeight.Black,
                                        fontSize = 20.sp
                                    )
                                }
                            }
                            Column {
                                Text(
                                    text = "વિદ્યાર્થી",
                                    fontSize = 11.sp,
                                    color = BentoOnSurfaceVariant,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "ભવદીપ મકવાણા",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = BentoOnSurface
                                )
                            }
                        }
                    }

                    // Quick Time/Marks Bento Cell (Span 1 equivalent)
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = BentoLilacCard),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BentoLilacCardBorder),
                        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        modifier = Modifier
                            .weight(1.2f)
                            .height(72.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "સમય",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = BentoPrimary
                            )
                            Text(
                                text = "૧:૩૦ કલાક",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Black,
                                color = BentoOnSurface
                            )
                        }
                    }
                }
            }

            // Student Welcome & Hero Banner Bento Card
            item {
                Card(
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(containerColor = BentoSurface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BentoOutline),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_hero_banner_1784770262076),
                                contentDescription = "Social Science Banner",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))
                                        )
                                    )
                            )
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(16.dp)
                            ) {
                                Surface(
                                    color = BentoPrimary,
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = "માત્ર ૬ આઈએમપી પ્રકરણો",
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "🏛️ સામાજિક વિજ્ઞાન સ્માર્ટ પેપર ૨૦૨૬",
                                    color = Color.White,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 18.sp
                                )
                                Text(
                                    text = "ધોરણ ૧૦ બોર્ડ પરીક્ષા ૪૦ ગુણનું આદર્શ મોડેલ પ્રશ્નપત્ર",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 11.sp
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            ChapterChip("પ્રકરણ ૧, ૨, ૩", "ઇતિહાસ")
                            ChapterChip("પ્રકરણ ૮, ૯", "ભૂગોળ")
                            ChapterChip("પ્રકરણ ૧૫", "અર્થશાસ્ત્ર")
                        }
                    }
                }
            }

            // Primary Call-To-Action Bento Card: Start 40 Mark Paper
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = BentoPrimaryContainer),
                    shape = RoundedCornerShape(28.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BentoLilacCardBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigateToModelPaper() }
                        .testTag("start_model_paper_card")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Surface(
                                    color = BentoPrimary,
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = "વિભાગ A, B, C, D",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                                Surface(
                                    color = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = "+૪૦ ગુણ",
                                        color = BentoPrimary,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 10.sp,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "મોડેલ પ્રશ્નપત્ર શરૂ કરો",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 17.sp,
                                color = BentoOnPrimaryContainer
                            )
                            Text(
                                text = "ઓનલાઇન ક્વિઝ + આદર્શ સોલ્યુશન અભ્યાસ મોડ",
                                fontSize = 12.sp,
                                color = BentoOnPrimaryContainer.copy(alpha = 0.8f)
                            )
                        }

                        Surface(
                            color = BentoPrimary,
                            shape = CircleShape,
                            modifier = Modifier.size(46.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }

            // Bento Grid Stats Section: Progress & Accuracy Bento Cards
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Progress Bento Card
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = BentoLilacCard),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BentoLilacCardBorder),
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            val lastScore = attempts.firstOrNull()?.scoreSectionA ?: 0
                            Text(
                                text = "પ્રગતિ",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = BentoOnSurfaceVariant
                            )
                            Row(
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = if (lastScore < 10) "૦$lastScore" else "$lastScore",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Black,
                                    color = BentoOnSurface
                                )
                                Text(
                                    text = "/ ૧૦",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = BentoOnSurfaceVariant,
                                    modifier = Modifier.padding(bottom = 3.dp)
                                )
                            }
                            LinearProgressIndicator(
                                progress = { if (attempts.isNotEmpty()) (lastScore / 10f).coerceIn(0f, 1f) else 0.35f },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .clip(CircleShape),
                                color = BentoPrimary,
                                trackColor = BentoOutline.copy(alpha = 0.4f)
                            )
                        }
                    }

                    // Accuracy Bento Card
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = BentoBlueCard),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BentoBlueCardBorder),
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "ચોકસાઈ",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = BentoOnSurfaceVariant
                            )
                            val accuracy = if (attempts.isNotEmpty()) {
                                val latest = attempts.first()
                                ((latest.scoreSectionA.toFloat() / latest.totalSectionA.coerceAtLeast(1)) * 100).toInt()
                            } else 85
                            Text(
                                text = "$accuracy%",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Black,
                                color = BentoOnSurface
                            )
                            Text(
                                text = "છેલ્લા પ્રશ્નોમાં બોર્ડ ટાર્ગેટ",
                                fontSize = 10.sp,
                                color = BentoOnSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Practice Options Grid Title
            item {
                Text(
                    text = "અભ્યાસ અને તૈયારી મોડ્યુલ",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp,
                    color = BentoOnSurface
                )
            }

            // Module Cards Row 1
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ModuleCard(
                        title = "પ્રકરણ આઈએમપી નોટ્સ",
                        subtitle = "૬ મુખ્ય પ્રકરણોના મુદ્દા",
                        icon = Icons.Default.MenuBook,
                        badgeText = "૬ પ્રકરણ",
                        containerColor = BentoSurface,
                        borderColor = BentoOutline,
                        onClick = onNavigateToChapters,
                        modifier = Modifier.weight(1f)
                    )

                    ModuleCard(
                        title = "નક્શાપૂર્તિ માર્ગદર્શિકા",
                        subtitle = "ગીર, કાઝીરંગા, કાળી જમીન",
                        icon = Icons.Default.Map,
                        badgeText = "૪ સ્થળો",
                        containerColor = BentoBlueCard,
                        borderColor = BentoBlueCardBorder,
                        onClick = onNavigateToMap,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Module Cards Row 2
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ModuleCard(
                        title = "ઝડપી ફ્લેશકાર્ડ્સ",
                        subtitle = "૧૫+ આઈએમપી પ્રશ્નકાર્ડ્સ",
                        icon = Icons.Default.Style,
                        badgeText = "૧૫ કાર્ડ",
                        containerColor = BentoLilacCard,
                        borderColor = BentoLilacCardBorder,
                        onClick = onNavigateToFlashcards,
                        modifier = Modifier.weight(1f)
                    )

                    ModuleCard(
                        title = "સ્કોર અને બુકમાર્ક",
                        subtitle = "${attempts.size} ટેસ્ટ પ્રયાસો, ${bookmarks.size} સેવ",
                        icon = Icons.Default.Bookmark,
                        badgeText = "${bookmarks.size} સેવ",
                        containerColor = BentoSurface,
                        borderColor = BentoOutline,
                        onClick = onNavigateToHistory,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Recent Attempt Quick Stats Bento Card
            if (attempts.isNotEmpty()) {
                item {
                    val lastAttempt = attempts.first()
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = BentoSurface),
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
                            Column {
                                Text(
                                    text = "છેલ્લી ક્વિઝ નો સ્કોર",
                                    fontSize = 11.sp,
                                    color = BentoOnSurfaceVariant,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "વિભાગ A: ${lastAttempt.scoreSectionA} / ${lastAttempt.totalSectionA} ગુણ",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 15.sp,
                                    color = BentoPrimary
                                )
                            }

                            Button(
                                onClick = onNavigateToHistory,
                                colors = ButtonDefaults.buttonColors(containerColor = BentoPrimary),
                                shape = RoundedCornerShape(16.dp)
                            ) {
                                Text(text = "ઇતિહાસ જુઓ", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChapterChip(title: String, subtitle: String) {
    Surface(
        color = BentoLilacCard,
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BentoLilacCardBorder)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = BentoOnSurface)
            Text(text = subtitle, fontSize = 9.sp, color = BentoOnSurfaceVariant)
        }
    }
}

@Composable
fun ModuleCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    badgeText: String,
    containerColor: Color,
    borderColor: Color = BentoOutline,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = containerColor),
        shape = RoundedCornerShape(24.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, borderColor),
        modifier = modifier
            .height(135.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = BentoPrimary,
                    shape = CircleShape,
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BentoOutline)
                ) {
                    Text(
                        text = badgeText,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = BentoPrimary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 13.sp,
                    lineHeight = 17.sp,
                    color = BentoOnSurface
                )
                Text(
                    text = subtitle,
                    fontSize = 10.sp,
                    color = BentoOnSurfaceVariant,
                    maxLines = 1
                )
            }
        }
    }
}
