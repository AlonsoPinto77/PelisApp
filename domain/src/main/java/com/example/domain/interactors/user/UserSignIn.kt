package com.example.domain.interactors.user

interface UserSignIn {
    suspend operator fun invoke(signInRequest: User): String
}

class UserSignInImpl(private val userRepository: UserRepository): UserSignIn{
    override suspend fun invoke(signInRequest: User): String {
        return userRepository.userSignIn(signInRequest)
    }
}