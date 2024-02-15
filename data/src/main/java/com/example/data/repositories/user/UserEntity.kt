package com.example.data.repositories.user

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.network.DomainMapper
import com.example.domain.interactors.user.User

@Entity(tableName = "user", indices = [Index(value = ["userName"], unique = true),])
data class UserEntity(
    @PrimaryKey
    @NonNull
    val userName : String,
    @NonNull
    val password : String,
) : DomainMapper<User> {
    override fun mapToDomainModel(): User {
        return User(userName, password)
    }
}