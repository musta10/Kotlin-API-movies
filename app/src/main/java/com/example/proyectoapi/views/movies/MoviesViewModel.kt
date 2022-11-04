package com.example.proyectoapi.views.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoapi.models.Generos
import com.example.proyectoapi.models.Movie
import com.example.proyectoapi.models.MoviesMain
import com.example.proyectoapi.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MoviesViewModel: ViewModel(){
    val moviesList = MutableStateFlow(MoviesMain())
    val loading = MutableStateFlow(false)


    fun getMovies(id: String) {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getMovies(genres = id)
            if (response.isSuccessful) {
                moviesList.value = response.body() ?: MoviesMain()
                Log.v("GenerosVM", "todo fenomenal en la peticion de Generos")

            } else {
                Log.v("GenerosVM", "Error en la peticion de Generos ${response.toString()}")
            }
            loading.value = false
        }
    }
}