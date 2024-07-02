package com.example.gamesexplorer.data.remote

import com.example.gamesexplorer.data.model.GenreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("genres")
    suspend fun getGenres(@Query("key") apiKey: String): Response<GenreResponse>
}