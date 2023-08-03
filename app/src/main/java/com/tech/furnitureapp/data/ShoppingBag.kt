package com.tech.furnitureapp.data

import androidx.annotation.DrawableRes
import com.tech.furnitureapp.R

data class ShoppingBag(
    val id : Int,
    val productName : String,
    val qty : Int,
    val price : String,
    @DrawableRes val icon : Int
)

val shoppingBagList = listOf(
    ShoppingBag(1,"Sofa",1,"$500", R.drawable.product_one),
    ShoppingBag(2,"Noriviken Chair & Table",2,"$100", R.drawable.product_two),
    ShoppingBag(3,"Ektorp Sofa",1,"$200", R.drawable.product_three),
    ShoppingBag(4,"Jan Sflanaganvik Sofa",2, "$250",R.drawable.product_four)
)
