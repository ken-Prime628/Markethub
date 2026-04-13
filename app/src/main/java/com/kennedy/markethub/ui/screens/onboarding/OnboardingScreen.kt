package com.kennedy.markethub.ui.screens.onboarding

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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kennedy.markethub.R
import com.kennedy.markethub.navigation.ROUTE_Register
import com.kennedy.markethub.ui.theme.Borange


@Composable
fun OnboardingScreen(navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.bg1), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //Image

        Image(
            painter = painterResource(R.drawable.products),
            contentDescription = "product",
            modifier = Modifier.size(300.dp)
        )


        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "WELCOME TO MARKETHUB",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Borange,
            fontFamily = FontFamily.Cursive
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Shop Smarter",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold

        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Everywhere You Go",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Our e-commerce application is a modern, user-focused platform designed to deliver a seamless and efficient online shopping experience. It allows users to browse a wide range of products, explore detailed descriptions, and make secure purchases with ease. The platform integrates smart search and filtering features to help customers quickly find what they need, while a responsive design ensures smooth performance across all devices. For sellers, it provides tools to manage inventory, track orders, and analyze sales, creating a complete digital marketplace. By combining intuitive design with powerful functionality, the app enhances convenience, improves customer satisfaction, and supports business growth in the digital economy.",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))


        Button(
            onClick = {
                navController.navigate(ROUTE_Register)

                      },
            colors = ButtonDefaults.buttonColors(Borange),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.width(350.dp)
        ) {
            Text(text = "Get Started")
        }





















    }


}


@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview(){

    OnboardingScreen(rememberNavController())
}