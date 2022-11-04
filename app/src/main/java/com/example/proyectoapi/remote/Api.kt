package com.example.proyectoapi.remote

import com.example.proyectoapi.models.Generos
import com.example.proyectoapi.models.MoviesMain
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("genre/movie/list")
    suspend fun getGeneros(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language
    ): Response<Generos>

    @GET("discover/movie")
    suspend fun getMovies(
        @Query("api_key") apikey: String = ApiService.api_key,
        @Query("language") language: String = ApiService.language,
        @Query("with_genres") genres: String
    ): Response<MoviesMain>

}