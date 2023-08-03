package com.tech.furnitureapp.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.tech.furnitureapp.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Popular(
    val id : Int,
    val title : String,
    @DrawableRes val image : Int,
    val price : String
) : Parcelable

val popularList = listOf(
    Popular(1,"Severom Chair", R.drawable.product_one,"$400"),
    Popular(2,"Noriviken Chair & Table", R.drawable.product_two,"$999"),
    Popular(3,"Ektorp Sofa", R.drawable.product_three,"$800"),
    Popular(4,"Jan Sflanaganvik Sofa", R.drawable.product_four,"$700")
)
