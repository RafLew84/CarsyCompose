package com.example.carsycompose.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.carsycompose.ui.screens.CalculatorsScreen
import com.example.carsycompose.ui.screens.OverviewScreen
import com.example.carsycompose.ui.screens.Screens
import com.example.carsycompose.ui.screens.TimeLineScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomMenu(navController = navController)},
        content = { NavGraph(paddingValues = it, navController = navController) }
    )
}

@Composable
fun NavGraph(paddingValues: PaddingValues, navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screens.Overview.route
    ) {
        composable(route = Screens.Overview.route){ OverviewScreen() }
        composable(route = Screens.TimeLine.route){ TimeLineScreen(paddingValues) }
        composable(route = Screens.Calculators.route){ CalculatorsScreen() }
    }
}

@Composable
fun BottomMenu(navController: NavHostController){
    val screens = listOf(
        Screens.Overview, Screens.TimeLine, Screens.Calculators
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{
        screens.forEach{screen ->
            NavigationBarItem(
                label = { Text(text = screen.title)},
                icon = { Icon(imageVector = screen.icon, contentDescription = "icon") },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {navController.navigate(screen.route)}
            )
        }
    }
}