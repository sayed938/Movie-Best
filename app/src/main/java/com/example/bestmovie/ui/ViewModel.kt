package com.example.bestmovie.ui


import android.annotation.SuppressLint
import com.example.bestmovie.ProjectData
import androidx.lifecycle.MutableLiveData
import com.example.bestmovie.pojo.PopularTVModel
import com.example.bestmovie.pojo.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ViewModel : androidx.lifecycle.ViewModel() {
    val liveDataMoviepopular: MutableLiveData<List<Result>> =
        MutableLiveData<List<Result>>()
    val liveDataMovietrending: MutableLiveData<List<Result>> =
        MutableLiveData<List<Result>>()
    val liveDataTvpopular: MutableLiveData<List<PopularTVModel.ResultTVPopular>> =
        MutableLiveData<List<PopularTVModel.ResultTVPopular>>()

    @SuppressLint("CheckResult")
    fun getMoviePopular() {
        val observable =
            RetrofitBuilder().interfaceObj().getMovie("movie/popular", ProjectData().api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        observable.subscribe { o -> liveDataMoviepopular.setValue(o.results) }
    }


    @SuppressLint("CheckResult")
    fun getTVPopular() {
        val observable =
            RetrofitBuilder().interfaceObj().getTv("popular", ProjectData().api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

        observable.subscribe { o -> liveDataTvpopular.setValue(o.results) }
    }

    @SuppressLint("CheckResult")
    fun getMovieTrending() {
        val observable =
            RetrofitBuilder().interfaceObj().getMovie("trending/all/day", ProjectData().api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        observable.subscribe { o -> liveDataMovietrending.setValue(o.results) }
    }
}


