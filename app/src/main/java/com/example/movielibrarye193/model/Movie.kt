package com.example.movielibrarye193.model

data class Movie(
    val id: Int = 0,
    val title: String,
    val year: Int,
    val genre: String,
    val rating: Float,
    val watched: Boolean
)
