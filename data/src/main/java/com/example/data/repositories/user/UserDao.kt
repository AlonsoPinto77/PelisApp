package com.example.data.repositories.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("select 1 from user where userName =:userName and password=:password ")
    suspend fun loginValidation(userName: String, password: String): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity)
}