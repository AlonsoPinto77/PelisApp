package com.example.pelisapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.interactors.user.SaveUser
import com.example.domain.interactors.user.User
import com.example.domain.interactors.user.UserSignIn
import com.example.pelisapp.common.USER_VALIDATION
import kotlinx.coroutines.launch

class LoginViewModel(
    private val signInCase: UserSignIn,
    private val saveUser: SaveUser
) : ViewModel() {

    val signInViewState = MutableLiveData<Resource<String>>()
    fun login(user: User){
        viewModelScope.launch {
            runCatching {
            signInViewState.postValue(Resource.Loading(true))
                signInCase.invoke(user)
            }.onSuccess {
                if(it == USER_VALIDATION)
                    signInViewState.postValue(Resource.Success(it))
                else
                    signInViewState.postValue(Resource.Error("Usuario no Valido"))
            }
        }
    }

    /*fun registerUser(){
        viewModelScope.launch {
            runCatching {
                saveUser.invoke()
            }
        }
    }*/
}