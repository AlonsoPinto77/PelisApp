package com.example.pelisapp.ui.catalogue

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Resource
import com.example.domain.interactors.movies.GetMovies
import com.example.domain.interactors.movies.GetMoviesOffline
import com.example.domain.interactors.movies.Movie
import kotlinx.coroutines.launch

class CatalogueViewModel(
    private val getMoviesCase: GetMovies,
    private val getMoviesOfflineCase: GetMoviesOffline
) : ViewModel(){

    val getMoviesViewState = MutableLiveData<Resource<List<Movie>>>()

    fun getMovies(page: Int){
        viewModelScope.launch {
            runCatching {
                getMoviesViewState.postValue(Resource.Loading())
                getMoviesCase.invoke(page)
            }.onSuccess {
                getMoviesViewState.postValue(Resource.Success(it))
            }.onFailure {
                Log.e("Error API", it.message.toString())
                getMoviesOffline(page)
            }
        }
    }

    private fun getMoviesOffline(page: Int){
        viewModelScope.launch {
            runCatching {
                getMoviesViewState.postValue(Resource.Loading())
                getMoviesOfflineCase.invoke(page)
            }.onSuccess {
                getMoviesViewState.postValue(Resource.Success(it))
            }.onFailure {
                getMoviesViewState.postValue(Resource.Error(it.message.toString()))
            }
        }
    }
}