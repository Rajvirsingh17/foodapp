package app.assignment4.foodapp
import android.os.Bundle
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.*
import app.assignment4.foodapp.ui.theme.FoodappTheme


import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodappTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "login") {
                    composable("login"){
                        LoginScreen(navController)
                    }
                    composable("restaurant_list") {
                        RestaurantList(navController)
                    }
                    composable(
                        "food_item_list/{restaurantId}",
                        arguments = listOf(navArgument("restaurantId") { type = NavType.IntType })
                    ) { entry ->
                        val restaurantId = entry.arguments?.getInt("restaurantId") ?: -1
                        FoodItemList(restaurantId, navController)
                    }
                }
            }
        }
    }
}



