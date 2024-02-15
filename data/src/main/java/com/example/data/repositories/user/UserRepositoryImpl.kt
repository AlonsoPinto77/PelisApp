package com.example.data.repositories.user

import com.example.data.common.USER_VALIDATION
import com.example.data.common.USER_VALIDATION_FAIL
import com.example.domain.interactors.user.User
import com.example.domain.interactors.user.UserRepository


class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun userSignIn(signInRequest: User): String {
        try {
            saveUser()
            val validation =
                userDao.loginValidation(signInRequest.userName, signInRequest.userPassword)
            return if (validation == 1)
                USER_VALIDATION
            else
                USER_VALIDATION_FAIL
        } catch (e: Exception) {
            throw Exception(e.message)
        }

    }

    override suspend fun saveUser(): Boolean {
        try {
            userDao.insert(
                UserEntity(
                    "Admin", "Password*123"
                )
            )
            return true
        } catch (e: Exception){
            throw Exception(e.message)
        }
    }
}