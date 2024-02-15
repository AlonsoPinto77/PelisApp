package com.example.data.network

import com.example.data.repositories.movie.MovieEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("upcoming")
    suspend fun getMovies(@Query("page") page : Int,
                          @Query("api_key") apiKey: String) : ApiResponse<List<MovieEntity>>
}