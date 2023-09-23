package app.assignment4.foodapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument

@Composable
fun AppNavigator(navController: NavController) {
    NavHost(navController as NavHostController, startDestination = "restaurant_list") {
        composable("restaurant_list") {
            RestaurantList(navController)
        }
        composable(
            "food_item_list/{restaurantId}",
            arguments = listOf(navArgument("restaurantId") { type = NavType.IntType })
        )
        { entry ->
            val restaurantId = entry.arguments?.getInt("restaurantId") ?: -1
            FoodItemList(restaurantId)
        }
    }
}
