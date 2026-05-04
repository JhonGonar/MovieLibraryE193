package com.example.movielibrarye193.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Insert
    fun insert(movie: MovieEntity)

    @Update
    fun update(movie: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)
}
