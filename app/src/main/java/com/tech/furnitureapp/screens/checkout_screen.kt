package com.tech.furnitureapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.furnitureapp.R
import com.tech.furnitureapp.data.ShoppingBag
import com.tech.furnitureapp.data.shoppingBagList
import com.tech.furnitureapp.ui.theme.DarkOrange
import com.tech.furnitureapp.ui.theme.FurnitureAppTheme
import com.tech.furnitureapp.ui.theme.LightGray_1
import com.tech.furnitureapp.ui.theme.LineColor
import com.tech.furnitureapp.ui.theme.TextColor_1

@Composable
fun CheckOutScreen(navHostController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(20.dp)) {

            IconButton(onClick = { navHostController.navigateUp() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "")
            }
            Text(
                text = stringResource(R.string.my_shopping_bag), style = TextStyle(
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.W600
                ), modifier = Modifier.padding(vertical = 15.dp)
            )
            LazyColumn(modifier = Modifier.padding(top = 20.dp, bottom = 80.dp)) {
                items(shoppingBagList, key = { it.id }) {
                    ShoppingEachRow(shoppingList = it)
                }
                item {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        color = LineColor
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    RecommendedProduct(modifier = Modifier.padding(0.dp))

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        CheckOutRow(modifier = Modifier.align(BottomCenter))

    }
}

@Composable
fun ShoppingEachRow(shoppingList: ShoppingBag) {

    var count by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .padding(vertical = 10.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = shoppingList.icon), contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(CenterVertically)
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = shoppingList.productName, style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            color = TextColor_1
                        ), modifier = Modifier.weight(1f)
                    )
                    Box(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .background(Color.White)
                            .border(
                                2.dp,
                                DarkOrange, CircleShape
                            )
                            .size(30.dp), contentAlignment = Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }

                Text(
                    text = "${shoppingList.qty} Qty", style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = LightGray_1
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(modifier = Modifier.weight(1f)) {

                        ProductCountButton(onClick = {
                            if (count > 0) count--
                        }, operator = "-")

                        Text(
                            text = "$count",
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .align(CenterVertically)
                        )

                        ProductCountButton(onClick = {
                            count++
                        }, operator = "+")

                        Text(
                            text = shoppingList.price, style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400,
                                color = DarkOrange
                            ), modifier = Modifier
                                .weight(1f)
                                .align(CenterVertically), textAlign = TextAlign.End
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CheckOutRow(modifier :Modifier = Modifier,onClick:()->Unit = {}) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(modifier = Modifier.fillMaxWidth(), color = LineColor)
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(text = stringResource(R.string.total), style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = LightGray_1
                ))
                Text(text = "$600", style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = DarkOrange
                ))
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = onClick,
                modifier = modifier
                    .fillMaxWidth()
                    .height(40.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = TextColor_1
                ), elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Text(
                    text = stringResource(R.string.proceed_to_checkout), style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400
                    )
                )
            }
        }
    }
}
@Composable
fun CheckoutPreview() {
    FurnitureAppTheme {
        val navHostController = rememberNavController()
        CheckOutScreen(navHostController = navHostController)
    }
}