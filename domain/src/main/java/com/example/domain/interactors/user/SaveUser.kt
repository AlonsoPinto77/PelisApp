package com.example.domain.interactors.user

interface SaveUser{
    suspend operator fun invoke(): Boolean
}

class SaveUserImpl(private val userRepository: UserRepository): SaveUser{
    override suspend fun invoke(): Boolean {
        return userRepository.saveUser()
    }

}