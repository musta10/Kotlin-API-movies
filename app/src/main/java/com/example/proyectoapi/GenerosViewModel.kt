package com.example.proyectoapi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoapi.models.Generos
import com.example.proyectoapi.remote.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.math.log

class GenerosViewModel : ViewModel() {
    val generoList = MutableStateFlow(Generos())
    val loading = MutableStateFlow(false)


    fun getGeneros() {
        loading.value = true
        viewModelScope.launch {
            val response = ApiService.api.getGeneros()
            if (response.isSuccessful) {
                generoList.value = response.body() ?: Generos()
                Log.v("GenerosVM", "todo fenomenal en la peticion de Generos")

            } else {
                Log.v("GenerosVM", "Error en la peticion de Generos ${response.toString()}")
            }
            loading.value = false
        }
    }
}