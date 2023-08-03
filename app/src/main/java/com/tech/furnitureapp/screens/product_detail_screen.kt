package com.tech.furnitureapp.screens

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tech.furnitureapp.R
import com.tech.furnitureapp.data.Popular
import com.tech.furnitureapp.data.popularList
import com.tech.furnitureapp.navigation.CheckOut
import com.tech.furnitureapp.ui.theme.DarkOrange
import com.tech.furnitureapp.ui.theme.FurnitureAppTheme
import com.tech.furnitureapp.ui.theme.GrayColor
import com.tech.furnitureapp.ui.theme.LightGray_1
import com.tech.furnitureapp.ui.theme.LightOrange
import com.tech.furnitureapp.ui.theme.LineColor
import com.tech.furnitureapp.ui.theme.TextColor_1

@Composable
fun ProductDetailScreen(navHostController: NavHostController) {

    val productDetail =
        navHostController.previousBackStackEntry?.savedStateHandle?.get<Popular>("data")

    val chips = listOf("Description", "Material", "Review")
    var selected by remember {
        mutableStateOf(0)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.product_four), contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp), contentScale = ContentScale.FillWidth
        )
        HeaderSection {
            navHostController.navigateUp()
        }
        Box(
            modifier = Modifier
                .padding(top = 230.dp)  ///this is not padding it's here given margin
                .fillMaxSize()
                .background(Color.White, RoundedCornerShape(16.dp))
        ) {
            LazyColumn {
                item {
                    if (productDetail != null)
                        ProductHeaderSection(productDetail = productDetail)

                    RatingRow()
                    Spacer(modifier = Modifier.height(15.dp))

                    Row {
                        chips.forEachIndexed { index, s ->
                            CustomChips(
                                titles = s,
                                selected = index == selected,
                                index = index,
                                onValueChange = {
                                    selected = it
                                })
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = stringResource(R.string.chip_desc), style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            color = LightGray_1
                        ), modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Divider(modifier = Modifier.fillMaxWidth(), color = GrayColor, thickness = 5.dp)

                    Spacer(modifier = Modifier.height(10.dp))
                    RecommendedProduct(modifier = Modifier.padding(20.dp))

                    BottomBarItem(modifier = Modifier.align(BottomCenter)) {
                        navHostController.navigate(CheckOut)
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderSection(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "",
            )
        }
        Icon(imageVector = Icons.Default.Share, contentDescription = "")
    }
}

@Composable
fun ProductHeaderSection(productDetail: Popular) {

    var count by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = productDetail.title, style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.W400,
                color = TextColor_1
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = productDetail.price, style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                    color = DarkOrange
                ), modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically)
            )
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
        }
    }
}

@Composable
fun ProductCountButton(onClick: () -> Unit, operator: String) {
    TextButton(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.size(35.dp),
        border = BorderStroke(2.dp, DarkOrange),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        @DrawableRes val operatorSign: Int
        if (operator == "+") {
            operatorSign = R.drawable.plus
        } else {
            operatorSign = R.drawable.minus
        }
        Icon(
            painter = painterResource(id = operatorSign),
            contentDescription = "",
            tint = DarkOrange, modifier = Modifier.size(35.dp)
        )
    }
}

@Composable
fun RatingRow() {

    val personIcon = listOf(
        R.drawable.person_1,
        R.drawable.person_2,
        R.drawable.person_3,
        R.drawable.person_4,
    )
    Box(
        modifier = Modifier
            .padding(20.dp)
            .background(GrayColor, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(15.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Row {
                    repeat(5) {
                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "",
                            tint = Color.Unspecified
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "5.0", style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            color = TextColor_1
                        ), modifier = Modifier.align(CenterVertically)
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                Row {
                    Text(
                        text = stringResource(R.string._98_reviews), style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            color = LightGray_1
                        )
                    )
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = "",
                        modifier = Modifier
                            .size(16.dp)
                            .align(CenterVertically),
                        tint = LightGray_1
                    )
                }
            }
            Row {
                personIcon.forEachIndexed { index, i ->
                    Icon(
                        painter = painterResource(id = i),
                        contentDescription = "",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(32.dp)
                            .offset(
                                x = ((if (index == 1) (-10.dp) else if (index == 2) (-20.dp) else if (index == 3) (-30.dp) else 0.dp)),
                                y = 0.dp
                            )
                    )
                }
            }

        }
    }
}

@Composable
fun CustomChips(titles: String, selected: Boolean, index: Int, onValueChange: (Int) -> Unit) {
    TextButton(
        onClick = { onValueChange(index) },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) LightOrange else Color.Transparent,
            contentColor = if (selected) DarkOrange else LightGray_1
        ),
        elevation = ButtonDefaults.buttonElevation(0.dp),
        modifier = Modifier.padding(start = 20.dp)
    ) {
        Text(
            text = titles, style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        )
    }
}

@Composable
fun RecommendedProduct(modifier: Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(R.string.recommended_sofa), style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = TextColor_1
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        LazyRow {
            items(popularList, key = { it.id }) {
                PopularEachRow(popular = it, modifier = Modifier.padding(end = 10.dp))
            }
        }

    }
}

@Composable
fun BottomBarItem(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider(modifier = Modifier.fillMaxWidth(), color = LineColor)
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            TextButton(
                onClick = {}, modifier = modifier
                    .border(
                        1.dp, LightGray_1,
                        RoundedCornerShape(8.dp)
                    )
                    .size(40.dp),
                elevation = ButtonDefaults.buttonElevation(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "",
                    modifier = modifier.size(16.dp),
                    tint = LightGray_1
                )
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
                    text = stringResource(R.string.add_to_bag), style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailPreview() {
    FurnitureAppTheme {
        val navHostController = rememberNavController()
        ProductDetailScreen(navHostController = navHostController)
    }
}