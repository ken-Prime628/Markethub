package com.kennedy.markethub.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kennedy.markethub.ui.screens.about.AboutScreen
import com.kennedy.markethub.ui.screens.auth.LoginScreen
import com.kennedy.markethub.ui.screens.auth.RegisterScreen
import com.kennedy.markethub.ui.screens.home.HomeScreen
import com.kennedy.markethub.ui.screens.intent.IntentScreen
import com.kennedy.markethub.ui.screens.onboarding.OnboardingScreen
import com.kennedy.markethub.ui.screens.payments.PaymentScreen
import com.kennedy.markethub.ui.screens.products.AddProductScreen
import com.kennedy.markethub.ui.screens.products.UpdateProductScreen
import com.kennedy.markethub.ui.screens.products.ViewProductScreen
import com.kennedy.markethub.ui.screens.scaffold.ScaffoldScreen
import com.kennedy.markethub.ui.screens.service.ServiceScreen
import com.kennedy.markethub.ui.screens.splash.SplashScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_Splash
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
            OnboardingScreen(navController)
        }

        composable(ROUTE_Payments) {
            PaymentScreen(navController)
        }
        composable(ROUTE_Service) {
            ServiceScreen(navController)
        }

        composable(ROUTE_Intent) {
            IntentScreen(navController)
        }

        composable(ROUTE_Splash) {
            SplashScreen(navController)
        }

        composable(ROUTE_Scaffold) {
            ScaffoldScreen(navController)
        }

        composable(ROUTE_ADD_PRODUCT) { AddProductScreen(navController) }

        composable(ROUTE_VIEW_PRODUCTS) { ViewProductScreen(navController) }

        composable(
            ROUTE_UPDATE_PRODUCT,
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")!!
            UpdateProductScreen(navController, productId)
        }






    }
}