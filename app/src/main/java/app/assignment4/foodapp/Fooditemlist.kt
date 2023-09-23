package app.assignment4.foodapp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun FoodItemList(restaurantId: Int,navController: NavController) {
    val restaurantName = when (restaurantId) {
        1 -> "KFC"
        2 -> "DOMINOS"
        3 -> "BURGER KING"
        4 -> "McDONALDS"
        else -> "Unknown Restaurant"
    }

    val foodItems = when (restaurantId) {
        1 -> listOf(
            Fooditem("Box Meal", 400.00, R.drawable.kfc1),
            Fooditem("12 Piece Bucket", 300.00, R.drawable.kfc2),
            Fooditem("6 pcs. Chicken Wings", 100.00, R.drawable.kfc3),
            Fooditem("Chicken Zinger", 250.00, R.drawable.kfc4)

        )

        2 -> listOf(
            Fooditem("Pepperoni Medium", 300.00, R.drawable.dom1),
            Fooditem("Onion and Capsicum Medium", 300.00, R.drawable.dom2),
            Fooditem("Tomato and Onion Medium", 200.00, R.drawable.dom3),
            Fooditem("Veg Fiesta Medium", 250.00, R.drawable.dom4)
        )

        3 -> listOf(
            Fooditem("Chicken Whopper", 200.00, R.drawable.bg1),
            Fooditem("Whopper Meal", 200.00, R.drawable.bg2),
            Fooditem("Big Chicken Whopper", 210.00, R.drawable.bg3),
            Fooditem("Chocolate Softie", 100.00, R.drawable.bg4)
        )

        4 -> listOf(
            Fooditem("Aloo Tikki Burger", 100.00, R.drawable.mcd),
            Fooditem("Maharaja Mac", 200.00, R.drawable.mcd2),
            Fooditem("Happy Meal", 210.00, R.drawable.mcd3),
            Fooditem("Chicken Med Meal", 190.00, R.drawable.mcd4)
        )

        else -> emptyList()
    }

    var selectedFoodItem by remember { mutableStateOf<Fooditem?>(null) }
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            item {
                Text(
                    text = "Food Items at $restaurantName",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
            items(foodItems) { foodItem ->
                FoodItemCard(
                    foodItem = foodItem,
                    isExpanded = foodItem == selectedFoodItem,
                    onClick = {
                        selectedFoodItem = if (selectedFoodItem == foodItem) null else foodItem
                    }
                )
            }
        }
    }
    IconButton(
        onClick = {
            navController.popBackStack()
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back to Restaurants"
        )
    }
}
@Composable
fun FoodItemCard(foodItem: Fooditem, isExpanded: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = foodItem.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = foodItem.name,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (isExpanded) {
                Text(
                    text = "Price: $${foodItem.price}",
                    style =TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
    }
}
