package com.example.bestmovie.ui


import androidx.lifecycle.MutableLiveData
import com.example.bestmovie.pojo.Result
import com.example.bestmovie.pojo.Root
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModel : androidx.lifecycle.ViewModel() {
    val mutableLiveData: MutableLiveData<List<com.example.bestmovie.pojo.Result>> =
        MutableLiveData<List<com.example.bestmovie.pojo.Result>>()
    val retro = Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun getmoviedata(){

        val anInterface: MovieInfac = retro.create(MovieInfac::class.java)

        val listMovies: Call<Root>? =
            anInterface.getMovie("top_rated", "cc3f22009f7f0053c6a4b97430f3e8e4")
        listMovies?.enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                 mutableLiveData.value= response.body()?.getResults() as List<Result>?

            }

            override fun onFailure(call: Call<Root>, t: Throwable) {

            }
        })
    }

}
