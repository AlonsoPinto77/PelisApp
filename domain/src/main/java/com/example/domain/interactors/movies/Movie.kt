package com.example.domain.interactors.movies

data class Movie(
    val id : Int,
    val posterUrl: String,
    val title: String,
    val overview: String,
    val voteAverage: Float,
    val releaseDate: String,
    val page : Int,
    val totalResults: Int
)