package com.example.bestmovie.ui

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    val retrofit = Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun interfaceObj(): GetMovies {
        return retrofit.create(GetMovies::class.java) as GetMovies
    }
}