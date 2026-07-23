package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MapLocation
import com.example.ui.theme.*
import com.example.ui.viewmodel.StudyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapPracticeScreen(
    viewModel: StudyViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mapLocations = viewModel.mapLocations
    val selectedLocation by viewModel.selectedMapLocation.collectAsState()

    Scaffold(
        containerColor = BentoBackground,
        topBar = {
            TopAppBar(
                title = { Text("વિભાગ D : નક્શાપૂર્તિ માર્ગદર્શિકા", fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = BentoOnBackground) },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            // Intro Card
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = BentoBlueCard),
                    shape = RoundedCornerShape(24.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BentoBlueCardBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = null,
                            tint = BentoPrimary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "ભારતના રેખાંકિત નક્શાપૂર્તિ પ્રશ્નો (૪ ગુણ)",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 13.sp,
                                color = BentoOnSurface
                            )
                            Text(
                                text = "બોર્ડ પરીક્ષા માટે મોસ્ટ આઈએમપી ૪ સ્થળો અને પ્રદેશો દર્શાવેલા છે.",
                                fontSize = 11.sp,
                                color = BentoOnSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Interactive Visual Map Canvas
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = BentoSurface),
                    shape = RoundedCornerShape(24.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, BentoOutline),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "🗺️ ભારત અને ગુજરાતનો રેખાંકિત નકશો:",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 14.sp,
                            color = BentoPrimary
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFE0F2FE)) // Light map water blue background
                        ) {
                            // Custom Stylized Map Outline Canvas
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                val w = size.width
                                val h = size.height

                                // Draw India Stylized Map Contour
                                val mapPath = Path().apply {
                                    moveTo(w * 0.35f, h * 0.15f) // North
                                    lineTo(w * 0.45f, h * 0.20f)
                                    lineTo(w * 0.55f, h * 0.22f)
                                    lineTo(w * 0.85f, h * 0.35f) // North East
                                    lineTo(w * 0.82f, h * 0.48f)
                                    lineTo(w * 0.60f, h * 0.45f) // Central East
                                    lineTo(w * 0.50f, h * 0.85f) // South Tip
                                    lineTo(w * 0.38f, h * 0.65f) // South West
                                    lineTo(w * 0.22f, h * 0.50f) // Gujarat Peninsular Gulf
                                    lineTo(w * 0.20f, h * 0.40f)
                                    close()
                                }

                                drawPath(
                                    path = mapPath,
                                    color = Color(0xFFFEF3C7) // Sand land color
                                )
                                drawPath(
                                    path = mapPath,
                                    color = Color(0xFFD97706),
                                    style = Stroke(width = 3f)
                                )

                                // Highlight Regions
                                // Black Soil Region (Gujarat/Maharashtra)
                                drawCircle(
                                    color = Color(0xFF262626).copy(alpha = 0.3f),
                                    radius = 35f,
                                    center = Offset(w * 0.32f, h * 0.58f)
                                )

                                // Alluvial Soil Region (UP/Ganga Plains)
                                drawCircle(
                                    color = Color(0xFF854D0E).copy(alpha = 0.3f),
                                    radius = 45f,
                                    center = Offset(w * 0.55f, h * 0.32f)
                                )
                            }

                            // Interactive Markers Overlay
                            mapLocations.forEach { loc ->
                                val isSelected = selectedLocation?.id == loc.id
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            start = (loc.relativeX * 280).dp,
                                            top = (loc.relativeY * 200).dp
                                        )
                                ) {
                                    Surface(
                                        color = if (isSelected) TerracottaSecondary else AmberPrimary,
                                        shape = CircleShape,
                                        shadowElevation = 4.dp,
                                        modifier = Modifier
                                            .size(if (isSelected) 36.dp else 28.dp)
                                            .clickable { viewModel.selectMapLocation(loc) }
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Icon(
                                                imageVector = Icons.Default.LocationOn,
                                                contentDescription = loc.title,
                                                tint = Color.White,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Text(
                            text = "ટીપ: નકશામાં અથવા નીચેના લિસ્ટમાંથી પિન પર ક્લિક કરો.",
                            fontSize = 11.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            // Selected Location Detail Card
            selectedLocation?.let { loc ->
                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = TerracottaContainer),
                        shape = RoundedCornerShape(16.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, TerracottaSecondary),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    color = TerracottaSecondary,
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text(
                                        text = loc.category,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                                Text(
                                    text = "રાજ્ય: ${loc.state}",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = TerracottaOnContainer
                                )
                            }

                            Text(
                                text = loc.title,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 16.sp,
                                color = TerracottaOnContainer
                            )

                            Text(
                                text = "સ્થાન: ${loc.regionText}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = TerracottaOnContainer.copy(alpha = 0.9f)
                            )

                            Text(
                                text = loc.description,
                                fontSize = 12.sp,
                                lineHeight = 18.sp,
                                color = TerracottaOnContainer.copy(alpha = 0.85f)
                            )
                        }
                    }
                }
            }

            // Map Locations List
            item {
                Text(
                    text = "નક્શાપૂર્તિ મોસ્ટ આઈએમપી સ્થળો:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            items(mapLocations) { loc ->
                val isSelected = selectedLocation?.id == loc.id
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) BentoLilacCard else BentoSurface
                    ),
                    shape = RoundedCornerShape(20.dp),
                    border = androidx.compose.foundation.BorderStroke(
                        2.dp,
                        if (isSelected) BentoPrimary else BentoOutline
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.selectMapLocation(loc) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = loc.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = BentoOnSurface
                            )
                            Text(
                                text = "${loc.state} • ${loc.regionText}",
                                fontSize = 11.sp,
                                color = BentoOnSurfaceVariant
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = BentoPrimary
                        )
                    }
                }
            }
        }
    }
}
