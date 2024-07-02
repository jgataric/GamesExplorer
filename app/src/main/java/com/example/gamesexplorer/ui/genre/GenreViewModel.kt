package com.example.gamesexplorer.ui.genre

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gamesexplorer.data.model.Genre
import com.example.gamesexplorer.data.repository.GenreRepository
import kotlinx.coroutines.launch

class GenreViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GenreRepository()
    private val sharedPreferences = application.getSharedPreferences("genre_preferences", Context.MODE_PRIVATE)


    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres

    fun fetchGenres(apiKey: String) {
        viewModelScope.launch {
            val response = repository.getGenres(apiKey)
            if (response.isSuccessful) {
                _genres.postValue(response.body()?.results)
            } else {
                // Handle error
            }
        }
    }

    fun saveGenreSelection(genre: Genre) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("onboarding_completed", true)
        editor.putString("selected_genre", genre.name)
        editor.apply()
    }

    fun isOnboardingCompleted(): Boolean {
        return sharedPreferences.getBoolean("onboarding_completed", false)
    }

}
