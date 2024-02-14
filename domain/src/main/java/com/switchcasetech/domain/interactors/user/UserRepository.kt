package com.switchcasetech.domain.interactors.user

interface UserRepository {
    suspend fun userSignIn(signInRequest: User): String
}