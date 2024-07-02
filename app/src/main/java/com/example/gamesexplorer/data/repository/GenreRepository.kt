package com.example.gamesexplorer.data.repository

import com.example.gamesexplorer.data.model.GenreResponse
import com.example.gamesexplorer.data.remote.Network
import retrofit2.Response

class GenreRepository {
    private val apiService = Network.genreApiService

    suspend fun getGenres(apiKey: String): Response<GenreResponse> {
        return apiService.getGenres(apiKey)
    }
}