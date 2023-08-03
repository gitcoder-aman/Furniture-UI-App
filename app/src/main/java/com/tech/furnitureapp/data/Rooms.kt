package com.tech.furnitureapp.data

import androidx.annotation.DrawableRes
import com.tech.furnitureapp.R

data class Rooms(
    val id : Int,
    val title : String,
    @DrawableRes val image : Int,

)
val roomList = listOf(
    Rooms(1,"Bed Room", R.drawable.bed_room),
    Rooms(2,"Dinning Room", R.drawable.dinning_room),
    Rooms(3,"Bed Room", R.drawable.bed_room),
    Rooms(4,"Dinning Room", R.drawable.dinning_room),
    Rooms(5,"Bed Room", R.drawable.bed_room),
    Rooms(6,"Dinning Room", R.drawable.dinning_room),
    Rooms(7,"Bed Room", R.drawable.bed_room),
    Rooms(8,"Dinning Room", R.drawable.dinning_room),
    Rooms(9,"Bed Room", R.drawable.bed_room),
    Rooms(10,"Dinning Room", R.drawable.dinning_room),
)
