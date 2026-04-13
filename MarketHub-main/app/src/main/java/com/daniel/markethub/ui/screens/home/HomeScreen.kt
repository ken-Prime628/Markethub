package com.daniel.markethub.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.daniel.markethub.R
import com.daniel.markethub.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){

    Column(

        modifier = Modifier.fillMaxSize()



    ) {


          //TopAppBar
        TopAppBar(
            title ={ Text(text = "Home")},
            navigationIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "menu"

                    ) }
            },
            actions = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "add to cart"

                    ) }
                IconButton(
                        onClick = {}
                        ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "notification"

                    ) }
                IconButton(
                        onClick = {}
                        ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "share"

                    ) }

            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Purple80,
                navigationIconContentColor = Color.Black,
                titleContentColor = Color.Black
            )
        )
        //end of TopAppBar

        Spacer(modifier = Modifier.height(20.dp))

        //Searchbar                             //To solve errors press alt+enter key while standing on the line with the issue
        var search by remember { mutableStateOf("") }
        // you can either use OutlinedTextField or just use TextField
        OutlinedTextField(
            value = search ,
            onValueChange = { search = it},
            modifier = Modifier.
                 padding(start = 10.dp,end = 10.dp )
                .fillMaxWidth(),
            leadingIcon = {  Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search"


            ) },
            placeholder = {Text(text = "Search Products,Category...")}



        )





        //End of searchbar


        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Product Categories",
            fontSize = 20.sp, fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 10.dp,end = 10.dp, )

        )

        Spacer(modifier = Modifier.height(20.dp))


        //Start of row
        //Brackets are mainly used for styling , {} contains the contents

        Row(modifier = Modifier.padding(start = 20.dp).horizontalScroll(rememberScrollState())


        ) {

            Column(){
                Image(

                    painter = painterResource(R.drawable.electronics),
                    contentDescription = "",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text ="Electronics",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 10.dp,end = 10.dp, ).clip(shape =  RoundedCornerShape(10.dp),)



                )

            }

            Spacer(modifier = Modifier.width(20.dp))


            Column(){

                Image(

                    painter = painterResource(R.drawable.electronics),
                    contentDescription = "",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text ="Clothes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 10.dp,end = 10.dp, ).clip(shape =  RoundedCornerShape(10.dp),)



                )



                

            }

            Spacer(modifier = Modifier.width(20.dp))


            Column(){
                Image(

                    painter = painterResource(R.drawable.electronics),
                    contentDescription = "",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text ="Electronics",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 10.dp,end = 10.dp, ).clip(shape =  RoundedCornerShape(10.dp),)



                )

            }

            Spacer(modifier = Modifier.width(20.dp))

            Column(){
                Image(

                    painter = painterResource(R.drawable.electronics),
                    contentDescription = "",
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text ="Electronics",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(start = 10.dp,end = 10.dp, ).clip(shape =  RoundedCornerShape(10.dp),)



                )

            }




        }





        //End of row











    }





}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){

    HomeScreen(rememberNavController())


}
