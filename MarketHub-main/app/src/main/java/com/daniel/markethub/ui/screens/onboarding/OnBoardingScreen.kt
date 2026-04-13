package com.daniel.markethub.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.daniel.markethub.R
import com.daniel.markethub.navigation.ROUTE_Login
import com.daniel.markethub.navigation.ROUTE_Register
import com.daniel.markethub.ui.theme.Maroon


@Composable
fun OnBoardingScreen(navController: NavController){

    Column(

        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center



    ) {

        Image(

            painter = painterResource(R.drawable.img),
            contentDescription = "Product",
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))


        Text(

            text = "WELCOME TO MARKETHUB",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Green,
            fontFamily = FontFamily.Monospace



        )

        Text(
            text = "Shop Smarter",
            fontSize = 20.sp,
            fontWeight = FontWeight.Thin,
            color = Color.Black

        )


        Text(
            text = "WELCOME TO MARKETHUB\n\nMarketHub is your all-in-one digital marketplace designed to connect buyers and sellers بسهولة and efficiently. Whether you're looking for fresh products, electronics, fashion, or everyday essentials, MarketHub provides a seamless and reliable platform to explore a wide variety of items.\n\nWith a user-friendly interface, secure transactions, and real-time updates, MarketHub ensures that your shopping experience is smooth and enjoyable. Sellers can easily showcase their products, reach a wider audience, and grow their businesses, while buyers get access to quality products at competitive prices.\n\nJoin MarketHub today and experience the future of smart, convenient, and connected shopping.",
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Button(
            onClick = {
                navController.navigate(ROUTE_Register)
            },
            colors = ButtonDefaults.buttonColors(Maroon),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.width(350.dp)
        ) {
            Text(
                text = "Get Started"
            )

        }






















    }





}


@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview(){

    OnBoardingScreen(rememberNavController())


}