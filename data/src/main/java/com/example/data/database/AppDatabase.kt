package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.repositories.movie.MovieDao
import com.example.data.repositories.movie.MovieEntity
import com.example.data.repositories.user.UserDao
import com.example.data.repositories.user.UserEntity

@Database(
    entities = [UserEntity::class, MovieEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun movieDao() : MovieDao
}