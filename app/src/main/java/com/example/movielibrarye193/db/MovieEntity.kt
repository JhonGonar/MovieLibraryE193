package com.example.movielibrarye193.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movielibrarye193.model.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val year: Int,
    val genre: String,
    val rating: Float,
    val watched: Boolean
)

fun MovieEntity.toMovie() = Movie(id, title, year, genre, rating, watched)
fun Movie.toMovieEntity() = MovieEntity(id, title, year, genre, rating, watched)
