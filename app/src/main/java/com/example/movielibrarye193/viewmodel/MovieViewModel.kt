package com.example.movielibrarye193.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movielibrarye193.model.Movie
import com.example.movielibrarye193.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    val movies: LiveData<List<Movie>> = repository.getMovies()

    fun getMovie(id: Int): LiveData<Movie> = repository.getMovie(id)

    fun insert(movie: Movie) = viewModelScope.launch {
        repository.insert(movie)
    }

    fun update(movie: Movie) = viewModelScope.launch {
        repository.update(movie)
    }

    fun delete(movie: Movie) = viewModelScope.launch {
        repository.delete(movie)
    }

    companion object {
        fun factory(repository: MovieRepository) = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                MovieViewModel(repository) as T
        }
    }
}
