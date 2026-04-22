package com.kennedy.markethub.ui.screens.about

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kennedy.markethub.navigation.ROUTE_Home

import com.kennedy.markethub.ui.theme.Borange

// ─── Color Palette ────────────────────────────────────────────────────────────
private val OrangePrimary  = Borange
private val OrangeDark     = Color(0xFFD84315)
private val OrangeLight    = Color(0xFFFFCCBC)
private val SurfaceWhite   = Color(0xFFFFFBF8)
private val CardSurface    = Color(0xFFFFFFFF)
private val TextPrimary    = Color(0xFF1A1A2E)
private val TextSecondary  = Color(0xFF5C5C7A)
private val DividerColor   = Color(0xFFEEEEEE)
private val BadgeRed       = Color(0xFFE53935)
private val SuccessGreen   = Color(0xFF2E7D32)

// ─── Data Models ──────────────────────────────────────────────────────────────
data class StatItem(val value: String, val label: String, val icon: ImageVector)
data class FeatureItem(val icon: ImageVector, val title: String, val description: String)
data class TeamMember(val name: String, val role: String, val initial: String, val color: Color)

// ─── Main Screen ──────────────────────────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navController: NavController,
    cartItemCount: Int = 3   // Pass real cart count from ViewModel
) {
    val scrollState = rememberScrollState()
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { visible = true }

    Scaffold(
        containerColor = SurfaceWhite,

        // ── TopAppBar ──────────────────────────────────────────────────────────
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "About MarketHub",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(44.dp)
                            .clickable { navController.navigate(ROUTE_Home) },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.ShoppingCart,
                            contentDescription = "Cart",
                            tint = Color.White,
                            modifier = Modifier.size(26.dp)
                        )
                        if (cartItemCount > 0) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .size(18.dp)
                                    .clip(CircleShape)
                                    .background(BadgeRed),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = if (cartItemCount > 9) "9+" else "$cartItemCount",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = OrangePrimary,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },

        bottomBar = {
            BottomNavBar(navController = navController, cartItemCount = cartItemCount)
        }

    ) { paddingValues ->

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(tween(600)) + slideInVertically(tween(600)) { it / 4 }
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {

                HeroBanner()

                Spacer(modifier = Modifier.height(24.dp))

                SectionCard(title = "Our Mission") {
                    Text(
                        text = "MarketHub empowers buyers and sellers across Africa and beyond with a secure, " +
                                "fast, and intuitive commerce platform. We bridge the gap between local enterprises " +
                                "and global markets — delivering trust, transparency, and technology at scale.",
                        fontSize = 14.sp,
                        color = TextSecondary,
                        lineHeight = 22.sp
                    )
                }

                SectionCard(title = "MarketHub by the Numbers") {
                    val stats = listOf(
                        StatItem("2M+",  "Active Users", Icons.Default.Person),
                        StatItem("50K+", "Sellers",      Icons.Default.Home),
                        StatItem("5M+",  "Products",     Icons.Default.ShoppingCart),
                        StatItem("4.8★", "App Rating",   Icons.Default.Star),
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        stats.forEach { stat ->
                            StatCard(stat = stat, modifier = Modifier.weight(1f))
                        }
                    }
                }

                SectionCard(title = "Why Choose MarketHub?") {
                    val features = listOf(
                        FeatureItem(Icons.Default.Home,      "Secure Payments",   "End-to-end encrypted transactions with multi-currency support."),
                        FeatureItem(Icons.Default.Person, "Fast Delivery",      "Same-day and next-day delivery in supported regions."),
                        FeatureItem(Icons.Default.Phone,      "Verified Sellers",  "All merchants undergo rigorous onboarding and KYC verification."),
                        FeatureItem(Icons.Default.AccountBox,  "24/7 Support",      "Round-the-clock customer care via chat, phone, and email."),
                        FeatureItem(Icons.Default.Add,      "Best Deals",        "Daily flash sales, coupons, and loyalty rewards programs."),
                        FeatureItem(Icons.Default.Build,     "Seller Analytics",  "Real-time dashboards and insights for enterprise merchants."),
                    )
                    features.forEachIndexed { index, feature ->
                        FeatureRow(feature = feature)
                        if (index < features.lastIndex) HorizontalDivider(color = DividerColor, thickness = 0.8.dp)
                    }
                }

                SectionCard(title = "Leadership Team") {
                    val team = listOf(
                        TeamMember("Kennedy Muchiri",  "Founder & CEO",          "KM", OrangePrimary),
                        TeamMember("Amara Osei",       "Chief Technology Officer","AO", Color(0xFF1565C0)),
                        TeamMember("Priya Sharma",     "Head of Product",         "PS", SuccessGreen),
                        TeamMember("Fatima Al-Hassan", "VP of Operations",        "FA", Color(0xFF6A1B9A)),
                    )
                    team.forEachIndexed { index, member ->
                        TeamMemberRow(member = member)
                        if (index < team.lastIndex) HorizontalDivider(color = DividerColor, thickness = 0.8.dp)
                    }
                }

                SectionCard(title = "Company Information") {
                    CompanyInfoRow(Icons.Default.AccountCircle,      "Legal Name",    "MarketHub Technologies Ltd.")
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp)
                    CompanyInfoRow(Icons.Default.LocationOn,    "Headquarters",  "Nairobi, Kenya — East Africa")
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp)
                    CompanyInfoRow(Icons.Default.Email,         "Support Email", "support@markethub.com")
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp)
                    CompanyInfoRow(Icons.Default.Phone,         "Phone",         "+254 700 000 000")
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp)
                    CompanyInfoRow(Icons.Default.Check,      "Website",       "www.markethub.com")
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp)
                    CompanyInfoRow(Icons.Default.Person, "Founded",       "2021")
                }

                SectionCard(title = "Legal & Policies") {
                    LegalLinkRow("Privacy Policy",         Icons.Default.Lock)
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp)
                    LegalLinkRow("Terms of Service",       Icons.Default.Home)
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp)
                    LegalLinkRow("Cookie Policy",          Icons.Default.Person)
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp)
                    LegalLinkRow("Seller Agreement",       Icons.Default.Add)
                    HorizontalDivider(color = DividerColor, thickness = 0.8.dp)
                    LegalLinkRow("Return & Refund Policy", Icons.Default.Phone)
                }

                AppVersionFooter()
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

// ─── Hero Banner ──────────────────────────────────────────────────────────────
@Composable
private fun HeroBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Brush.verticalGradient(colors = listOf(OrangePrimary, OrangeDark))),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White.copy(alpha = 0.15f))
                    .border(2.dp, Color.White.copy(alpha = 0.4f), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Build,
                    contentDescription = "MarketHub Logo",
                    tint = Color.White,
                    modifier = Modifier.size(42.dp)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("MarketHub", fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, color = Color.White, letterSpacing = 1.sp)
            Text("Enterprise Commerce Platform", fontSize = 12.sp, color = Color.White.copy(alpha = 0.85f), letterSpacing = 0.5.sp)
        }
    }
}

// ─── Section Card Wrapper ─────────────────────────────────────────────────────
@Composable
private fun SectionCard(title: String, content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = title.uppercase(),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = OrangePrimary,
            letterSpacing = 1.5.sp,
            modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = CardSurface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) { content() }
        }
    }
}

// ─── Stat Card ────────────────────────────────────────────────────────────────
@Composable
private fun StatCard(stat: StatItem, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(44.dp).clip(CircleShape).background(OrangeLight),
            contentAlignment = Alignment.Center
        ) {
            Icon(stat.icon, contentDescription = null, tint = OrangeDark, modifier = Modifier.size(22.dp))
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(stat.value, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp, color = TextPrimary)
        Text(stat.label, fontSize = 10.sp, color = TextSecondary, textAlign = TextAlign.Center)
    }
}

// ─── Feature Row ─────────────────────────────────────────────────────────────
@Composable
private fun FeatureRow(feature: FeatureItem) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(40.dp).clip(RoundedCornerShape(10.dp)).background(OrangeLight),
            contentAlignment = Alignment.Center
        ) {
            Icon(feature.icon, contentDescription = null, tint = OrangeDark, modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(feature.title, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = TextPrimary)
            Text(feature.description, fontSize = 12.sp, color = TextSecondary, lineHeight = 18.sp)
        }
    }
}

// ─── Team Member Row ──────────────────────────────────────────────────────────
@Composable
private fun TeamMemberRow(member: TeamMember) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(44.dp).clip(CircleShape).background(member.color),
            contentAlignment = Alignment.Center
        ) {
            Text(member.initial, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color.White)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(member.name, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = TextPrimary)
            Text(member.role, fontSize = 12.sp, color = TextSecondary)
        }
    }
}

// ─── Company Info Row ─────────────────────────────────────────────────────────
@Composable
private fun CompanyInfoRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = OrangePrimary, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(label, fontSize = 11.sp, color = TextSecondary, fontWeight = FontWeight.Medium)
            Text(value, fontSize = 14.sp, color = TextPrimary, fontWeight = FontWeight.SemiBold)
        }
    }
}

// ─── Legal Link Row ───────────────────────────────────────────────────────────
@Composable
private fun LegalLinkRow(title: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Navigate to respective policy screen */ }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = OrangePrimary, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(title, fontSize = 14.sp, color = TextPrimary, modifier = Modifier.weight(1f))
        Icon(Icons.Default.Create, contentDescription = null, tint = TextSecondary)
    }
}

// ─── App Version Footer ───────────────────────────────────────────────────────
@Composable
private fun AppVersionFooter() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Version 2.4.1 (Build 2024110)", fontSize = 12.sp, color = TextSecondary)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            "© 2024 MarketHub Technologies Ltd. All rights reserved.",
            fontSize = 11.sp,
            color = TextSecondary.copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}

// ─── Bottom Navigation Bar ────────────────────────────────────────────────────
@Composable
private fun BottomNavBar(navController: NavController, cartItemCount: Int) {
    var selectedIndex by remember { mutableStateOf(3) }

    NavigationBar(containerColor = OrangePrimary) {

        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.White) },
            label = { Text("Home", color = Color.White, fontSize = 10.sp) },
            selected = selectedIndex == 0,
            onClick = { selectedIndex = 0; navController.navigate(ROUTE_Home) }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Wishlist", tint = Color.White) },
            label = { Text("Wishlist", color = Color.White, fontSize = 10.sp) },
            selected = selectedIndex == 1,
            onClick = { selectedIndex = 1 }
        )

        NavigationBarItem(
            icon = {
                Box {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = Color.White)
                    if (cartItemCount > 0) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(16.dp)
                                .clip(CircleShape)
                                .background(BadgeRed),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (cartItemCount > 9) "9+" else "$cartItemCount",
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }
            },
            label = { Text("Cart", color = Color.White, fontSize = 10.sp) },
            selected = selectedIndex == 2,
            onClick = { selectedIndex = 2; navController.navigate(ROUTE_Home) }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.White) },
            label = { Text("Profile", color = Color.White, fontSize = 10.sp) },
            selected = selectedIndex == 3,
            onClick = { selectedIndex = 3 }
        )
    }
}

// ─── Preview ──────────────────────────────────────────────────────────────────
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(rememberNavController(), cartItemCount = 3)
}