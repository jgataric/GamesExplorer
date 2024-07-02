package com.example.gamesexplorer.data.model

data class GenreResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Genre>
)

data class Genre(
    val id: Int,
    val name: String,
    val slug: String,
    val gamesCount: Int,
    val imageBackground: String,
    val games: List<Game>
)

data class Game(
    val id: Int,
    val slug: String,
    val name: String,
    val added: Int
)