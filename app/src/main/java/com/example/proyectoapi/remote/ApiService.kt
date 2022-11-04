package com.example.proyectoapi.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
        lateinit var api: Api

        val URL = "https://api.themoviedb.org/3/"
    var URL_IMAGES = "https://image.tmdb.org/t/p/w500/"
        val api_key = "a9dbb270d0a3648ef4ccbf03de229836"
        val language = "es-Es"

        init {
            initService()
        }

        private fun initService() {
            val retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            api = retrofit.create(Api::class.java)
        }
    }