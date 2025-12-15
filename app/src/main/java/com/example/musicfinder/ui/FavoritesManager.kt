package com.example.musicfinder.ui

// Singleton para guardar favoritos
object FavoritesManager {
    private val favorites = mutableListOf<Release>()

    fun addFavorite(release: Release) {
        if (!favorites.any { it.title == release.title }) {
            favorites.add(release)
        }
    }

    fun getFavorites(): List<Release> = favorites
}