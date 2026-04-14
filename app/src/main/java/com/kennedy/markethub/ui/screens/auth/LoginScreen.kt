package com.kennedy.markethub.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kennedy.markethub.R
import com.kennedy.markethub.navigation.ROUTE_Home
import com.kennedy.markethub.navigation.ROUTE_Register

import com.kennedy.markethub.ui.theme.Borange

@Composable
fun LoginScreen(navController: NavController){

    Column(

        modifier = Modifier
            .paint(painter = painterResource(R.drawable.img_1), contentScale = ContentScale.FillBounds)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center





    ) {


        Image(

            painter = painterResource(R.drawable.re),
            contentDescription = "Product",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))




        Text(
            text = "Welcome Back,",
            fontSize = 20.sp, fontWeight = FontWeight.Medium,


            )
        Spacer(modifier = Modifier.height(20.dp))


        //Variables

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        OutlinedTextField(
            value = email ,
            onValueChange = { email = it},
            modifier = Modifier.width(350.dp),
            leadingIcon = {  Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "email")  },

            label = {Text(text = "Enter Email")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Gray


            )





        )


        Spacer(modifier = Modifier.height(10.dp))



        OutlinedTextField(
            value = password ,
            onValueChange = { password = it},
            modifier = Modifier.width(350.dp),
            leadingIcon = {  Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "password")  },

            label = {Text(text = "Enter password")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Gray


            ),
            visualTransformation = PasswordVisualTransformation()





        )


        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(Borange),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.width(350.dp)
        ) {
            Text(
                text = "Login"
            )

        }

        Spacer(modifier = Modifier.height(10.dp))


        TextButton(onClick = {
            navController.navigate(ROUTE_Register)
        }) {
            Text(text = "Don't have an account? Register")
        }

        TextButton(onClick = {
        navController.navigate(ROUTE_Home)
        }) {
            Text(text = "Go to Home")
        }









    }






}


@Preview(showBackground = true)
@Composable
fun ServiceScreenPreview(){

    LoginScreen(rememberNavController())


}