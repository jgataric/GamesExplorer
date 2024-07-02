package com.example.gamesexplorer.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamesexplorer.data.model.Genre
import com.example.gamesexplorer.data.repository.GenreRepository
import kotlinx.coroutines.launch

class OnboardingViewModel : ViewModel() {

    private val repository = GenreRepository()

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> get() = _genres

    private val _selectedGenres = MutableLiveData<MutableList<Genre>>(mutableListOf())
    val selectedGenres: LiveData<List<Genre>> get() = _selectedGenres as LiveData<List<Genre>>

    fun fetchGenres(apiKey: String) {
        viewModelScope.launch {
            val response = repository.getGenres(apiKey)
            if (response.isSuccessful) {
                _genres.value = response.body()?.results ?: emptyList()
            }
        }
    }

    fun toggleGenreSelection(genre: Genre) {
        val selected = _selectedGenres.value ?: mutableListOf()
        if (selected.contains(genre)) {
            selected.remove(genre)
        } else {
            selected.add(genre)
        }
        _selectedGenres.value = selected
    }

    fun canProceed(): Boolean {
        return _selectedGenres.value?.size ?: 0 >= 3
    }
}
