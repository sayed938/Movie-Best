package com.example.bestmovie.ui

import android.database.Observable
import com.example.bestmovie.pojo.PopularTVModel
import com.example.bestmovie.pojo.Root
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GetMovies {
    @GET("3/{sort}")
    open fun getMovie(
        @Path("sort") sort: String?,
        @Query("api_key") api_key: String?
    ): io.reactivex.Observable<Root>

    @GET("3/tv/{sort}")
    open fun getTv(
        @Path("sort") sort: String?,
        @Query("api_key") api_key: String?
    ): io.reactivex.Observable<PopularTVModel.RootTVPopular>


}