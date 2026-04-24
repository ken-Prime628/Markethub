package com.example.restaurantbooking.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

// ─────────────────────────────────────────────
//  DESIGN TOKENS
// ─────────────────────────────────────────────
private val Cream      = Color(0xFFFDF6EC)
private val Espresso   = Color(0xFF1C0F07)
private val Mahogany   = Color(0xFF7B3822)
private val Amber      = Color(0xFFD4843E)
private val Gold       = Color(0xFFC9A84C)
private val Sage       = Color(0xFF8A9E7E)
private val Blush      = Color(0xFFF5DDD1)
private val CardBg     = Color(0xFFFFFFFF)
private val Divider    = Color(0xFFEDE0D4)

// ─────────────────────────────────────────────
//  DATA MODELS
// ─────────────────────────────────────────────
data class TimeSlot(val time: String, val period: String, val available: Boolean = true)
data class TableOption(val icon: ImageVector, val label: String, val size: String)

// ─────────────────────────────────────────────
//  MAIN SCREEN
// ─────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RestaurantBookingScreen(
    onBack: () -> Unit = {},
    onConfirm: (date: LocalDate, time: String, guests: Int, table: String) -> Unit = { _, _, _, _ -> }
) {
    // ── State ──
    val today = LocalDate.now()
    val days = remember { (0..13).map { today.plusDays(it.toLong()) } }

    var selectedDate   by remember { mutableStateOf(today) }
    var selectedTime   by remember { mutableStateOf<TimeSlot?>(null) }
    var guestCount     by remember { mutableIntStateOf(2) }
    var selectedTable  by remember { mutableStateOf<TableOption?>(null) }
    var specialRequest by remember { mutableStateOf("") }
    var showConfirm    by remember { mutableStateOf(false) }

    val timeSlots = remember {
        listOf(
            TimeSlot("12:00", "PM"), TimeSlot("12:30", "PM"),
            TimeSlot("01:00", "PM"), TimeSlot("01:30", "PM"), TimeSlot("02:00", "PM", false),
            TimeSlot("06:00", "PM"), TimeSlot("06:30", "PM"), TimeSlot("07:00", "PM"),
            TimeSlot("07:30", "PM"), TimeSlot("08:00", "PM"), TimeSlot("08:30", "PM"),
            TimeSlot("09:00", "PM", false)
        )
    }

    val tableOptions = remember {
        listOf(
            TableOption(Icons.Outlined.TableRestaurant, "Indoor",  "2–4 seats"),
            TableOption(Icons.Outlined.Deck,            "Terrace", "2–6 seats"),
            TableOption(Icons.Outlined.LocalBar,        "Bar",     "1–2 seats"),
            TableOption(Icons.Outlined.FamilyRestroom,  "Private", "6–12 seats")
        )
    }

    val allSelected = selectedTime != null && selectedTable != null

    Scaffold(
        containerColor = Cream,
        topBar = {
            BookingTopBar(onBack = onBack)
        },
        bottomBar = {
            ConfirmBar(
                enabled  = allSelected,
                date     = selectedDate,
                time     = selectedTime?.let { "${it.time} ${it.period}" } ?: "—",
                guests   = guestCount,
                onConfirm = {
                    if (allSelected) showConfirm = true
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {

            // Hero Banner
            HeroBanner()

            Spacer(Modifier.height(24.dp))

            // Section: Date Picker
            SectionLabel("Select a Date")
            Spacer(Modifier.height(12.dp))
            DatePickerRow(days = days, selected = selectedDate, onSelect = { selectedDate = it })

            Spacer(Modifier.height(28.dp))

            // Section: Guests
            SectionLabel("Number of Guests")
            Spacer(Modifier.height(12.dp))
            GuestCounter(count = guestCount, onDecrement = { if (guestCount > 1) guestCount-- }, onIncrement = { if (guestCount < 20) guestCount++ })

            Spacer(Modifier.height(28.dp))

            // Section: Time Slots
            SectionLabel("Available Times")
            Spacer(Modifier.height(12.dp))
            TimeSlotGrid(slots = timeSlots, selected = selectedTime, onSelect = { selectedTime = it })

            Spacer(Modifier.height(28.dp))

            // Section: Table Type
            SectionLabel("Table Preference")
            Spacer(Modifier.height(12.dp))
            TableSelector(options = tableOptions, selected = selectedTable, onSelect = { selectedTable = it })

            Spacer(Modifier.height(28.dp))

            // Section: Special Requests
            SectionLabel("Special Requests")
            Spacer(Modifier.height(12.dp))
            SpecialRequestField(value = specialRequest, onChange = { specialRequest = it })

            Spacer(Modifier.height(100.dp)) // space for bottom bar
        }
    }

    // Confirmation dialog
    if (showConfirm) {
        ConfirmationDialog(
            date   = selectedDate,
            time   = "${selectedTime!!.time} ${selectedTime!!.period}",
            guests = guestCount,
            table  = selectedTable!!.label,
            onDismiss = { showConfirm = false },
            onConfirm = {
                showConfirm = false
                onConfirm(selectedDate, "${selectedTime!!.time} ${selectedTime!!.period}", guestCount, selectedTable!!.label)
            }
        )
    }
}

// ─────────────────────────────────────────────
//  TOP BAR
// ─────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookingTopBar(onBack: () -> Unit) {
    TopAppBar(
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().padding(end = 48.dp)) {
                Text("Reserve a Table", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Espresso)
                Text("Osteria La Luna", fontSize = 13.sp, color = Mahogany, fontWeight = FontWeight.Medium)
            }
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Espresso)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Cream),
    )
}

// ─────────────────────────────────────────────
//  HERO BANNER
// ─────────────────────────────────────────────
@Composable
private fun HeroBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        // Gradient placeholder for restaurant image
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(Color(0xFF3B1A0B), Color(0xFF7B3822), Color(0xFFD4843E))
                    )
                )
        )
        // Dark overlay bottom scrim
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xCC1C0F07)),
                        startY = 60f
                    )
                )
        )
        // Bottom info row
        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Italian Fine Dining", color = Color(0xFFD4843E), fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                Text("Via Roma 12 · Amsterdam", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Gold, modifier = Modifier.size(14.dp))
                Spacer(Modifier.width(4.dp))
                Text("4.8", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Text(" (312)", color = Color.White.copy(alpha = 0.6f), fontSize = 12.sp)
            }
        }
        // Decorative label top-right
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFFD4843E).copy(alpha = 0.9f),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
        ) {
            Text("Open Tonight", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp))
        }
    }
}

// ─────────────────────────────────────────────
//  SECTION LABEL
// ─────────────────────────────────────────────
@Composable
private fun SectionLabel(text: String) {
    Text(
        text,
        modifier = Modifier.padding(horizontal = 20.dp),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Espresso
    )
}

// ─────────────────────────────────────────────
//  DATE PICKER ROW
// ─────────────────────────────────────────────
@Composable
private fun DatePickerRow(
    days: List<LocalDate>,
    selected: LocalDate,
    onSelect: (LocalDate) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(days) { day ->
            val isSelected = day == selected
            val isToday = day == LocalDate.now()
            val animColor by animateColorAsState(
                if (isSelected) Mahogany else CardBg,
                animationSpec = tween(200), label = "dateBg"
            )
            Column(
                modifier = Modifier
                    .width(58.dp)
                    .shadow(if (isSelected) 6.dp else 1.dp, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(animColor)
                    .clickable { onSelect(day) }
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    day.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).uppercase(),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (isSelected) Color.White.copy(0.7f) else Color(0xFFAA9080)
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    day.dayOfMonth.toString(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (isSelected) Color.White else Espresso
                )
                Spacer(Modifier.height(4.dp))
                if (isToday) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Color.White else Amber)
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────
//  GUEST COUNTER
// ─────────────────────────────────────────────
@Composable
private fun GuestCounter(count: Int, onDecrement: () -> Unit, onIncrement: () -> Unit) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = CardBg,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.People, contentDescription = null, tint = Mahogany, modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Guests", fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = Espresso)
                Text("Select number of people", fontSize = 12.sp, color = Color(0xFFAA9080))
            }
            // Decrement
            FilledIconButton(
                onClick = onDecrement,
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Blush),
                modifier = Modifier.size(36.dp)
            ) {
                Text("−", fontSize = 20.sp, color = Mahogany, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.width(16.dp))
            Text(
                count.toString(),
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Espresso,
                modifier = Modifier.widthIn(min = 28.dp),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.width(16.dp))
            // Increment
            FilledIconButton(
                onClick = onIncrement,
                colors = IconButtonDefaults.filledIconButtonColors(containerColor = Mahogany),
                modifier = Modifier.size(36.dp)
            ) {
                Text("+", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// ─────────────────────────────────────────────
//  TIME SLOT GRID
// ─────────────────────────────────────────────
@Composable
private fun TimeSlotGrid(
    slots: List<TimeSlot>,
    selected: TimeSlot?,
    onSelect: (TimeSlot) -> Unit
) {
    val chunked = remember(slots) { slots.chunked(4) }
    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        chunked.forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                row.forEach { slot ->
                    val isSelected = slot == selected
                    val animBg by animateColorAsState(
                        when {
                            !slot.available -> Color(0xFFF0EBE6)
                            isSelected -> Mahogany
                            else -> CardBg
                        },
                        label = "slotBg"
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .shadow(if (isSelected) 4.dp else 1.dp, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .background(animBg)
                            .then(
                                if (slot.available) Modifier.clickable { onSelect(slot) }
                                else Modifier
                            )
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                slot.time,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = when {
                                    !slot.available -> Color(0xFFBBAA9E)
                                    isSelected -> Color.White
                                    else -> Espresso
                                }
                            )
                            Text(
                                slot.period,
                                fontSize = 11.sp,
                                color = when {
                                    !slot.available -> Color(0xFFBBAA9E)
                                    isSelected -> Color.White.copy(0.7f)
                                    else -> Color(0xFFAA9080)
                                }
                            )
                            if (!slot.available) {
                                Text("Full", fontSize = 10.sp, color = Color(0xFFCCBBAA))
                            }
                        }
                    }
                }
                // Fill remaining cells in last row
                repeat(4 - row.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

// ─────────────────────────────────────────────
//  TABLE SELECTOR
// ─────────────────────────────────────────────
@Composable
private fun TableSelector(
    options: List<TableOption>,
    selected: TableOption?,
    onSelect: (TableOption) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(options) { option ->
            val isSelected = option == selected
            val animBg by animateColorAsState(
                if (isSelected) Espresso else CardBg,
                label = "tableBg"
            )
            Column(
                modifier = Modifier
                    .width(100.dp)
                    .shadow(if (isSelected) 6.dp else 1.dp, RoundedCornerShape(18.dp))
                    .clip(RoundedCornerShape(18.dp))
                    .background(animBg)
                    .clickable { onSelect(option) }
                    .padding(vertical = 16.dp, horizontal = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color.White.copy(0.12f) else Blush),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        option.icon,
                        contentDescription = option.label,
                        tint = if (isSelected) Color.White else Mahogany,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    option.label,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = if (isSelected) Color.White else Espresso,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    option.size,
                    fontSize = 10.sp,
                    color = if (isSelected) Color.White.copy(0.6f) else Color(0xFFAA9080),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// ─────────────────────────────────────────────
//  SPECIAL REQUEST FIELD
// ─────────────────────────────────────────────
@Composable
private fun SpecialRequestField(value: String, onChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        placeholder = {
            Text(
                "Allergies, celebrations, seating preferences…",
                color = Color(0xFFBBAA9E),
                fontSize = 14.sp
            )
        },
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Mahogany,
            unfocusedBorderColor = Divider,
            focusedContainerColor = CardBg,
            unfocusedContainerColor = CardBg,
            cursorColor = Mahogany
        ),
        minLines = 3,
        maxLines = 5,
        leadingIcon = {
            Icon(Icons.Outlined.EditNote, contentDescription = null, tint = Mahogany)
        }
    )
}

// ─────────────────────────────────────────────
//  CONFIRM BOTTOM BAR
// ─────────────────────────────────────────────
@Composable
private fun ConfirmBar(
    enabled: Boolean,
    date: LocalDate,
    time: String,
    guests: Int,
    onConfirm: () -> Unit
) {
    Surface(
        color = Cream,
        shadowElevation = 16.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp)) {
            // Summary pill
            AnimatedVisibility(visible = enabled) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Blush,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SummaryChip(icon = Icons.Outlined.CalendarToday,
                            label = "${date.dayOfMonth} ${date.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH)}")
                        SummaryChip(icon = Icons.Outlined.Schedule, label = time)
                        SummaryChip(icon = Icons.Outlined.People, label = "$guests guests")
                    }
                }
            }
            // CTA Button
            Button(
                onClick = onConfirm,
                enabled = enabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Mahogany,
                    disabledContainerColor = Color(0xFFD6C4BB)
                )
            ) {
                Text(
                    if (enabled) "Confirm Reservation" else "Select time & table to continue",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun SummaryChip(icon: ImageVector, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Mahogany, modifier = Modifier.size(14.dp))
        Spacer(Modifier.width(5.dp))
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Espresso, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

// ─────────────────────────────────────────────
//  CONFIRMATION DIALOG
// ─────────────────────────────────────────────
@Composable
private fun ConfirmationDialog(
    date: LocalDate,
    time: String,
    guests: Int,
    table: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Cream,
        shape = RoundedCornerShape(24.dp),
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text("🍽️", fontSize = 36.sp)
                Spacer(Modifier.height(8.dp))
                Text("Almost There!", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = Espresso)
                Text("Please confirm your booking details", fontSize = 13.sp, color = Color(0xFFAA9080), textAlign = TextAlign.Center)
            }
        },
        text = {
            Surface(shape = RoundedCornerShape(16.dp), color = Blush) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    DialogRow(icon = Icons.Outlined.CalendarToday, label = "Date",
                        value = "${date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)}, ${date.dayOfMonth} ${date.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)}")
                    HorizontalDivider(color = Divider)
                    DialogRow(icon = Icons.Outlined.Schedule, label = "Time", value = time)
                    HorizontalDivider(color = Divider)
                    DialogRow(icon = Icons.Outlined.People, label = "Guests", value = "$guests people")
                    HorizontalDivider(color = Divider)
                    DialogRow(icon = Icons.Outlined.TableRestaurant, label = "Seating", value = "$table area")
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Mahogany)
            ) {
                Text("Book Now", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(vertical = 4.dp))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss, modifier = Modifier.fillMaxWidth()) {
                Text("Edit Details", color = Mahogany, fontWeight = FontWeight.SemiBold)
            }
        }
    )
}

@Composable
private fun DialogRow(icon: ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = Mahogany, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(10.dp))
        Column {
            Text(label, fontSize = 11.sp, color = Color(0xFFAA9080), fontWeight = FontWeight.Medium)
            Text(value, fontSize = 14.sp, color = Espresso, fontWeight = FontWeight.SemiBold)
        }
    }
}

// ─────────────────────────────────────────────
//  PREVIEW
// ─────────────────────────────────────────────
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RestaurantBookingScreenPreview() {
    MaterialTheme {
        RestaurantBookingScreen()
    }
}