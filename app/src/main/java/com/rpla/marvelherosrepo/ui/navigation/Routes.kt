package com.rpla.marvelherosrepo.ui.navigation

sealed class Routes(val route: String) {
    object HomeScreen : Routes("HomeScreen")
    object CharacterProfile : Routes("CharacterProfile?name={id}") {
        fun createRoute(id : String) = "CharacterProfile?name=$id"
    }
}