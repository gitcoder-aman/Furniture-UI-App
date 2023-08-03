package com.tech.furnitureapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tech.furnitureapp.screens.CheckOutScreen
import com.tech.furnitureapp.screens.Home_Screen
import com.tech.furnitureapp.screens.ProductDetailScreen

@Composable
fun FurnitureNavigation() {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Home ){
        composable(Home){
            Home_Screen(navHostController)
        }

        composable(ProductDetail){
            ProductDetailScreen(navHostController)
        }
        composable(CheckOut){
            CheckOutScreen(navHostController)
        }


    }
    
}
const val Home = "home_screen"
const val ProductDetail = "product_detail_screen"
const val CheckOut = "checkout_screen"