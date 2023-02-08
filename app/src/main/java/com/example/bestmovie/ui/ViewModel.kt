package com.example.bestmovie.ui


import androidx.lifecycle.MutableLiveData
import com.example.bestmovie.pojo.Result
import com.example.bestmovie.pojo.Root
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : androidx.lifecycle.ViewModel() {
    val liveDataToprated: MutableLiveData<List<Result>> =
        MutableLiveData<List<Result>>()
    fun getMovieToprated(){
        val listMovies: Call<Root>? =RetrofitBuilder().interfaceObj().getMovie("top_rated", "cc3f22009f7f0053c6a4b97430f3e8e4")
        listMovies?.enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                 liveDataToprated.value= response.body()?.getResults() as List<Result>?
            }
            override fun onFailure(call: Call<Root>, t: Throwable) {}
        })
    }

}
