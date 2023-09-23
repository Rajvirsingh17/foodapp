package app.assignment4.foodapp

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*

import app.assignment4.foodapp.ui.theme.FoodappTheme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RestaurantList(navController: NavController) {
    val restaurants = listOf(
        Restaurant("KFC", 1, R.drawable.kfc),
        Restaurant("Dominos", 2, R.drawable.dominos),
        Restaurant("Burger King", 2, R.drawable.burgerking),
        Restaurant("McDonalds", 2, R.drawable.mcdonalds)
    )
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(restaurants) { restaurant ->
                RestaurantCard(restaurant, navController)
            }
        }
    }
}
@Composable
fun RestaurantCard(restaurant: Restaurant, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray)
            .clickable {
                navController.navigate("food_item_list/${restaurant.id}")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = restaurant.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = restaurant.name,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp)
            )
        }
    }
}

/*
@Composable
fun RestaurantList(
    restaurants: List<Restaurant>,
    navController: NavController,
    focusManager: FocusManager
) {
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BasicTextField(
            value = searchQuery,
            onValueChange = { newValue -> searchQuery = newValue },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(restaurants) { restaurant ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("food_item_list/${restaurant.id}")
                        },
                    elevation = 1.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Display restaurant image
                        Image(
                            painter = painterResource(id = restaurant.imageResourceId),
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(MaterialTheme.shapes.medium)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = restaurant.name,
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
        }
    }
}
*/