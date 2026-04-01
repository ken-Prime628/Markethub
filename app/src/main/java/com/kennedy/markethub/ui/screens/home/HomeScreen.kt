package com.kennedy.markethub.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.kennedy.markethub.ui.theme.Borange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(){

    Column(
        modifier = Modifier.fillMaxSize()
    ) {


        TopAppBar(
            title ={ Text(text = "Home")},
            navigationIcon = {
                IconButton(
                    onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    ) }
            },
            
            actions = {
                IconButton(
                    onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = ""
                    ) }

                IconButton(
                    onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = ""
                    ) }

                IconButton(
                    onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = ""
                    ) }
            },

            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Borange,
                navigationIconContentColor = Color.White,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White,
            )

            
        )































    }
    

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){

    HomeScreen()

}