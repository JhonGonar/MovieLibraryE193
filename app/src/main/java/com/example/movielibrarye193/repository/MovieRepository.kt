package com.example.movielibrarye193.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.movielibrarye193.db.MovieDao
import com.example.movielibrarye193.db.toMovie
import com.example.movielibrarye193.db.toMovieEntity
import com.example.movielibrarye193.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val dao: MovieDao) {

    fun getMovies(): LiveData<List<Movie>> =
        dao.getAllMovies().map { list -> list.map { it.toMovie() } }

    fun getMovie(id: Int): LiveData<Movie> =
        dao.getMovieById(id).map { it.toMovie() }

    suspend fun insert(movie: Movie) = withContext(Dispatchers.IO) {
        dao.insert(movie.toMovieEntity())
    }

    suspend fun update(movie: Movie) = withContext(Dispatchers.IO) {
        dao.update(movie.toMovieEntity())
    }

    suspend fun delete(movie: Movie) = withContext(Dispatchers.IO) {
        dao.delete(movie.toMovieEntity())
    }
}
