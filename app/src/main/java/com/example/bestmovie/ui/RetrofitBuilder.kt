package com.example.bestmovie.ui

import com.example.bestmovie.ProjectData
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    private val retrofit = Retrofit.Builder().baseUrl(ProjectData().baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun interfaceObj(): GetMovies {
        return retrofit.create(GetMovies::class.java) as GetMovies
    }
}