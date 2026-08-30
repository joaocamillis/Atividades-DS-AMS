package com.joaopedro.geovista

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joaopedro.geovista.pages.HomePage
import com.joaopedro.geovista.pages.LoginPage
import com.joaopedro.geovista.pages.SignupPage
import com.joaopedro.geovista.pages.DetailPage

@Composable
fun MyAppNavigation(
    authViewModel: AuthViewModel = viewModel()
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        composable("login") {
            LoginPage(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable("signup") {
            SignupPage(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable("home") {
            HomePage(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable("detail/{itemId}") { backStackEntry ->

            val itemId =
                backStackEntry.arguments?.getString("itemId") ?: ""

            DetailPage(
                navController = navController,
                itemId = itemId
            )
        }
    }
}