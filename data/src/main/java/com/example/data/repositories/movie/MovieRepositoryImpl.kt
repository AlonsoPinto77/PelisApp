package com.example.data.repositories.movie

import com.example.data.network.Api
import com.example.domain.interactors.movies.Movie
import com.example.domain.interactors.movies.MoviesRepository

class MovieRepositoryImpl(private val api : Api, private val movieDao: MovieDao): MoviesRepository {
    override suspend fun getMovies(page: Int): List<Movie> {
        try{
            val response = api.getMovies(page, "f46b58478f489737ad5a4651a4b25079") //todo añadir api key
            val movieList : MutableList<Movie> = arrayListOf()
            if (response.results.isNotEmpty()){
                for(movie in response.results){
                    movie.page = response.page
                    movieList.add(movie.mapToDomainModel())
                }
            }
            movieDao.insertMultiple(response.results)
            return movieList
        } catch (e: Exception){
            throw Exception(e.message)
        }
    }

    override suspend fun getMoviesOffline(page: Int): List<Movie> {
        try {
            val movieList: MutableList<Movie> = arrayListOf()
            val dbResponse = movieDao.getAllMoviesByPage(page)
            for (movie in dbResponse)
                movieList.add(movie.mapToDomainModel())
            return movieList
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}