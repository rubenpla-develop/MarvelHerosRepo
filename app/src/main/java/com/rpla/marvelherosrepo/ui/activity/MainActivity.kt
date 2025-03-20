package com.rpla.marvelherosrepo.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rpla.marvelherosrepo.home.ui.HomeScreen
import com.rpla.marvelherosrepo.home.ui.viewModel.CharactersListViewModel
import com.rpla.marvelherosrepo.home.ui.viewModel.HomeIntent
import com.rpla.marvelherosrepo.profile.ui.CharacterDetailScreen
import com.rpla.marvelherosrepo.ui.navigation.CHARACTER_ID_PARAM_NAME
import com.rpla.marvelherosrepo.ui.navigation.DEFAULT_CHARACTER_ID
import com.rpla.marvelherosrepo.ui.navigation.Routes
import com.rpla.marvelherosrepo.ui.theme.MarvelHerosRepoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CharactersListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MarvelHerosRepoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()

                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.HomeScreen.route
                    ) {
                        composable(Routes.HomeScreen.route) {
                            HomeScreen(
                                viewModel = viewModel,
                                navigationController = navigationController
                            )
                        }
                        composable(Routes.CharacterProfile.route,
                            arguments =  listOf(navArgument(CHARACTER_ID_PARAM_NAME) {
                                defaultValue = DEFAULT_CHARACTER_ID
                            })) { NavBackStackEntry ->

                            CharacterDetailScreen(
                                characterId = NavBackStackEntry.arguments?.getInt(
                                    CHARACTER_ID_PARAM_NAME)) {
                                navigationController.navigate(Routes.HomeScreen.route)
                            }
                        }
                    }
                }
            }
        }

        viewModel.dispatchIntent(HomeIntent.AllCharacters)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarvelHerosRepoTheme {
        Greeting("Android")
    }
}