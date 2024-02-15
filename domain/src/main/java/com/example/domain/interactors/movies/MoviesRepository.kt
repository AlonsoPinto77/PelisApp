package com.example.domain.interactors.movies

interface MoviesRepository {

    suspend fun getMovies(page  : Int): List<Movie>
    suspend fun getMoviesOffline(page: Int) : List<Movie>
}