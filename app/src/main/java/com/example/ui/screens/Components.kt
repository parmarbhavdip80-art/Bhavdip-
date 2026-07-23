package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun StudyModeBanner(
    isStudyMode: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isStudyMode) BentoLilacCard else BentoPrimaryContainer
        ),
        shape = RoundedCornerShape(24.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BentoLilacCardBorder),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(BentoPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isStudyMode) Icons.Default.School else Icons.Default.EditNote,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Column {
                    Text(
                        text = if (isStudyMode) "અભ્યાસ / સ્ટડી મોડ (ચાલુ)" else "પરીક્ષા મોડ (ચાલુ)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = BentoOnSurface
                    )
                    Text(
                        text = if (isStudyMode) "બધા જવાબો અને આદર્શ મુદ્દાઓ દર્શાવેલા છે." else "જવાબો છુપાયેલા છે. તમે પેપર સોલ્વ કરી શકો છો.",
                        fontSize = 11.sp,
                        color = BentoOnSurfaceVariant
                    )
                }
            }

            Button(
                onClick = onToggle,
                colors = ButtonDefaults.buttonColors(
                    containerColor = BentoPrimary
                ),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                modifier = Modifier.testTag("toggle_study_mode_btn")
            ) {
                Icon(
                    imageVector = if (isStudyMode) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (isStudyMode) "છુપાવો" else "દર્શાવો",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SolutionBlock(
    modelAnswer: String,
    bulletPoints: List<String> = emptyList(),
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = BentoLilacCard
        ),
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BentoLilacCardBorder),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.CheckCircle,
                    contentDescription = null,
                    tint = BentoPrimary,
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = "આદર્શ ઉત્તર અને મુદ્દાઓ:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = BentoPrimary
                )
            }

            Text(
                text = modelAnswer,
                fontSize = 12.sp,
                lineHeight = 18.sp,
                color = BentoOnSurface
            )

            if (bulletPoints.isNotEmpty()) {
                Divider(
                    color = BentoLilacCardBorder,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                bulletPoints.forEach { point ->
                    Row(
                        modifier = Modifier.padding(vertical = 2.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "• ",
                            fontWeight = FontWeight.Bold,
                            color = BentoPrimary,
                            fontSize = 12.sp
                        )
                        Text(
                            text = point,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = BentoOnSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SectionHeaderBadge(
    sectionTitle: String,
    totalMarks: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = BentoSurface),
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BentoOutline),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = sectionTitle,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 14.sp,
                color = BentoOnSurface
            )
            Surface(
                color = BentoPrimary,
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = totalMarks,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
        }
    }
}
