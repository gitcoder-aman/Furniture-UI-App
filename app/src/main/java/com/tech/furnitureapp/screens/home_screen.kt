package com.tech.furnitureapp.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.furnitureapp.R
import com.tech.furnitureapp.data.Category
import com.tech.furnitureapp.data.Popular
import com.tech.furnitureapp.data.Rooms
import com.tech.furnitureapp.data.categoryList
import com.tech.furnitureapp.data.popularList
import com.tech.furnitureapp.data.roomList
import com.tech.furnitureapp.navigation.ProductDetail
import com.tech.furnitureapp.ui.theme.DarkOrange
import com.tech.furnitureapp.ui.theme.FurnitureAppTheme
import com.tech.furnitureapp.ui.theme.LightGray_1
import com.tech.furnitureapp.ui.theme.TextColor_1

@Composable
fun Home_Screen(navHostController: NavHostController) {

    var searchText by remember {
        mutableStateOf("")
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            Header()
            CustomTextField(text = searchText, onValueChange = {
                searchText = it
            })
            Spacer(modifier = Modifier.height(20.dp))
            CategoryRow()

            Spacer(modifier = Modifier.height(20.dp))
            PopularRow{
                navHostController.currentBackStackEntry?.savedStateHandle?.set("data",it)
                navHostController.navigate(ProductDetail)
            }

            Spacer(modifier = Modifier.height(20.dp))
            BannerRow()

            Spacer(modifier = Modifier.height(20.dp))
            Rooms()
        }
    }
}

@Composable
fun Header(onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = stringResource(R.string.explore_what_you_home_needs), style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black
            )
        )
        IconButton(
            onClick = { onClick() },
            modifier = Modifier
                .size(32.dp)
                .align(CenterVertically)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(text: String, modifier: Modifier = Modifier, onValueChange: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = DarkOrange
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.placeholder), style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W400,
                    color = LightGray_1,
                )
            )
        },
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "search",
                tint = LightGray_1
            )
        },
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
            .border(2.dp, LightGray_1, shape = RoundedCornerShape(8.dp)),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}

@Composable
fun CategoryRow() {
    Column {
        CommonTitle(title = stringResource(R.string.categories))
        Spacer(modifier = Modifier.height(20.dp))

        LazyRow(modifier = Modifier.padding(start = 20.dp)) {
            items(categoryList, key = { it.id }) {
                CategoryEachRow(category = it)
            }
        }
    }
}

@Composable
fun CommonTitle(title: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Text(
            text = title, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black
            )
        )
        TextButton(onClick = { onClick() }) {
            Text(
                text = "See All", style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = DarkOrange
                )
            )
            Spacer(modifier = Modifier.width(3.dp))
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "",
                tint = DarkOrange,
                modifier = Modifier.size(20.dp)
            )
        }

    }
}

@Composable
fun CategoryEachRow(category: Category) {
    Box(
        modifier = Modifier
            .padding(end = 15.dp)
            .background(category.color, RoundedCornerShape(8.dp))
            .width(140.dp)
            .height(80.dp)
    ) {
        Text(
            text = category.title, style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = Color.Black
            ), modifier = Modifier
                .padding(start = 5.dp)
                .align(CenterStart)
        )
        Icon(
            painter = painterResource(id = category.image), tint = Color.Unspecified,
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .scale(.8f)
                .padding(end = 5.dp)
                .align(
                    BottomEnd
                )
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularRow(onClick: (popular:Popular) -> Unit) {
    Column {
        CommonTitle(title = stringResource(R.string.popular))
        Spacer(modifier = Modifier.height(10.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            popularList.forEach {
                PopularEachRow(popular = it){
                    onClick(it)
                }
            }
        }
    }
}

@Composable
fun PopularEachRow(
    popular: Popular,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {

    var isWishlist by remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier.clickable { onClick() }) {
        Box {
            Image(
                painter = painterResource(id = popular.image),
                contentDescription = "",
                modifier = modifier
                    .width(141.dp)
                    .height(149.dp)
            )
            Box(
                modifier = modifier
                    .padding(10.dp)      //here is given margin not that padding in Box
                    .background(if (isWishlist) Color.Red else Color.White, CircleShape)
                    .size(25.dp)
                    .align(
                        TopEnd
                    )
            ) {

                IconButton(onClick = { isWishlist = !isWishlist }) {

                    Icon(
                        painter = painterResource(id = R.drawable.wishlist),
                        tint = if (isWishlist) Color.White else Color.Unspecified,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(5.dp)  //here is given padding in Icon
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        ElevatedCard(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(bottom = 10.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp)) {
                Text(
                    text = popular.title, style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = LightGray_1
                    )
                )
                Text(
                    text = popular.price, style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.Black
                    )
                )
            }
        }
    }
}

@Composable
fun BannerRow() {
    Image(
        painter = painterResource(id = R.drawable.banner),
        contentDescription = "",
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(113.dp)
            .clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.FillWidth
    )
}

@Composable
fun Rooms() {
    Column(modifier = Modifier.padding(20.dp)) {
        Text(
            text = stringResource(R.string.rooms), style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = stringResource(R.string.room_desc), style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = Color.Gray
            )
        )
        LazyRow(modifier = Modifier.padding(top = 10.dp)) {
            items(roomList, key = { it.id }) {
                RoomSection(rooms = it)
            }
        }

    }
}

@Composable
fun RoomSection(rooms: Rooms) {
    Box(modifier = Modifier.padding(end = 15.dp)) {
        Image(
            painter = painterResource(id = rooms.image),
            contentDescription = "",
            modifier = Modifier
                .width(127.dp)
                .height(195.dp)
                .clip(
                    RoundedCornerShape(8.dp)
                )
        )
        Text(
            text = rooms.title, style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = TextColor_1
            ), modifier = Modifier
                .width(100.dp)
                .padding(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    FurnitureAppTheme {
        val navHostController = rememberNavController()
        Home_Screen(navHostController = navHostController)
//        PopularEachRow(popular = popularList[0])
    }
}