package com.example.bestmovie.ui

import com.example.bestmovie.pojo.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GetMovies {
@GET("3/movie/{sort}")
open fun getMovie(@Path("sort") sort: String?, @Query("api_key") api_key: String?): Call<Root>?

}