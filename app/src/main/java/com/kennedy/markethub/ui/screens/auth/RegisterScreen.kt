package com.kennedy.markethub.ui.screens.auth

import android.media.MediaRouter2
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.kennedy.markethub.data.AuthViewModel
import com.kennedy.markethub.navigation.ROUTE_Login
import com.kennedy.markethub.ui.theme.Borange

@Composable
fun RegisterScreen(navController: NavController){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painter = painterResource(R.drawable.img_1), contentScale = ContentScale.FillBounds),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(R.drawable.re),
            contentDescription = "product",
            modifier = Modifier.size(300.dp)
        )

        Text(
            text = "Join Us and Start Your Journey Today",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Borange
        )

        Spacer(modifier = Modifier.height(20.dp))

        //Variables
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmpassword by remember { mutableStateOf("") }


        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.padding(start = 20.dp, end = 20.dp).fillMaxWidth(),
            leadingIcon = {Icon(imageVector = Icons.Default.Person, contentDescription = "")},
            label = { Text(text = "Username") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Borange,
                focusedBorderColor = Borange,
                unfocusedLeadingIconColor = Borange,

            )



        )

        Spacer(modifier = Modifier.height(20.dp))



        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(start = 20.dp, end = 20.dp).fillMaxWidth(),
            leadingIcon = {Icon(imageVector = Icons.Default.Email, contentDescription = "")},
            label = { Text(text = "Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Borange,
                focusedBorderColor = Borange,
                unfocusedLeadingIconColor = Borange,

                )



        )

        Spacer(modifier = Modifier.height(20.dp))




        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(start = 20.dp, end = 20.dp).fillMaxWidth(),
            leadingIcon = {Icon(imageVector = Icons.Default.Lock, contentDescription = "")},
            label = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Borange,
                focusedBorderColor = Borange,
                unfocusedLeadingIconColor = Borange,

                ),
            visualTransformation = PasswordVisualTransformation()



        )

        Spacer(modifier = Modifier.height(20.dp))





        OutlinedTextField(
            value = confirmpassword,
            onValueChange = { confirmpassword = it },
            modifier = Modifier.padding(start = 20.dp, end = 20.dp).fillMaxWidth(),
            leadingIcon = {Icon(imageVector = Icons.Default.Lock, contentDescription = "")},
            label = { Text(text = "Confirmpassword") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Borange,
                focusedBorderColor = Borange,
                unfocusedLeadingIconColor = Borange,

                ),
            visualTransformation = PasswordVisualTransformation()



        )


        Spacer(modifier = Modifier.height(20.dp))



        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context)
        Button(
            onClick = {

                authViewModel.signup(username, email, password,confirmpassword)


            },
            colors = ButtonDefaults.buttonColors(Borange),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.width(350.dp)


            ) {
            Text(text = "Register Now")
        }

        Spacer(modifier = Modifier.height(20.dp))

        TextButton(onClick = {
        navController.navigate(ROUTE_Login)
        }) {
            Text(text = "Already Have An Account?Login")
        }
























    }


}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview(){

    RegisterScreen(rememberNavController())

}