package com.example.domain.interactors.user

interface UserRepository {
    suspend fun userSignIn(signInRequest: User): String
    suspend fun saveUser(): Boolean
}