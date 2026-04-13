package com.daniel.markethub.navigation





import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daniel.markethub.ui.screens.about.AboutScreen
import com.daniel.markethub.ui.screens.auth.LoginScreen
import com.daniel.markethub.ui.screens.auth.RegisterScreen
import com.daniel.markethub.ui.screens.home.HomeScreen
import com.daniel.markethub.ui.screens.onboarding.OnBoardingScreen
import com.daniel.markethub.ui.screens.payments.PaymentScreen
import com.daniel.markethub.ui.screens.service.ServiceScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_OnBoarding
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUTE_Home) {
            HomeScreen(navController)
        }
        composable(ROUTE_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUTE_Login) {
            LoginScreen(navController)
        }
        composable(ROUTE_Register) {
            RegisterScreen(navController)
        }
        composable(ROUTE_OnBoarding) {
            OnBoardingScreen(navController)
        }

        composable(ROUTE_Payments) {
            PaymentScreen(navController)
        }
        composable(ROUTE_Service) {
            ServiceScreen(navController)
        }





    }
}