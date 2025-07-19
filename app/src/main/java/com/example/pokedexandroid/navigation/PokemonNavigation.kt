package com.example.pokedexandroid.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedexandroid.ui.screens.PokemonDetailScreen
import com.example.pokedexandroid.ui.screens.PokemonListScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PokemonNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "pokemon_list",
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        composable("pokemon_list") {
            PokemonListScreen(
                onPokemonClick = { pokemonId ->
                    navController.navigate("pokemon_detail/$pokemonId")
                }
            )
        }
        
        composable("pokemon_detail/{pokemonId}") {
            PokemonDetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}