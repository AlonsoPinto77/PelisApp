package com.example.data.repositories.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("select * from movie where page =:page")
    suspend fun getAllMoviesByPage(page: Int) : List<MovieEntity>

    @Query("select * from movie where id=:id")
    suspend fun getMovie(id: Int) : MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiple(movie: List<MovieEntity>)
}